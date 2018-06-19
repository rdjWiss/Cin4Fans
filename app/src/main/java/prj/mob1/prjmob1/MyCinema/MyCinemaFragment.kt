package prj.mob1.prjmob1.MyCinema

import prj.mob1.prjmob1.ListItem.BaseFragment
import prj.mob1.prjmob1.ListItem.Item
import prj.mob1.prjmob1.R

/**
 * Created by LE on 20/04/2018.
 */
class MyCinemaFragment:BaseFragment()
{
    override fun typeAdpter(): Int {
        return 1
    }
    override fun initItem():ArrayList<Item>
    {
        var item= Item(""
                ,"",
                resources.getString(R.string.cinema_title),
                resources.getString(R.string.cinema_adress).toString())
        var items= arrayListOf(item,item,item,item,item,item,item,item,item,item)
        return items
    }
    override fun openActivity(position: Int) {
       //Géolicasation
    }
}
