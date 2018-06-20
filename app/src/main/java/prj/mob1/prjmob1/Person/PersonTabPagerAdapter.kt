package prj.mob1.prjmob1.Person

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import prj.mob1.prjmob1.Comment.CommentsFragment
import prj.mob1.prjmob1.Liste_movies.ListMoviesFragment
import prj.mob1.prjmob1.Liste_shows.ListShowFragment

/**
 * Created by sol on 16/04/2018.
 */
class PersonTabPagerAdapter (fm: FragmentManager, private var tabCount: Int,
                             val person:Person, val modeLand: Boolean) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        var pos = position
        if (modeLand) pos++

        when (pos) {
            0 -> return PersonOverviewFragment.newInstance(person.biography, person.birthday, person.origin)
            1 -> return ListMoviesFragment()
            2 -> return ListShowFragment()
            3 -> return CommentsFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}