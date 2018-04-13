package prj.mob1.prjmob1.episode

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.databinding.FragmentEpisodeInfosBinding
import prj.mob1.prjmob1.season.Season


class EpisodeInfosFragment : Fragment() {

    private var episode: Episode? = null

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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding : FragmentEpisodeInfosBinding =
                FragmentEpisodeInfosBinding.inflate(inflater!! ,container , false)
        var myView : View  = binding.root


        var episode = arguments.getParcelable<Episode>(ARG_Episode) as Episode
        // setting values to model
       /* val episodeTitle = getString(R.string.episode_title)
        val numEpisode = getString(R.string.episode_num)
        val numSeason = getString(R.string.episode_num_season)
        val title = getString(R.string.episode_title_show)
        val date = getString(R.string.episode_date)
        val channel = getString(R.string.episode_channel)
        val episode = Episode(episodeTitle,numEpisode,numSeason,title,date,channel,1,"")
        Log.e("TAG",episode.title_show)*/
        binding.episode = episode

        return myView
        //return inflater?.inflate(R.layout.fragment_movie_infos, container, false)
    }
}// Required empty public constructor
