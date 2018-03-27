package prj.mob1.prjmob1.episode

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.databinding.FragmentEpisodeOverviewBinding


class EpisodeOverviewFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

//        return inflater!!.inflate(R.layout.fragment_movie_overview, container, false)
        var binding : FragmentEpisodeOverviewBinding =
                FragmentEpisodeOverviewBinding .inflate(inflater!! ,container , false)
        var myView : View  = binding.root


        // setting values to model
        val overview = getString(R.string.season_overview)

        var episode = Episode()
        episode.overview = overview
        Log.e("TAG",episode.num_season)
        binding.episode = episode

        return myView
    }
}// Required empty public constructor
