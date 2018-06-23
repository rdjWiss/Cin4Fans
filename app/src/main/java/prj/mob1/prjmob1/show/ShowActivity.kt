package prj.mob1.prjmob1.show

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import android.widget.VideoView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_show.*
import prj.mob1.prjmob1.Crew.CrewFragment
import prj.mob1.prjmob1.Person.PersonActivity
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.Util.ConnectivityChecker
import prj.mob1.prjmob1.Util.LoadingDialog
import prj.mob1.prjmob1.rating.OnRateClick
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService
import prj.mob1.prjmob1.season.Season
import prj.mob1.prjmob1.season.SeasonActivity

class ShowActivity : AppCompatActivity(), CrewFragment.OnCrewSelected,
                ShowSeasonsFragment.OnSeasonSelected, OnRateClick {

    private var modeTab = false
    private var id: Int = 0
    private var show: TVShow = TVShow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_show)
        if (resources.getString(R.string.isLand) == "true") modeTab = true

        val bundle = intent.getBundleExtra("bundle")
        if(bundle != null){
            id  = bundle.getInt("id")
        }else{
            //TODO: afficher erreur: id manquant / no bundle
            //finish()
            id = 1399//550
        }

        //Get les infos du show
        if(ConnectivityChecker.isNetworkAvailable(this)) this.getShowData()
        else{
            Toast.makeText(this,"Can't get show infos. No Network Connection",Toast.LENGTH_LONG).show()
            finish()
        }

        //Go back arrow
        back_arrow.setOnClickListener{
            finish()
        }

    }

    private fun getShowData(){
        val loadingDialog= LoadingDialog.showLoadingDialog(this)

        val apiService: RemoteApiService? = RemoteApiService.create()
        apiService!!.getShowInfosById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    result ->
//                    Toast.makeText(this,"Response ${result.networks[0].name}", Toast.LENGTH_LONG).show()
                    Log.e("SHOW",result.seasons.toString())
                    show = result

                    loadingDialog.dismiss()
                    initShowInfosFrag()
                    initOverviewFragTabMode()
                    configureTabLayout()
                    initTrailer()
                }, { error ->
                    Toast.makeText(this,"Error ${error.message}", Toast.LENGTH_LONG).show()
                    error.printStackTrace()
                    loadingDialog.dismiss()
                    finish()

                })

    }

    private fun initShowInfosFrag(){
        //Fragment infos
        val infosFragment =
                ShowInfosFragment.newInstance(show)
        supportFragmentManager.beginTransaction().add(R.id.show_infos, infosFragment).commit()
    }

    private fun initOverviewFragTabMode(){
        //Overview fragment en mode tablette
        if (modeTab){
            val overviewFrag =
                    ShowOverviewFragment.newInstance(show.overview)
            supportFragmentManager.beginTransaction()
                    .add(R.id.show_overview, overviewFrag).commit()
        }
    }

    private fun configureTabLayout() {
        if(!modeTab) show_tab_layout.addTab(show_tab_layout.newTab().setText(getString(R.string.show_tab1)))
        show_tab_layout.addTab(show_tab_layout.newTab().setText(getString(R.string.show_tab2)))
        show_tab_layout.addTab(show_tab_layout.newTab().setText(getString(R.string.show_tab3)))
        show_tab_layout.addTab(show_tab_layout.newTab().setText(getString(R.string.show_tab4)))
        show_tab_layout.addTab(show_tab_layout.newTab().setText(getString(R.string.show_tab5)))

        val adapter =ShowTabPagerAdapter(supportFragmentManager,
                show_tab_layout.tabCount,modeTab,show)
        show_viewpager.adapter = adapter

        show_viewpager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(show_tab_layout))

        show_tab_layout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                show_viewpager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }

    private fun initTrailer(){
        //Trailer
        val image = findViewById<ImageView>(R.id.show_image)
        if(show.imagePath != null) RemoteApiService.getRemoteImage(show.imagePath,this)!!.into(image)

        val videos = show.videos.results
        Log.e("VIDEOS",videos.toString())
        var i =0
        while (i<videos.size && videos[i].type != "Trailer") i++
        if(i==videos.size) return
        if(videos[i].site != "YouTube") btn_play!!.visibility = View.INVISIBLE
        else{
            val URL = RemoteApiService.getYoutubeURL(videos[i].key)
            btn_play!!.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(URL)))
            }
        }

    }

    override fun onCrewSelected(creditId:Int) {
        val intent = Intent(this, PersonActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("personId",creditId)
        intent.putExtra("bundle",bundle)
        startActivity(intent)
    }

    override fun onSeasonSelected(numSeason: String,nbrEpisodes:Int) {
        val intent = Intent(this, SeasonActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("seasonNum",numSeason.toInt())
        bundle.putInt("showId",id)
        bundle.putString("showTitle",show.title)
        bundle.putInt("nbrEpisode",nbrEpisodes)
        bundle.putInt("nbrEpisode",nbrEpisodes)
        bundle.putString("network",show.networks[0].name)
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