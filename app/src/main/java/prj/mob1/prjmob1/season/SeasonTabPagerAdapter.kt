package prj.mob1.prjmob1.season

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import prj.mob1.prjmob1.Comment.CommentsFragment
import prj.mob1.prjmob1.Crew.CrewFragment
import prj.mob1.prjmob1.episode.OverviewFragment

/**
 * Created by Wissem on 27/03/2018.
 */
class SeasonTabPagerAdapter (fm: FragmentManager, private var tabCount: Int,
                             val season : Season, val modeLand: Boolean) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        var pos = position
        if (modeLand) pos++

        when (pos) {
            0 -> return  SeasonOverviewFragment.newInstance(season.overview)
            1 -> return  CrewFragment.newInstance(0,season.credits)
            2 -> return SeasonEpisodesFragment.newInstance(ArrayList(season.episodes))
            3 -> return CommentsFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}