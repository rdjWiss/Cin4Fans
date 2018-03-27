package prj.mob1.prjmob1.show

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout

import kotlinx.android.synthetic.main.activity_show.*
import prj.mob1.prjmob1.R

class ShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_show)

        //Fragment infos
        supportFragmentManager.beginTransaction().add(R.id.show_infos, ShowInfosFragment()).commit()

        //Tabs
        configureTabLayout()

    }

    private fun configureTabLayout() {
        show_tab_layout.addTab(show_tab_layout.newTab().setText(getString(R.string.show_tab1)))
        show_tab_layout.addTab(show_tab_layout.newTab().setText(getString(R.string.show_tab2)))
        show_tab_layout.addTab(show_tab_layout.newTab().setText(getString(R.string.show_tab3)))
        show_tab_layout.addTab(show_tab_layout.newTab().setText(getString(R.string.show_tab4)))
        show_tab_layout.addTab(show_tab_layout.newTab().setText(getString(R.string.show_tab5)))

        val adapter =ShowTabPagerAdapter(supportFragmentManager,
                show_tab_layout.tabCount)
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
}