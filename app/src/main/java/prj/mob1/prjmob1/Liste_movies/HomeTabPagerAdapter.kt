package prj.mob1.prjmob1.Liste_movies

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log

/**
 * Created by LE on 04/04/2018.
 */
class HomeTabPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {

       /* when (position) {
            0 -> return ListMoviesFragment()
        // 1 -> return CrewFragment()
            else -> return null
        }*/
        return ListMoviesFragment()
    }

    override fun getCount(): Int {
        return tabCount
    }
}
