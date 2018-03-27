package prj.mob1.prjmob1.movie

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import prj.mob1.prjmob1.CommentsFragment
import prj.mob1.prjmob1.CrewFragment

/**
 * Created by sol on 26/03/2018.
 */
class MovieTabPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> return MovieOverviewFragment()
            1 -> return CrewFragment()
            2 -> return MovieCinemaFragment()
            3 -> return CommentsFragment()
            4 -> return MovieRelatedFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}