package prj.mob1.prjmob1.movie

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import prj.mob1.prjmob1.R

import prj.mob1.prjmob1.databinding.FragmentMovieInfosBinding
//import com.example.sol.fragments.databinding.FragmentBookDetailsBinding

//import java.io.Serializable
//import com.example.sol.fragments.databinding.RecyclerItemBookBinding

/**
 * Created by sol on 25/03/2018.
 */
class MovieInfosFragment: Fragment() {
    companion object {

       /* private const val BOOK = "book"
        fun newInstance(book : Book): BookDetailsFragment {
            val args = Bundle()
            args.putSerializable(BOOK, book as Serializable)
            val fragment = BookDetailsFragment()
            fragment.arguments = args
            return fragment
        }*/
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding : FragmentMovieInfosBinding =
                FragmentMovieInfosBinding.inflate(inflater!! ,container , false)
        var myView : View  = binding.root


        // setting values to model
        val title = getResources().getString(R.string.movie_title)
        val release = getResources().getString(R.string.movie_release_date)
        val tags = getResources().getString(R.string.movie_tags)
        val year = getResources().getString(R.string.movie_year).toInt()
        val duration = getResources().getString(R.string.movie_duration).toInt()
        val movie = MovieClass(title, release,
                year, tags, duration,1,"")
       Log.e("TAG",movie.title)
        binding.movie = movie

        return myView
        //return inflater?.inflate(R.layout.fragment_movie_infos, container, false)
    }

}