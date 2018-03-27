package prj.mob1.prjmob1.season

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout

import kotlinx.android.synthetic.main.activity_season.*
import prj.mob1.prjmob1.R

class SeasonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_season)

        //Fragment infos
        supportFragmentManager.beginTransaction().add(R.id.season_infos, SeasonInfosFragment()).commit()

        //Tabs
        configureTabLayout()

    }

    private fun configureTabLayout() {
        season_tab_layout.addTab(season_tab_layout.newTab().setText(getString(R.string.season_tab1)))
        season_tab_layout.addTab(season_tab_layout.newTab().setText(getString(R.string.season_tab2)))
        season_tab_layout.addTab(season_tab_layout.newTab().setText(getString(R.string.season_tab3)))
        season_tab_layout.addTab(season_tab_layout.newTab().setText(getString(R.string.season_tab4)))

        val adapter = SeasonTabPagerAdapter(supportFragmentManager,
                season_tab_layout.tabCount)
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
}
