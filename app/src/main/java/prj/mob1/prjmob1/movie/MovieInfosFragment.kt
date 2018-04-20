package prj.mob1.prjmob1.movie

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_movie_infos.*
import prj.mob1.prjmob1.R

import prj.mob1.prjmob1.databinding.FragmentMovieInfosBinding
import prj.mob1.prjmob1.rating.OnRateClick

/**
 * Created by sol on 25/03/2018.
 */
class MovieInfosFragment: Fragment() {

    private lateinit var listener: OnRateClick

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

        val binding : FragmentMovieInfosBinding =
                FragmentMovieInfosBinding.inflate(inflater!! ,container , false)
        val myView : View  = binding.root


        // setting values to model
        val title = getResources().getString(R.string.movie_title)
        val release = getResources().getString(R.string.movie_release_date)
        val tags = getResources().getString(R.string.movie_tags)
        val year = getResources().getString(R.string.movie_year).toInt()
        val duration = getResources().getString(R.string.movie_duration).toInt()
        val movie = MovieClass(title, release,
                year, tags, duration,1,"")

        val indice = 1
        val poster = resources.obtainTypedArray(R.array.movie_images).getResourceId(indice,0)
        myView.findViewById<ImageView>(R.id.movie_infos_poster).setImageResource(poster)

        Log.e("TAG",movie.title)
        binding.movie = movie

        //Ajout du lister de rate
        myView.findViewById<ImageView>(R.id.movie_infos_rate).setOnClickListener{
            listener.onRateClick()
        }

        //Ajout du listner de bookmark
        myView.findViewById<ImageView>(R.id.movie_infos_bookmark1).setOnClickListener{
            Snackbar.make(myView,"Added to favorites",Snackbar.LENGTH_SHORT).show()
            movie_infos_bookmark2.visibility = View.VISIBLE
            movie_infos_bookmark1.visibility = View.INVISIBLE
        }

        myView.findViewById<ImageView>(R.id.movie_infos_bookmark2).setOnClickListener{
            Snackbar.make(myView,"Removed from favorites",Snackbar.LENGTH_SHORT).show()
            movie_infos_bookmark1.visibility = View.VISIBLE
            movie_infos_bookmark2.visibility = View.INVISIBLE
        }

        return myView
    }

}