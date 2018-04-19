package prj.mob1.prjmob1.season

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import prj.mob1.prjmob1.CommentsFragment
import prj.mob1.prjmob1.CrewFragment
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
            0 -> {
                var bundle = Bundle()
                Log.e("TAG", season.overview)
                bundle.putString("overview", season.overview)
                var frag = OverviewFragment()
                frag.arguments = bundle
                return frag
            }
            1 -> {
                var bundle = Bundle()
                bundle.putInt("TypeCrew", 1)
                var frag = CrewFragment()
                frag.arguments = bundle
                return frag
            }
            2 -> return SeasonEpisodesFragment()
            3 -> return CommentsFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}