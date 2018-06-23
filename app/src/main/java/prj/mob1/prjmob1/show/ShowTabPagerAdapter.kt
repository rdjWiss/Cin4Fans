package prj.mob1.prjmob1.show

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import prj.mob1.prjmob1.Comment.CommentsFragment
import prj.mob1.prjmob1.Comment.ReviewsFragment
import prj.mob1.prjmob1.Crew.CrewFragment
import prj.mob1.prjmob1.ListItem.Item
import prj.mob1.prjmob1.Liste_shows.ListShowFragment
import prj.mob1.prjmob1.retrofitUtil.models.SimilarShowsResponse

/**
 * Created by sol on 26/03/2018.
 */
class ShowTabPagerAdapter (fm: FragmentManager, private var tabCount: Int, val modeLand: Boolean, val show:TVShow) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        var pos = position
        if (modeLand) pos++
        when (pos) {
            0 -> return ShowOverviewFragment.newInstance(show.overview)
            1 -> return CrewFragment.newInstance(0,show.credits)
            2 -> return ShowSeasonsFragment.newInstance(ArrayList(show.seasons))//return ShowSeasonsFragment()
            3 -> return ReviewsFragment.newInstance(show.reviews)
            4 -> return ListShowFragment
                    .newInstance(similarMoviesResponseToArrayItem(show.similar), true)
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

    private fun similarMoviesResponseToArrayItem( similarShows: SimilarShowsResponse):ArrayList<Item>{

        val showArray = ArrayList<Item>()
        for (show  in similarShows.results) {
            val item = Item(show.id,show.posterId,show.title)
            showArray.add(item)
        }
        return showArray
    }
}