package prj.mob1.prjmob1.Util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import prj.mob1.prjmob1.Drawer.DrawerFragment
import prj.mob1.prjmob1.R

/**
 * Created by LE on 17/04/2018.
 */
fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}


fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction{replace(frameId, fragment)}
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun AppCompatActivity.initDrawer()
{
    val toolbar: Toolbar?= findViewById<View>(R.id.toolbar) as Toolbar?
    setSupportActionBar(toolbar)
    supportActionBar!!.setDisplayShowHomeEnabled(true)

    // To get the instance of the fragment
    val drawerFragment:DrawerFragment = supportFragmentManager.findFragmentById(R.id.fragment_navigation_drawer) as DrawerFragment
    drawerFragment!!.setUpDrawer(R.id.fragment_navigation_drawer, findViewById<View>(R.id.drawer_layout) as DrawerLayout, toolbar!!)
}
