package prj.mob1.prjmob1.show

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import prj.mob1.prjmob1.Comment.CommentsFragment
import prj.mob1.prjmob1.Crew.CrewFragment
import prj.mob1.prjmob1.Liste_shows.ListShowFragment

/**
 * Created by sol on 26/03/2018.
 */
class ShowTabPagerAdapter (fm: FragmentManager, private var tabCount: Int, val modeLand: Boolean) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        var pos = position
        if (modeLand) pos++
        when (pos) {
            0 -> return ShowOverviewFragment()
            1 -> {
                val bundle = Bundle()
                bundle.putInt("TypeCrew", 1)
                val frag = CrewFragment()
                frag.arguments = bundle
                return frag
            }
            2 -> return ShowSeasonsFragment()
            3 -> return CommentsFragment()
            4 -> return ListShowFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}