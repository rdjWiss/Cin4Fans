package prj.mob1.prjmob1.movie

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.databinding.FragmentMovieOverviewBinding


class MovieOverviewFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentMovieOverviewBinding =
                FragmentMovieOverviewBinding.inflate(inflater!! ,container , false)
        val myView : View  = binding.root

        val overview = getResources().getString(R.string.movie_overview)

        val movie = MovieClass()
        movie.overview = overview
        Log.e("TAG",movie.title)
        binding.movie = movie

        return myView
    }

}// Required empty public constructor
