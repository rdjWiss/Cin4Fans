package prj.mob1.prjmob1.AllListMovies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import prj.mob1.prjmob1.Liste_movies.ListMoviesFragment

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.Util.addFragment
import prj.mob1.prjmob1.Util.initDrawer

class Activity_all_list_movie : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_list_movie)

        apply {
            initDrawer()
//            addFragment(AllListMoviesFragment(),R.id.container_body_all_movie)
            addFragment(ListMoviesFragment(),R.id.container_body_all_movie)
        }


    }
}
