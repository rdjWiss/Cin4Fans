package prj.mob1.prjmob1.movie

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import prj.mob1.prjmob1.CommentsFragment
import prj.mob1.prjmob1.CrewFragment
import prj.mob1.prjmob1.Liste_movies.ListMoviesFragment

/**
 * Created by sol on 26/03/2018.
 */
class MovieTabPagerAdapter(fm: FragmentManager, private var tabCount: Int, val modeLand: Boolean) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        var pos = position
        if (modeLand) pos++

        when (pos) {
            0 -> return MovieOverviewFragment()
            1 -> {
                val bundle = Bundle()
                bundle.putInt("TypeCrew", 0)
                val frag = CrewFragment()
                frag.arguments = bundle
                return frag
            }
            2 -> return MovieCinemaFragment()
            3 -> return CommentsFragment()
            4 -> return ListMoviesFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}