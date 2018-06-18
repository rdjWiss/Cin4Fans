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

    private lateinit var overview: String

    companion object {

        private val ARG_overview = "Overview"

        fun newInstance(overview:String): MovieOverviewFragment {
            val fragment = MovieOverviewFragment()
            val args = Bundle()
            args.putString(ARG_overview, overview)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            overview = arguments!!.getString(ARG_overview)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentMovieOverviewBinding =
                FragmentMovieOverviewBinding.inflate(inflater ,container , false)
        val myView : View  = binding.root

        //if(this.overview==null) overview = getResources().getString(R.string.movie_overview)

        val movie = MovieClass()
        movie.overview = overview
        Log.e("TAG",movie.title)
        binding.movie = movie

        return myView
    }

}// Required empty public constructor
