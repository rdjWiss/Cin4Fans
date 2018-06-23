package prj.mob1.prjmob1.season

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.databinding.FragmentSeasonOverviewBinding


class SeasonOverviewFragment : Fragment() {

    private lateinit var overview: String

    companion object {

        private val ARG_OVERVIEW = "overview"

        fun newInstance(overview: String): SeasonOverviewFragment {
            val fragment = SeasonOverviewFragment()
            val args = Bundle()
            args.putString(ARG_OVERVIEW, overview)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            overview = arguments!!.getString(ARG_OVERVIEW)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentSeasonOverviewBinding =
                FragmentSeasonOverviewBinding.inflate(inflater ,container , false)
        val myView : View  = binding.root

//        val overview = getResources().getString(R.string.season_overview)

        val season = Season()
        season.overview = overview
        binding.season = season

        return myView
    }

}// Required empty public constructor
