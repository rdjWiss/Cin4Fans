package prj.mob1.prjmob1.season

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_season.*
import prj.mob1.prjmob1.CrewFragment
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.episode.Episode
import prj.mob1.prjmob1.episode.EpisodeActivity

class SeasonActivity : AppCompatActivity(), CrewFragment.OnCrewSelected, SeasonEpisodesFragment.OnEpisodeSelected {

    private var season = Season()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_season)

        val bundle = intent.getBundleExtra("bundle")
        /*TODO: not null*/
//        var season: Season;
        if(bundle != null){
            season  = bundle.getParcelable<Season>("season") as Season
        }else{
            season = Season()
        }
        //Fragment infos
        val infosFragment =
                SeasonInfosFragment.newInstance(season)
        supportFragmentManager.beginTransaction().add(R.id.season_infos, infosFragment).commit()

        //Tabs
        configureTabLayout()

    }

    private fun configureTabLayout() {
        season_tab_layout.addTab(season_tab_layout.newTab().setText(getString(R.string.season_tab1)))
        season_tab_layout.addTab(season_tab_layout.newTab().setText(getString(R.string.season_tab2)))
        season_tab_layout.addTab(season_tab_layout.newTab().setText(getString(R.string.season_tab3)))
        season_tab_layout.addTab(season_tab_layout.newTab().setText(getString(R.string.season_tab4)))

        val adapter = SeasonTabPagerAdapter(supportFragmentManager,
                season_tab_layout.tabCount,season)
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
        Toast.makeText(this, "Click detected on item $name",
                Toast.LENGTH_LONG).show()
    }

    override fun onEpisodeSelected(episode : Episode) {
        /*Toast.makeText(this, "Click detected on item ${episode.episode_title}",
                Toast.LENGTH_SHORT).show()*/

        val intent = Intent(this, EpisodeActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable("episode",episode)
        intent.putExtra("bundle",bundle)
        startActivity(intent)

    }
}
