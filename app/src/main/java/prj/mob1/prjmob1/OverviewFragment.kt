package prj.mob1.prjmob1.episode

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.databinding.FragmentEpisodeOverviewBinding


class OverviewFragment : Fragment() {

    private var overview: String? = null

    companion object {

        private val ARG_Overview = "overview"

        fun newInstance(overview: String): OverviewFragment {
            val fragment = OverviewFragment()
            val args = Bundle()
            args.putString(ARG_Overview, overview)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            overview = arguments!!.getString(ARG_Overview)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater!!.inflate(R.layout.fragment_overview, container, false)


        var overview = ""
        overview = arguments!!.getString(ARG_Overview)

        Log.e("TAG",overview)
        val overviewView: TextView = view.findViewById<TextView>(R.id.all_overview) as TextView
        overviewView.text = overview

        return view
    }
}// Required empty public constructor
