package prj.mob1.prjmob1.AllListShow


import android.content.Context
import android.content.Intent
import prj.mob1.prjmob1.ListItem.BaseFragment
import prj.mob1.prjmob1.ListItem.Item
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.movie.MovieActivity
import prj.mob1.prjmob1.show.ShowActivity


/**
 * Created by LE on 17/04/2018.
 */
class AllListShowFragment: BaseFragment()
{
    override fun typeAdpter(): Int {
        return 0
    }
    override fun initItem():Array<Item>
    {
        var item= Item(R.drawable.show_poster
                ,resources.getString(R.string.show_year).toInt(),
                resources.getString(R.string.show_title),
                resources.getString(R.string.show_tags).toString())

        var items= arrayOf(item,item,item,item,item,item,item,item,item,item)
        return items
    }

    override fun openActivity(position: Int) {
        val context: Context = getContext()
        val intent = Intent (context, ShowActivity:: class.java)
        startActivity (intent)
    }
}
