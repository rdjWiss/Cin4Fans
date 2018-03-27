package prj.mob1.prjmob1.season

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import prj.mob1.prjmob1.CommentsFragment
import prj.mob1.prjmob1.CrewFragment

/**
 * Created by Wissem on 27/03/2018.
 */
class SeasonTabPagerAdapter (fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> return SeasonOverviewFragment()
            1 -> return CrewFragment()
            2 -> return SeasonEpisodesFragment()
            3 -> return CommentsFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}