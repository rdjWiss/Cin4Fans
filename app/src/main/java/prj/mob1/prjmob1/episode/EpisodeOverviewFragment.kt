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

    private var overview: String? = null

    companion object {

        private val ARG_Overview = "overview"

        fun newInstance(overview: String): EpisodeOverviewFragment {
            val fragment = EpisodeOverviewFragment()
            val args = Bundle()
            args.putString(ARG_Overview, overview)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            overview = arguments.getString(ARG_Overview)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

//        return inflater!!.inflate(R.layout.fragment_movie_overview, container, false)
        var binding : FragmentEpisodeOverviewBinding =
                FragmentEpisodeOverviewBinding .inflate(inflater!! ,container , false)
        var myView : View  = binding.root


        var overview = ""
                overview = arguments.getString(ARG_Overview)
        // setting values to model
        //val overview = getString(R.string.episode_overview)

        var episode = Episode()
        episode.overview = overview
        Log.e("TAG",episode.num_season)
        binding.episode = episode

        return myView
    }
}// Required empty public constructor
