package prj.mob1.prjmob1.Home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import prj.mob1.prjmob1.Liste_movies.ListMoviesFragment
import prj.mob1.prjmob1.Liste_shows.ListShowFragment

/**
 * Created by LE on 04/04/2018.
 */
class HomeTabPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> return ListMoviesFragment()
            1 -> return ListShowFragment()
            else -> return null
        }

    }

    override fun getCount(): Int {
        return tabCount
    }
}
