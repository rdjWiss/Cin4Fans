package prj.mob1.prjmob1.episode

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout

import kotlinx.android.synthetic.main.activity_episode.*
import prj.mob1.prjmob1.R

class EpisodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_episode)

        //Fragment infos
        supportFragmentManager.beginTransaction().add(R.id.episode_infos, EpisodeInfosFragment()).commit()

        //Tabs
        configureTabLayout()

    }

    private fun configureTabLayout() {
        episode_tab_layout.addTab(episode_tab_layout.newTab().setText(getString(R.string.episode_tab1)))
        episode_tab_layout.addTab(episode_tab_layout.newTab().setText(getString(R.string.episode_tab2)))

        val adapter = EpisodeTabPagerAdapter(supportFragmentManager,
                episode_tab_layout.tabCount)
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
