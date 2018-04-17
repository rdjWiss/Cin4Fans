package prj.mob1.prjmob1.AllListShow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.View

import prj.mob1.prjmob1.Drawer.DrawerFragment
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.Util.addFragment

class AllListShowActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var drawerFragment: DrawerFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_list_show)

        toolbar = findViewById<View>(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        // To get the instance of the fragment
        drawerFragment = supportFragmentManager.findFragmentById(R.id.fragment_navigation_drawer) as DrawerFragment
        drawerFragment!!.setUpDrawer(R.id.fragment_navigation_drawer, findViewById<View>(R.id.drawer_layout) as DrawerLayout, toolbar!!)


        addFragment(AllListShowFragment(),R.id.container_body_all_show)

    }
}
