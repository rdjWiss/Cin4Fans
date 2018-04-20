package prj.mob1.prjmob1.Cinema

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.Util.addFragment
import prj.mob1.prjmob1.Util.initDrawer

class CinemasActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinemas)

        apply {
            initDrawer()
            addFragment(CinemaFragment(),R.id.container_body_all_cinema)
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
}
