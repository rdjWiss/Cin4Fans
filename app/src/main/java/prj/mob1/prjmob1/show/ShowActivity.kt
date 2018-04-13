package prj.mob1.prjmob1.show

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_show.*
import prj.mob1.prjmob1.CrewFragment
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.season.Season
import prj.mob1.prjmob1.season.SeasonActivity

class ShowActivity : AppCompatActivity(), CrewFragment.OnCrewSelected, ShowSeasonsFragment.OnSeasonSelected {

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

    override fun onCrewSelected(name: String) {
        Toast.makeText(this, "Click detected on item $name",
                Toast.LENGTH_LONG).show()
    }

    override fun onSeasonSelected(season: Season) {
        /*Toast.makeText(this, "Click detected on item ${season.num_season}",
                Toast.LENGTH_LONG).show()
        Toast.makeText(this, "Click detected on item ${season.nbr_episodes}",
                Toast.LENGTH_LONG).show()
        Toast.makeText(this, "Click detected on item ${season.overview}",
                Toast.LENGTH_LONG).show()
        Toast.makeText(this, "Click detected on item ${season.title_show}",
                Toast.LENGTH_LONG).show()*/

        val intent = Intent(this, SeasonActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable("season",season)
        intent.putExtra("bundle",bundle)
        startActivity(intent)

    }
}