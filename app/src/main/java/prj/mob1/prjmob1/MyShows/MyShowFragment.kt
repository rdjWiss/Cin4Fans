package prj.mob1.prjmob1.MyShows

import android.content.Context
import android.content.Intent
import prj.mob1.prjmob1.ListItem.BaseFragment
import prj.mob1.prjmob1.ListItem.Item
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.show.ShowActivity

/**
 * Created by LE on 18/04/2018.
 */
class MyShowFragment: BaseFragment()
{
    override fun typeAdpter(): Int {
        return 1
    }
    override fun initItem():ArrayList<Item>
    {
        var item= Item(""
                ,"",
                resources.getString(R.string.show_title),
                resources.getString(R.string.show_tags).toString())

        var items= arrayListOf(item,item,item,item,item,item,item,item,item,item)
        return items
    }
    override fun openActivity(position: Int) {
        val context: Context? = getContext()
        val intent = Intent (context, ShowActivity:: class.java)
        startActivity (intent)
    }
}
