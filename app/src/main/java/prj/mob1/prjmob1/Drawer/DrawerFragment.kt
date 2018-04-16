package prj.mob1.prjmob1.Drawer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import prj.mob1.prjmob1.R
import android.content.Context
import android.content.Intent
import android.util.Log
import prj.mob1.prjmob1.HomeActivity
import prj.mob1.prjmob1.movie.MovieActivity


import java.util.*

class DrawerFragment : Fragment() {

    private var views: View? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var drawerAdapter: DrawerAdapter? = null
    private var containerView: View? = null
    private var recyclerView: RecyclerView? = null


   // var nms = this.resources.getStringArray(R.array.drawer_names)

   private val names = arrayOf("Home","Movies","TV Shows","Cinemas",
            "My Movies","My TV Shows","My Cinemas","Settings","About","Logout")


    private val images = intArrayOf(R.drawable.home,
           R.drawable.movies,R.drawable.tv_shows,
            R.drawable.cinemas,R.drawable.movies,R.drawable.tv_shows
            ,R.drawable.cinemas,R.drawable.settings,R.drawable.about,R.drawable.logout
            )

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }*/

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        views = inflater!!.inflate(R.layout.fragment_drawer, container, false)
        recyclerView = views!!.findViewById<View>(R.id.listview) as RecyclerView
        drawerAdapter = DrawerAdapter(activity, populateList())
        recyclerView!!.adapter = drawerAdapter
        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        recyclerView!!.addOnItemTouchListener(RecyclerTouchListener(activity, recyclerView!!, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                openFragment(position)
                mDrawerLayout!!.closeDrawer(containerView)
            }

            override fun onLongClick(view: View?, position: Int) {

            }
        }))

        openFragment(-1)

        return views //to return the layout as a result
    }

    private fun openFragment(position: Int) {
        val context:Context= getContext()
       when (position) {
           // 0 -> removeAllFragment(FriendListFragment(), "Friends")
           // 1 -> removeAllFragment(NotificationFragment(), "Notifiaction")

           0-> {
                if (context.toString().contains("HomeActivity"))
                { openFragment(-1)
                    Log.e("error", "2 context= + $context et position= $position ") }
                else
                { val intent = Intent (context, HomeActivity:: class.java)
                    startActivity (intent)}
           }
           1->
             {val intent = Intent (context, MovieActivity:: class.java)
                startActivity (intent)}
            else -> {
            }
        }
    }

    fun removeAllFragment(replaceFragment: Fragment, tag: String) {
        val manager = activity.supportFragmentManager
        val ft = manager.beginTransaction()
        manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        ft.replace(R.id.container_body, replaceFragment)
        ft.commitAllowingStateLoss()
    }

    fun setUpDrawer(fragmentId: Int, drawerLayout: DrawerLayout, toolbar: Toolbar) {
        containerView = activity.findViewById(fragmentId)
        mDrawerLayout = drawerLayout
        mDrawerToggle = object : ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
                activity.invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View?) {
                super.onDrawerClosed(drawerView)
                activity.invalidateOptionsMenu()
            }

            override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                toolbar.alpha = 1 - slideOffset / 2
            }
        }

        mDrawerLayout!!.setDrawerListener(mDrawerToggle)
        mDrawerLayout!!.post { mDrawerToggle!!.syncState() }

    }

    private fun populateList(): ArrayList<DrawerModel> {

        val list = ArrayList<DrawerModel>()

        for (i in names.indices) {
            val drawerModel = DrawerModel()
            drawerModel.image = images[i]
            drawerModel.name = names[i]
            list.add(drawerModel)
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