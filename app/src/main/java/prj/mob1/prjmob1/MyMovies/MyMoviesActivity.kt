package prj.mob1.prjmob1.MyMovies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import prj.mob1.prjmob1.ListItem.Item
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.Util.addFragment
import prj.mob1.prjmob1.Util.initDrawer
import prj.mob1.prjmob1.roomComponenets.RoomDataUtil

class MyMoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_movies)
        setTitle("My Movies")

        apply {
            initDrawer()
            initMyMoviesFrag()

        }
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
                //mAdapter.getFilter().filter(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun initMyMoviesFrag(){
        RoomDataUtil.getFavMoviesList(this, { movieList ->
            for (movie in movieList!!.iterator()) Log.e("List",movie.title)

            val movieArray = ArrayList<Item>()
            for (movie  in movieList) {
                val item = Item(movie.id,movie.posterId,movie.title)
                movieArray.add(item)
            }

            val favMovies = MyMoviesFragment.newInstance(movieArray)
            addFragment(favMovies,R.id.container_body_my_movie)

        })
    }

}
