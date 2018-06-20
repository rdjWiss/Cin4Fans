package prj.mob1.prjmob1.season

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.databinding.FragmentSeasonInfosBinding
import prj.mob1.prjmob1.rating.OnRateClick

class SeasonInfosFragment : Fragment() {

    private var season: Season? = null
    private lateinit var listener: OnRateClick

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
            season = arguments!!.getParcelable(ARG_Season)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnRateClick) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnRateClick.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentSeasonInfosBinding =
                FragmentSeasonInfosBinding.inflate(inflater!! ,container , false)
        val myView : View  = binding.root

        //Rate listner
        myView.findViewById<ImageView>(R.id.season_infos_rate).setOnClickListener{
            listener.onRateClick()
        }

        val season = arguments!!.getParcelable<Season>(ARG_Season) as Season
        //myView.findViewById<ImageView>(R.id.season_infos_poster).setImageResource(season.posterId)
        binding.season = season

        return myView
    }
}// Required empty public constructor
