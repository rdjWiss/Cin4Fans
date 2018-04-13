package prj.mob1.prjmob1.episode

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import android.widget.Toast
import prj.mob1.prjmob1.CommentsFragment

/**
 * Created by sol on 27/03/2018.
 */
class EpisodeTabPagerAdapter (fm: FragmentManager, private var tabCount: Int, val episode: Episode) :
        FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> {
                var bundle = Bundle()
                Log.e("TAG", episode.overview)
                bundle.putString("overview", episode.overview)
                //var frag = EpisodeOverviewFragment()
                var frag = OverviewFragment()
                frag.arguments = bundle
                return frag
            }
            1 -> return CommentsFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}