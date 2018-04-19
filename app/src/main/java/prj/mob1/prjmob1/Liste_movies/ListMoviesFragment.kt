package prj.mob1.prjmob1.Liste_movies
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.movie.MovieActivity
import java.util.ArrayList


/**
 * Created by LE on 03/04/2018.
 */
class ListMoviesFragment:Fragment()
{

    private var views: View? = null

    private var list_mov_adapter: ListMoviesAdapter? = null
    private var recyclerView: RecyclerView? = null
    private lateinit var item:Movies_Item
    private lateinit var movie_items:Array<Movies_Item>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        item= Movies_Item(R.drawable.movie_poster
                ,resources.getString(R.string.movie_year).toInt(),
                resources.getString(R.string.movie_title),
                resources.getString(R.string.movie_tags).toString())

        movie_items = arrayOf(item,item,item,item,item,item,item,item,item,item)



        // Inflate the layout for this fragment
        views = inflater!!.inflate(R.layout.fragment_list_movies, container, false)



        recyclerView = views!!.findViewById<View>(R.id.movie_item_listview) as RecyclerView
        list_mov_adapter= ListMoviesAdapter(activity,populateList())
        recyclerView!!.adapter = list_mov_adapter
        //
        if (resources.getString(R.string.isLand) == "false") {
            recyclerView!!.layoutManager = LinearLayoutManager(activity)
        }else{
            recyclerView!!.layoutManager = GridLayoutManager(activity,2)
        }
        recyclerView!!.addOnItemTouchListener(ListMoviesFragment.RecyclerTouchListener(activity, recyclerView!!, object : ListMoviesFragment.ClickListener {
            override fun onClick(view: View, position: Int) {
                openFragment(position)
            }
            override fun onLongClick(view: View?, position: Int) {
            }
        }))

       // openFragment(0)

        return views //to return the layout as a result
    }
    private fun openFragment(position: Int) {
        val context: Context = getContext()
        val intent = Intent (context, MovieActivity:: class.java)
        startActivity (intent)
    }


    private fun populateList(): ArrayList<Movies_Item> {
        val list = ArrayList<Movies_Item>()

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