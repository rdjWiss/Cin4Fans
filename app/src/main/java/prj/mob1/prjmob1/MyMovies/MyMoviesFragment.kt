package prj.mob1.prjmob1.MyMovies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import prj.mob1.prjmob1.ListItem.BaseFragment
import prj.mob1.prjmob1.ListItem.Item
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.movie.MovieActivity
import prj.mob1.prjmob1.movie.MovieClass


/**
 * Created by LE on 18/04/2018.
 */

class  MyMoviesFragment: BaseFragment()

{
    override fun typeAdpter(): Int {
        return 1
    }

    private lateinit var movieList:ArrayList<Item>

    companion object {

        private val ARG_LIST = "list"

        fun newInstance(movies: ArrayList<Item>): MyMoviesFragment {
            val fragment = MyMoviesFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_LIST, movies)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            movieList = arguments!!.getParcelableArrayList(ARG_LIST)
        }
    }

    override fun initItem():ArrayList<Item>
    {
//        val item= Item(200,"","")
//        val items= arrayListOf(item,item,item,item,item,item,item,item,item,item)
        return movieList
    }
    override fun openActivity(position: Int) {
        val intent = Intent(context, MovieActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("id",movieList[position].id)
        bundle.putString("mode","Offline")
        intent.putExtra("bundle",bundle)
        startActivity(intent)
    }

}