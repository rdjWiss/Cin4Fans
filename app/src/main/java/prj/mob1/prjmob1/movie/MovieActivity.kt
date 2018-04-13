package prj.mob1.prjmob1.movie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Window
import prj.mob1.prjmob1.R
import android.support.v4.view.ViewPager
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_movie.*
import prj.mob1.prjmob1.CrewFragment

class MovieActivity : AppCompatActivity(), CrewFragment.OnCrewSelected {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie)

        //Fragment infos
        supportFragmentManager.beginTransaction().add(R.id.movie_infos, MovieInfosFragment()).commit()

        //Tabs
        configureTabLayout()

    }

    private fun configureTabLayout() {
        //movie_tab_layout.setTabTextColors(getColor(R.color.color_tabs_indicator);)
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Overview"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Crew"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Cinemas"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Comments"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Related"))

        val adapter = MovieTabPagerAdapter(supportFragmentManager,
                movie_tab_layout.tabCount)
        movie_viewpager.adapter = adapter

        movie_viewpager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(movie_tab_layout))

        movie_tab_layout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                movie_viewpager.currentItem = tab.position
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
}
