package prj.mob1.prjmob1.movie

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import prj.mob1.prjmob1.R
import android.support.design.widget.TabLayout
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.RatingBar
import android.widget.Toast
import android.widget.VideoView

import kotlinx.android.synthetic.main.activity_movie.*
import prj.mob1.prjmob1.CrewFragment
import prj.mob1.prjmob1.Person.PersonActivity
import prj.mob1.prjmob1.rating.OnRateClick

class MovieActivity : AppCompatActivity(), CrewFragment.OnCrewSelected, OnRateClick {

    private var modeTab = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie)
        if (resources.getString(R.string.isLand) == "true") modeTab = true

        //Trailer
        val videoview = findViewById<VideoView>(R.id.movie_trailer)
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.black)
        videoview.setVideoURI(uri)

        var firstPlay = true
        btn_play!!.setOnClickListener {
            btn_play!!.visibility = View.INVISIBLE
            btn_pause!!.visibility = View.VISIBLE
            if(firstPlay) {
                movie_image!!.visibility = View.INVISIBLE
                movie_trailer!!.visibility = View.VISIBLE
                firstPlay = false
            }
            videoview.start()
        }

        btn_pause!!.setOnClickListener {
            btn_pause!!.visibility = View.INVISIBLE
            btn_play!!.visibility = View.VISIBLE
            videoview.pause()
        }
        //END trailer

        //Go back arrow
        movie_back_arrow!!.setOnClickListener{
            finish()
        }
        //Fragment infos
        supportFragmentManager.beginTransaction().add(R.id.movie_infos, MovieInfosFragment()).commit()

        //Overview fragment for tablet
        if (modeTab){
            supportFragmentManager.beginTransaction()
                    .add(R.id.movie_overview, MovieOverviewFragment()).commit()
        }

        //Tabs
        configureTabLayout()

    }

    private fun configureTabLayout() {
        if(!modeTab) movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Overview"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Crew"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Cinemas"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Comments"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Related"))

        val adapter = MovieTabPagerAdapter(supportFragmentManager,
                movie_tab_layout.tabCount,modeTab)
        movie_viewpager.adapter = adapter

        movie_viewpager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(movie_tab_layout))

        movie_tab_layout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                movie_viewpager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun onCrewSelected(name: String) {
        val intent = Intent(this, PersonActivity::class.java)
        val bundle = Bundle()
        bundle.putString("personName",name)
        intent.putExtra("bundle",bundle)
        startActivity(intent)
    }

    override fun onRateClick(){
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_rating, null)
        val dialogRatingBar = dialogView.findViewById<RatingBar>(R.id.dialogRb)

        builder.setView(dialogView)
                .setPositiveButton("OK") { dialogInterface, i ->

                    Toast.makeText(this,
                            "Rated: ${dialogRatingBar.rating}", Toast.LENGTH_SHORT )
                            .show()

                }
                .setNegativeButton("Annuler") { dialogInterface, i ->
                }
                .show()
    }

}
