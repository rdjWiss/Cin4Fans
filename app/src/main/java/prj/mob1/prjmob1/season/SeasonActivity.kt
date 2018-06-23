package prj.mob1.prjmob1.season

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_season.*
import prj.mob1.prjmob1.Crew.CrewFragment
import prj.mob1.prjmob1.Person.PersonActivity
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.Util.ConnectivityChecker
import prj.mob1.prjmob1.Util.LoadingDialog
import prj.mob1.prjmob1.episode.EpisodeActivity
import prj.mob1.prjmob1.rating.OnRateClick
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService
import java.util.*

class SeasonActivity : AppCompatActivity(), CrewFragment.OnCrewSelected,
        SeasonEpisodesFragment.OnEpisodeSelected, OnRateClick {

    private var seasonNum : Int = 1
    private var showId: Int = 1399
    private var season = Season()

    private var showTitle: String = ""
    private var nbrEpisode : Int = 0
    private var network = ""

    private var modeTab = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_season)
        if (resources.getString(R.string.isLand) == "true") modeTab = true

        val bundle = intent.getBundleExtra("bundle")
        if(bundle != null){
            seasonNum  = bundle.getInt("seasonNum",1)
            showId = bundle.getInt("showId",1399)
            showTitle = bundle.getString("showTitle")
            nbrEpisode = bundle.getInt("nbrEpisode")
            network = bundle.getString("network")
        }else{
            seasonNum = 1
            showId = 1399
            //TODO if null show erro then finish act
        }

        if(ConnectivityChecker.isNetworkAvailable(this)) this.getSeasonData()
        else{
            Toast.makeText(this,"Can't get season infos. No Network Connection",Toast.LENGTH_SHORT).show()
            finish()
        }


        back_arrow.setOnClickListener{
            finish()
        }

    }

    private fun getSeasonData() {
        val loadingDialog= LoadingDialog.showLoadingDialog(this)

        val apiService: RemoteApiService? = RemoteApiService.create()
        apiService!!.getSeasonInfosById(showId, seasonNum)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    result ->
//                    Toast.makeText(this,"Response ${result}", Toast.LENGTH_LONG).show()
                    Log.e("SEASON",result.toString())
                    season = result
                    season.title_show = showTitle
                    season.nbr_episodes = nbrEpisode

                    loadingDialog.dismiss()
                    initSeasonInfosFrag()
                    initOverviewFragTabMode()
                    configureTabLayout()

                    initImageTop()
                }, { error ->
                    Toast.makeText(this,"Error ${error.message}", Toast.LENGTH_LONG).show()
                    error.printStackTrace()
                    loadingDialog.dismiss()
                    finish()

                })

    }

    private fun initSeasonInfosFrag(){
        //Fragment infos
        val infosFragment =
                SeasonInfosFragment.newInstance(season)
        supportFragmentManager.beginTransaction().add(R.id.season_infos, infosFragment).commit()
    }

    private fun initOverviewFragTabMode(){
        if (modeTab){
            val overviewFrag =
                    SeasonOverviewFragment.newInstance(season.overview)
            supportFragmentManager.beginTransaction()
                    .add(R.id.season_overview, overviewFrag).commit()
        }
    }

    private fun configureTabLayout() {
        if(!modeTab) season_tab_layout.addTab(season_tab_layout.newTab().setText(getString(R.string.season_tab1)))
        season_tab_layout.addTab(season_tab_layout.newTab().setText(getString(R.string.season_tab2)))
        season_tab_layout.addTab(season_tab_layout.newTab().setText(getString(R.string.season_tab3)))
        season_tab_layout.addTab(season_tab_layout.newTab().setText(getString(R.string.season_tab4)))

        val adapter = SeasonTabPagerAdapter(supportFragmentManager,
                season_tab_layout.tabCount,season,modeTab)
        season_viewpager.adapter = adapter

        season_viewpager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(season_tab_layout))

        season_tab_layout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                season_viewpager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }

    private fun initImageTop(){
        val imageTop = findViewById<ImageView>(R.id.season_trailer)
        //if(season.posterId!=null) RemoteApiService.getRemoteImage(season.posterId,this)!!.into(poster)
        val episodes = season.episodes
        val rand= { Random().nextInt(episodes.size-1 + 1 ) } // TODO fix invalid index -> Fixed ... maybe
        var i = rand()
        while (episodes[i].posterId == null) i= rand()
        RemoteApiService.getRemoteImage(episodes[i].posterId,this)!!.into(imageTop)
    }

    override fun onCrewSelected(creditId:Int) {
        val intent = Intent(this, PersonActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("personId",creditId)
        intent.putExtra("bundle",bundle)
        startActivity(intent)
    }

    override fun onEpisodeSelected(episodeNum : Int) {
        val intent = Intent(this, EpisodeActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("showId",showId)
        bundle.putString("showTitle",showTitle)
        bundle.putInt("seasonNum",seasonNum)
        bundle.putInt("episodeNum",episodeNum)
        bundle.putString("network",network)
        intent.putExtra("bundle",bundle)
        startActivity(intent)

    }

    override fun onRateClick(){
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_rating, null)
        val dialogRatingBar = dialogView.findViewById<RatingBar>(R.id.dialogRb)

        builder.setView(dialogView)
                .setPositiveButton("OK") { dialogInterface, i ->

                    Toast.makeText(this,
                            "Rated: ${dialogRatingBar.rating}", Toast.LENGTH_SHORT )
                            .show()

                }
                .setNegativeButton("Annuler") { dialogInterface, i ->
                }
                .show()
    }

    override fun onAddBookmark() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRemoveBookmark() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
