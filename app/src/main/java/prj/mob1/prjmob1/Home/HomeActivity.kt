package prj.mob1.prjmob1.Home
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.View
import kotlinx.android.synthetic.main.movie_tab.*
import prj.mob1.prjmob1.AllListShow.AllListShowFragment
import prj.mob1.prjmob1.Drawer.DrawerFragment
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.Util.addFragment


class HomeActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var drawerFragment: DrawerFragment? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar = findViewById<View>(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        // To get the instance of the fragment
        drawerFragment = supportFragmentManager.findFragmentById(R.id.fragment_navigation_drawer) as DrawerFragment
        drawerFragment!!.setUpDrawer(R.id.fragment_navigation_drawer, findViewById<View>(R.id.drawer_layout) as DrawerLayout, toolbar!!)


        // The tab
        configureTabLayout()



    }


    private fun configureTabLayout() {

        mv_tab_layout.addTab(mv_tab_layout.newTab().setText("Movies Playing"))
        mv_tab_layout.addTab(mv_tab_layout.newTab().setText("TV Shows airing"))

        val adapter = HomeTabPagerAdapter(supportFragmentManager,
                mv_tab_layout.tabCount)

        mv_viewpager.adapter = adapter

       mv_viewpager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(mv_tab_layout))


        mv_tab_layout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
               mv_viewpager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }


}

