package prj.mob1.prjmob1.MyShows

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import prj.mob1.prjmob1.BaseFragment.BaseFragment
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.show.ShowActivity

/**
 * Created by LE on 18/04/2018.
 */
class MyShowFragment: BaseFragment()
{

    private var views: View? = null

    private var list_show_adapter: MyShowAdapter? = null
    private var recyclerView: RecyclerView? = null
    private lateinit var item: MyShowItem
    private lateinit var show_items:Array<MyShowItem>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        item= MyShowItem(R.drawable.show_poster,
                resources.getString(R.string.show_title),
                resources.getString(R.string.show_tags).toString())

        show_items = arrayOf(item,item,item,item,item,item,item,item,item,item)

        // Inflate the layout for this fragment
        views = inflater!!.inflate(R.layout.fragment_list_show, container, false)



        recyclerView = views!!.findViewById<View>(R.id.show_item_listview) as RecyclerView
        list_show_adapter= MyShowAdapter(activity,populateList())
        recyclerView!!.adapter = list_show_adapter
        //
        if (resources.getString(R.string.isLand) == "false") {
            recyclerView!!.layoutManager = GridLayoutManager(activity,2)
        }else{
            recyclerView!!.layoutManager = GridLayoutManager(activity,5)
        }
        recyclerView!!.addOnItemTouchListener(MyShowFragment.RecyclerTouchListener(activity, recyclerView!!, object : MyShowFragment.ClickListener {
            override fun onClick(view: View, position: Int) {
                openFragment(position)
            }
            override fun onLongClick(view: View?, position: Int) {
            }
        }))

        // openFragment(0)

        return views //to return the layout as a result
    }
    override fun openFragment(position: Int) {
        val context: Context = getContext()
        val intent = Intent (context,  ShowActivity:: class.java)
        startActivity (intent)
    }


    private fun populateList(): ArrayList<MyShowItem> {
        val list = ArrayList<MyShowItem>()

        for (i in show_items.indices) {
            list.add(show_items[i])
        }
        return list
    }

    interface ClickListener {
        fun onClick(view: View, position: Int)

        fun onLongClick(view: View?, position: Int)
    }

    internal class RecyclerTouchListener(context: Context, recyclerView: RecyclerView, private val clickListener: ClickListener?) : RecyclerView.OnItemTouchListener {

        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val child = recyclerView.findChildViewUnder(e.x, e.y)
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child))
                    }
                }
            })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }

}
