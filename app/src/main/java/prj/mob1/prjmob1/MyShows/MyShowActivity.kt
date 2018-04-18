package prj.mob1.prjmob1.MyShows

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.Util.addFragment
import prj.mob1.prjmob1.Util.initDrawer

class MyShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_show)
        apply {
            initDrawer()
            addFragment(MyShowFragment(),R.id.container_body_my_show)
        }

    }
}
