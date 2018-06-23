package prj.mob1.prjmob1.Home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView

import android.view.Menu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
//import kotlinx.android.synthetic.main.movie_tab.*
import prj.mob1.prjmob1.Liste_movies.ListMoviesFragment
import prj.mob1.prjmob1.Liste_shows.ListShowFragment
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.Util.ConnectivityChecker
import prj.mob1.prjmob1.Util.initDrawer
import java.util.*
import android.content.Intent
import prj.mob1.prjmob1.notification.NotificationService
import prj.mob1.prjmob1.notification.UpdateScheduler


class HomeActivity : AppCompatActivity() {


    lateinit var  adapter:HomeTabPagerAdapter

/*    private lateinit var mHandler: Handler
    private lateinit var mRunnable:Runnable*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setTitle("Home")

//        if(!ConnectivityChecker.isNetworkAvailable(this)){
//            Toast.makeText(this, "No Network Connection",Toast.LENGTH_LONG).show()
//
//        }
        apply {
            initDrawer()
            // The tab
            configureTabLayout()
            startNotifService()
        }

        val service = UpdateScheduler()
        service.scheduleAlarm(this)
        /*// Initialize the handler instance
        mHandler = Handler()


        // Set an on refresh listener for swipe refresh layout
        swipe_refresh_layout.setOnRefreshListener {
            // Initialize a new Runnable
            mRunnable = Runnable {
                // Hide swipe to refresh icon animation
                swipe_refresh_layout.isRefreshing = false
            }

            // Execute the task after specified time
            mHandler.postDelayed(
                    mRunnable,
                    (randomInRange(1,5)*1000).toLong() // Delay 1 to 5 seconds
            )
        }*/
    }



    override fun onCreateOptionsMenu(menu: Menu) :Boolean{
        getMenuInflater().inflate(R.menu.search_item, menu)
        val mSearch = menu.findItem(R.id.action_search)
        val  mSearchView : SearchView =  mSearch.getActionView() as SearchView
        mSearchView.setQueryHint("Search")
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {

                if (mv_viewpager.currentItem  ==0)
                {
                    val fragment =  mv_viewpager.adapter!!.instantiateItem(mv_viewpager, 0) as Fragment
                    if ( fragment is ListMoviesFragment)
                        fragment.onQueryTextChange(newText)
                }
                else
                {
                    val fragment =  mv_viewpager.adapter!!.instantiateItem(mv_viewpager, 1) as Fragment
                    if ( fragment is ListShowFragment)
                        fragment.onQueryTextChange(newText)
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun configureTabLayout() {

        mv_tab_layout.addTab(mv_tab_layout.newTab().setText("Movies Playing"))
        mv_tab_layout.addTab(mv_tab_layout.newTab().setText("TV Shows airing"))

         adapter = HomeTabPagerAdapter(supportFragmentManager,
                mv_tab_layout.tabCount)

        mv_viewpager.adapter = adapter

       mv_viewpager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(mv_tab_layout))

        mv_tab_layout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                mv_viewpager.currentItem = tab.position

            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

    private fun randomInRange(min:Int, max:Int):Int{
        // Define a new Random class
        val r = Random()

        // Get the next random number within range
        // Including both minimum and maximum number
        return r.nextInt((max - min) + 1) + min;
    }

    fun startNotifService() {
        // Construct our Intent specifying the Service
        val i = Intent(this, NotificationService::class.java)
        // Add extras to the bundle
        i.putExtra("foo", "bar")
        // Start the service
        startService(i)
    }

}

