package prj.mob1.prjmob1.AllListShow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import prj.mob1.prjmob1.Liste_shows.ListShowFragment
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.Util.addFragment
import prj.mob1.prjmob1.Util.initDrawer

class AllListShowActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_list_show)
        setTitle("All TV Shows")

        apply {
            initDrawer()
            //addFragment(AllListShowFragment(),R.id.container_body_all_show)
            addFragment(ListShowFragment(),R.id.container_body_all_show)
        }
    }
}
