package prj.mob1.prjmob1.episode

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.databinding.FragmentEpisodeInfosBinding
import prj.mob1.prjmob1.rating.OnRateClick


class EpisodeInfosFragment : Fragment() {

    private var episode: Episode? = null
    private lateinit var listener: OnRateClick

    companion object {

        private val ARG_Episode = "Episode"

        fun newInstance(episode: Episode): EpisodeInfosFragment {
            val fragment = EpisodeInfosFragment()
            val args = Bundle()
            args.putParcelable(ARG_Episode, episode)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            episode = arguments.getParcelable(ARG_Episode)
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentEpisodeInfosBinding =
                FragmentEpisodeInfosBinding.inflate(inflater!! ,container , false)
        val myView : View  = binding.root

        //Set rating listener
        myView.findViewById<ImageView>(R.id.episode_infos_rate).setOnClickListener{
            listener.onRateClick()
        }

        val episode = arguments.getParcelable<Episode>(ARG_Episode) as Episode
        binding.episode = episode

        return myView
    }
}
