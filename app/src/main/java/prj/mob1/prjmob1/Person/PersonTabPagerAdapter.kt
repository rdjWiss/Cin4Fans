package prj.mob1.prjmob1.Person

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import prj.mob1.prjmob1.Comment.CommentsFragment
import prj.mob1.prjmob1.ListItem.Item
import prj.mob1.prjmob1.Liste_movies.ListMoviesFragment
import prj.mob1.prjmob1.Liste_shows.ListShowFragment
import prj.mob1.prjmob1.retrofitUtil.models.PersonMovieCredits
import prj.mob1.prjmob1.retrofitUtil.models.PersonTVCredits

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
            1 -> return ListMoviesFragment
                    .newInstance(movieCreditsToArrayMovie(person.movieCredits),true)
            2 -> return ListShowFragment.newInstance(tvCreditsToArrayMovie(person.tvCredits),true)
            3 -> return CommentsFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

    private fun movieCreditsToArrayMovie(movieCredits: PersonMovieCredits):ArrayList<Item>{
        val movieArray = ArrayList<Item>()
        for (movie  in movieCredits.cast) {
            val item = Item(movie.movieId,movie.poster,movie.title)
            movieArray.add(item)
        }
        return movieArray

    }

    private fun tvCreditsToArrayMovie(tvCredits: PersonTVCredits):ArrayList<Item>{
        val showArray = ArrayList<Item>()
        for (show  in tvCredits.cast) {
            val item = Item(show.showId,show.poster,show.title)
            showArray.add(item)
        }
        return showArray

    }


}