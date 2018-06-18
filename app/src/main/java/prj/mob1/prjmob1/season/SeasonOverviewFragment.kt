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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentSeasonOverviewBinding =
                FragmentSeasonOverviewBinding.inflate(inflater!! ,container , false)
        val myView : View  = binding.root

        val overview = getResources().getString(R.string.season_overview)

        val season = Season()
        season.overview = overview
        Log.e("TAG",season.num_season)
        binding.season = season

        return myView
    }

}// Required empty public constructor
