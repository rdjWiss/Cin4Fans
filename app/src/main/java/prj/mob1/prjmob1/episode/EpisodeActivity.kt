package prj.mob1.prjmob1.episode

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.RatingBar
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_episode.*
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.rating.OnRateClick
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService

class EpisodeActivity : AppCompatActivity(), OnRateClick {

    private var showId = 1
    private var seasonNum = 1
    private var episodeNum = 1
    private var showTitle = ""
    private var network = ""

    private var episode: Episode = Episode()
    private var modeTab = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_episode)
        if (resources.getString(R.string.isLand) == "true") modeTab = true

        val bundle = intent.getBundleExtra("bundle")
        /*TODO: not null*/
        if(bundle != null){
            showId = bundle.getInt("showId")
            seasonNum = bundle.getInt("seasonNum")
            episodeNum = bundle.getInt("episodeNum")
            showTitle = bundle.getString("showTitle")
            network = bundle.getString("network")
        }else{
            //TODO Show error and finish activity
        }

        //Back arrow
        back_arrow.setOnClickListener{
            finish()
        }

        getEpisodeData()

    }

    private fun getEpisodeData(){

        val apiService: RemoteApiService? = RemoteApiService.create()
        apiService!!.getEpisodeInfosById(showId, seasonNum,episodeNum)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    result ->
//                    Toast.makeText(this,"Response ${result}", Toast.LENGTH_LONG).show()
                    Log.e("EPSIODE",result.toString())
                    episode = result
                    episode.title_show = showTitle
                    episode.channel = network

                    initEpisodeInfosFrag()
                    initOverviewFragTabMode()
                    configureTabLayout()
                }, { error ->
                    Toast.makeText(this,"Error ${error.message}", Toast.LENGTH_LONG).show()
                    error.printStackTrace()

                })

    }

    private fun initEpisodeInfosFrag(){
        //Fragment infos
        val infosFragment =
                EpisodeInfosFragment.newInstance(episode)
        supportFragmentManager.beginTransaction().add(R.id.episode_infos, infosFragment).commit()
    }

    private fun initOverviewFragTabMode(){
        //Overview fragment for tablet
        if (modeTab){
            val overviewFrag = OverviewFragment.newInstance(episode.overview)
            supportFragmentManager.beginTransaction()
                    .add(R.id.episode_overview, overviewFrag).commit()
        }

    }

    private fun configureTabLayout() {
        if(!modeTab) episode_tab_layout.addTab(episode_tab_layout.newTab().setText(getString(R.string.episode_tab1)))
        episode_tab_layout.addTab(episode_tab_layout.newTab().setText(getString(R.string.episode_tab2)))


        val adapter = EpisodeTabPagerAdapter(supportFragmentManager,
                episode_tab_layout.tabCount, episode,modeTab)
        episode_viewpager.adapter = adapter

        episode_viewpager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(episode_tab_layout))

        episode_tab_layout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                episode_viewpager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
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
                .setNegativeButton("Annuler") { _, _ ->
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
