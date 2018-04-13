package prj.mob1.prjmob1.show

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import prj.mob1.prjmob1.CommentsFragment
import prj.mob1.prjmob1.CrewFragment
import prj.mob1.prjmob1.movie.*

/**
 * Created by sol on 26/03/2018.
 */
class ShowTabPagerAdapter (fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> return ShowOverviewFragment()
            1 -> {
                var bundle = Bundle()
                bundle.putInt("TypeCrew", 1)
                var frag = CrewFragment()
                frag.arguments = bundle
                return frag
            }
            2 -> return ShowSeasonsFragment()
            3 -> return CommentsFragment()
            4 -> return MovieRelatedFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}