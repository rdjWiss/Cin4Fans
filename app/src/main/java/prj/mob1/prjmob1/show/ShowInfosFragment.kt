package prj.mob1.prjmob1.show

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.databinding.FragmentMovieInfosBinding
import prj.mob1.prjmob1.databinding.FragmentShowInfosBinding
import prj.mob1.prjmob1.movie.MovieClass


class ShowInfosFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding : FragmentShowInfosBinding =
                FragmentShowInfosBinding.inflate(inflater!! ,container , false)
        var myView : View  = binding.root


        // setting values to model
        val title:String = getString(R.string.show_title)
        val episodes = getString(R.string.show_episodes).toInt()
        val tags = getString(R.string.show_tags)
        val duration = getString(R.string.show_duration).toInt()
        val show = TVShow(title, episodes ,
                tags, duration,1,"")
        Log.e("TAG",show.title)
        binding.show = show

        return myView
        //return inflater?.inflate(R.layout.fragment_movie_infos, container, false)
    }
}// Required empty public constructor
