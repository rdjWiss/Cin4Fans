package prj.mob1.prjmob1.movie

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import prj.mob1.prjmob1.Cinema.CinemaFragment
import prj.mob1.prjmob1.Comment.CommentsFragment
import prj.mob1.prjmob1.Comment.ReviewsFragment
import prj.mob1.prjmob1.Crew.CrewFragment
import prj.mob1.prjmob1.Liste_movies.ListMoviesFragment
import prj.mob1.prjmob1.R

/**
 * Created by sol on 26/03/2018.
 */
class MovieTabPagerAdapter(fm: FragmentManager, private var tabCount: Int, val modeLand: Boolean,
                          val id:Int,val movie:MovieClass) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        var pos = position
        if (modeLand) pos++

        when (pos) {
            0 -> return MovieOverviewFragment.newInstance(movie.overview)
            1 -> return CrewFragment.newInstance(0,movie.credits)
            2 -> return CinemaFragment()
            3 -> return ReviewsFragment.newInstance(movie.reviews)
            4 -> return ListMoviesFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}