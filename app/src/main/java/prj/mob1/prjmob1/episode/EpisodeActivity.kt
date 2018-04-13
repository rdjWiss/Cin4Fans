package prj.mob1.prjmob1.episode

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_episode.*
import prj.mob1.prjmob1.R

class EpisodeActivity : AppCompatActivity() {

    private var episode: Episode = Episode()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_episode)

        val bundle = intent.getBundleExtra("bundle")
        /*TODO: not null*/
        //var episode: Episode;
        if(bundle != null){
            episode  = bundle.getParcelable<Episode>("episode") as Episode
        }else{
            episode= Episode()
        }
        //Fragment infos
        val infosFragment =
                EpisodeInfosFragment.newInstance(episode)
        supportFragmentManager.beginTransaction().add(R.id.episode_infos, infosFragment).commit()
        //Fragment infos
        ///supportFragmentManager.beginTransaction().add(R.id.episode_infos, EpisodeInfosFragment()).commit()

        //Tabs
        configureTabLayout()

    }

    private fun configureTabLayout() {
        episode_tab_layout.addTab(episode_tab_layout.newTab().setText(getString(R.string.episode_tab1)))
        episode_tab_layout.addTab(episode_tab_layout.newTab().setText(getString(R.string.episode_tab2)))

        /*Toast.makeText(this, "Click detected on item ${episode.overview}",
                Toast.LENGTH_SHORT).show()*/

        val adapter = EpisodeTabPagerAdapter(supportFragmentManager,
                episode_tab_layout.tabCount, episode)
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
}
