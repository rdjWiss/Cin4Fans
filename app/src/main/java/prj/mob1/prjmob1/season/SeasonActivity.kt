package prj.mob1.prjmob1.season

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AlertDialog
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_season.*
import prj.mob1.prjmob1.Crew.CrewFragment
import prj.mob1.prjmob1.Person.PersonActivity
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.episode.Episode
import prj.mob1.prjmob1.episode.EpisodeActivity
import prj.mob1.prjmob1.rating.OnRateClick

class SeasonActivity : AppCompatActivity(), CrewFragment.OnCrewSelected,
        SeasonEpisodesFragment.OnEpisodeSelected, OnRateClick {

    private var season = Season()
    private var modeTab = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_season)
        if (resources.getString(R.string.isLand) == "true") modeTab = true

        back_arrow.setOnClickListener{
            finish()
        }

        val bundle = intent.getBundleExtra("bundle")
        /*TODO: not null*/
        if(bundle != null){
            season  = bundle.getParcelable<Season>("season") as Season
        }else{
            season = Season()
        }

        //Set image top
        findViewById<ImageView>(R.id.season_trailer).setImageResource(season.imageId)

        //Fragment infos
        val infosFragment =
                SeasonInfosFragment.newInstance(season)
        supportFragmentManager.beginTransaction().add(R.id.season_infos, infosFragment).commit()

        if (modeTab){
            supportFragmentManager.beginTransaction()
                    .add(R.id.season_overview, SeasonOverviewFragment()).commit()
        }

        //Tabs
        configureTabLayout()

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

    override fun onCrewSelected(name: String) {
        val intent = Intent(this, PersonActivity::class.java)
        val bundle = Bundle()
        bundle.putString("personName",name)
        intent.putExtra("bundle",bundle)
        startActivity(intent)
    }

    override fun onEpisodeSelected(episode : Episode) {
        val intent = Intent(this, EpisodeActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable("episode",episode)
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
}
