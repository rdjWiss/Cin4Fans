package prj.mob1.prjmob1.season

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.databinding.FragmentSeasonInfosBinding
import prj.mob1.prjmob1.season.Season

class SeasonInfosFragment : Fragment() {

    private var season: Season? = null

    companion object {

        private val ARG_Season = "Season"

        fun newInstance(season: Season): SeasonInfosFragment {
            val fragment = SeasonInfosFragment()
            val args = Bundle()
            args.putParcelable(ARG_Season, season)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            season = arguments.getParcelable(ARG_Season)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding : FragmentSeasonInfosBinding =
                FragmentSeasonInfosBinding.inflate(inflater!! ,container , false)
        var myView : View  = binding.root


        var season = arguments.getParcelable<Season>(ARG_Season) as Season
        // setting values to model
        /*val numSeason = getString(R.string.season_num)
        val title:String = getString(R.string.season_title_show)
        val episodes = getString(R.string.season_episodes).toInt()
        val dateBegin = getString(R.string.season_date_begin)
        val dateEnd = getString(R.string.season_date_end)
        val season = Season(numSeason,title, episodes ,
                dateBegin,dateEnd,1,"")
        Log.e("TAG",season.title_show)*/
        binding.season = season

        return myView
    }
}// Required empty public constructor
