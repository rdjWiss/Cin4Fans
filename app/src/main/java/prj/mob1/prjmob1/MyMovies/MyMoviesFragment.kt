package prj.mob1.prjmob1.MyMovies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import prj.mob1.prjmob1.AllListMovies.AllListMoviesFragment
import prj.mob1.prjmob1.BaseFragment.BaseFragment
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.movie.MovieActivity

/**
 * Created by LE on 18/04/2018.
 */

class  MyMoviesFragment: BaseFragment()
{

    private var views: View? = null

    private var list_mov_adapter: MyMoviesAdapter? = null
    private var recyclerView: RecyclerView? = null
    private lateinit var item: MyMovieItem
    private lateinit var movie_items:Array<MyMovieItem>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        item= MyMovieItem(R.drawable.poster_mov,
                resources.getString(R.string.movie_title),
                resources.getString(R.string.movie_tags).toString())

        movie_items = arrayOf(item,item,item,item,item)



        // Inflate the layout for this fragment
        views = inflater!!.inflate(R.layout.fragment_list_movies, container, false)



        recyclerView = views!!.findViewById<View>(R.id.movie_item_listview) as RecyclerView
        list_mov_adapter= MyMoviesAdapter(activity,populateList())
        recyclerView!!.adapter = list_mov_adapter
        //
        if (resources.getString(R.string.is_phone) == "true") {
            recyclerView!!.layoutManager = GridLayoutManager(activity,2)
        }else{
            recyclerView!!.layoutManager = GridLayoutManager(activity,4)
        }
        recyclerView!!.addOnItemTouchListener(AllListMoviesFragment.RecyclerTouchListener(activity, recyclerView!!, object : AllListMoviesFragment.ClickListener {
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
        val intent = Intent (context, MovieActivity:: class.java)
        startActivity (intent)
    }


    private fun populateList(): ArrayList<MyMovieItem> {
        val list = ArrayList<MyMovieItem>()

        for (i in movie_items.indices) {
            list.add(movie_items[i])
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