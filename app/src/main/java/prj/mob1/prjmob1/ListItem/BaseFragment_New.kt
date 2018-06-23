package prj.mob1.prjmob1.ListItem

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import prj.mob1.prjmob1.R

/**
 * Created by LE on 20/06/2018.
 */
abstract class BaseFragment_New: android.support.v4.app.Fragment(){


    private var views: View? = null

    abstract  fun getData()
    abstract fun openActivity(position:Int)
    abstract fun  onQueryTextChange(text: String)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        views = inflater.inflate(R.layout.fragment_list, container, false)
        return views
    }



    fun onCreateMovieLatestFail(error:Throwable) {
        Log.e("erroor","errror"+ error.message.toString())

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

   fun filter(text: String,ArrayMovies:ArrayList<Item>,list_adapter:MyListAdapter) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<Item>()

        //looping through existing elements
        for (movie in ArrayMovies) {
            //if the existing elements contains the search input
            if (movie.title.startsWith(text.toLowerCase(),true)) {
                //adding the element to filtered list
                filterdNames.add(movie)
            }
        }

        //calling a method of the adapter class and passing the filtered list
        list_adapter.filterList(filterdNames)
    }
}