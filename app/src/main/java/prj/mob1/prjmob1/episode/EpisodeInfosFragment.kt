package prj.mob1.prjmob1.episode

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.databinding.FragmentEpisodeInfosBinding


class EpisodeInfosFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding : FragmentEpisodeInfosBinding =
                FragmentEpisodeInfosBinding.inflate(inflater!! ,container , false)
        var myView : View  = binding.root


        // setting values to model
        val numEpisode = getString(R.string.episode_num)
        val numSeason = getString(R.string.episode_num_season)
        val title = getString(R.string.episode_title_show)
        val date = getString(R.string.episode_date)
        val channel = getString(R.string.episode_channel)
        val episode = Episode(numEpisode,numSeason,title,date,channel,1,"")
        Log.e("TAG",episode.title_show)
        binding.episode = episode

        return myView
        //return inflater?.inflate(R.layout.fragment_movie_infos, container, false)
    }
}// Required empty public constructor
