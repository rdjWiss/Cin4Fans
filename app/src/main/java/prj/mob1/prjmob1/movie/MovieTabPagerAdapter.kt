package prj.mob1.prjmob1.movie

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import prj.mob1.prjmob1.Cinema.CinemaFragment
import prj.mob1.prjmob1.Comment.CommentsFragment
import prj.mob1.prjmob1.Comment.ReviewsFragment
import prj.mob1.prjmob1.Crew.CrewFragment
import prj.mob1.prjmob1.ListItem.Item
import prj.mob1.prjmob1.Liste_movies.ListMoviesFragment
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.retrofitUtil.models.SimilarMoviesResponse

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
            2 -> return CinemaFragment()//TODO NOTHING MUCH TODO
            3 -> return ReviewsFragment.newInstance(movie.reviews)
            4 -> return ListMoviesFragment
                    .newInstance(similarMoviesResponseToArrayItem(movie.similar), true)
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

    private fun similarMoviesResponseToArrayItem( similarMovies: SimilarMoviesResponse):ArrayList<Item>{

        val movieArray = ArrayList<Item>()
        for (movie  in similarMovies.results) {
            val item = Item(movie.id,movie.posterId,movie.title)
            movieArray.add(item)
        }
        return movieArray
    }
}