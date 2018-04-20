package prj.mob1.prjmob1.ListItem

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Adapter
import prj.mob1.prjmob1.Cinema.ListCinemaAdpater
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.movie.MovieActivity
import java.util.ArrayList


/**
 * Created by LE on 17/04/2018.
 */
abstract open class BaseFragment : android.support.v4.app.Fragment()
{

    private var views: View? = null

    private lateinit var list_adapter :ListAdapter
    private lateinit var my_list_adpter:MyListAdapter
    private lateinit var cinema_adapter:ListCinemaAdpater

    private var recyclerView: RecyclerView? = null

    private lateinit var items:Array<Item>

    abstract fun initItem():Array<Item>

    abstract fun typeAdpter():Int
    abstract fun openActivity(position:Int)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        items=initItem()
        // Inflate the layout for this fragment
        views = inflater!!.inflate(R.layout.fragment_list, container, false)
        recyclerView = views!!.findViewById<View>(R.id.item_listview) as RecyclerView

        when (typeAdpter())
        {
            0-> { list_adapter = ListAdapter(activity, populateList())
                recyclerView!!.adapter = list_adapter }
            1->{my_list_adpter= MyListAdapter(activity,populateList())
                recyclerView!!.adapter = my_list_adpter }
            2->{
                cinema_adapter = ListCinemaAdpater(activity, populateList())
                recyclerView!!.adapter = cinema_adapter }
            else ->
            { list_adapter = ListAdapter(activity, populateList())
                recyclerView!!.adapter = list_adapter
            }
        }

        //
        if (resources.getString(R.string.isLand) == "false") {
            if (typeAdpter()==0 ||typeAdpter()==2)
            recyclerView!!.layoutManager = LinearLayoutManager(activity)
            else
                recyclerView!!.layoutManager = GridLayoutManager(activity,2)
        }else{
            recyclerView!!.layoutManager = GridLayoutManager(activity,2)
        }
        recyclerView!!.addOnItemTouchListener(RecyclerTouchListener(activity, recyclerView!!, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                openActivity(position)
            }

            override fun onLongClick(view: View?, position: Int) {
            }
        }))

        return views //to return the layout as a result
    }





    private fun populateList(): ArrayList<Item> {
        val list = ArrayList<Item>()

        for (i in items.indices) {
            list.add(items[i])
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