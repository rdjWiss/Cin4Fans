package prj.mob1.prjmob1.MyMovies

import android.content.Context
import android.content.Intent
import prj.mob1.prjmob1.ListItem.BaseFragment
import prj.mob1.prjmob1.ListItem.Item
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.movie.MovieActivity


/**
 * Created by LE on 18/04/2018.
 */

class  MyMoviesFragment: BaseFragment()

{
    override fun typeAdpter(): Int {
        return 1
    }

    override fun initItem():ArrayList<Item>
    {
        var item= Item(200,"")
        var items= arrayListOf(item,item,item,item,item,item,item,item,item,item)
        return items
    }
    override fun openActivity(position: Int) {
        val context: Context? = getContext()
        val intent = Intent (context, MovieActivity:: class.java)
        startActivity (intent)
    }
}