package prj.mob1.prjmob1.show

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.RatingBar
import android.widget.Toast
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_show.*
import prj.mob1.prjmob1.Crew.CrewFragment
import prj.mob1.prjmob1.Person.PersonActivity
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.rating.OnRateClick
import prj.mob1.prjmob1.season.Season
import prj.mob1.prjmob1.season.SeasonActivity

class ShowActivity : AppCompatActivity(), CrewFragment.OnCrewSelected,
                ShowSeasonsFragment.OnSeasonSelected, OnRateClick {

    private var modeTab = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_show)
        if (resources.getString(R.string.isLand) == "true") modeTab = true

        //Trailer
        val videoview = findViewById<VideoView>(R.id.show_trailer)
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.walking_dead)
        videoview.setVideoURI(uri)

        var firstPlay = true
        btn_play!!.setOnClickListener {
            btn_play!!.visibility = View.INVISIBLE
            btn_pause!!.visibility = View.VISIBLE
            if(firstPlay) {
                show_image!!.visibility = View.INVISIBLE
                show_trailer!!.visibility = View.VISIBLE
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
        back_arrow.setOnClickListener{
            finish()
        }

        //Fragment infos
        supportFragmentManager.beginTransaction().add(R.id.show_infos, ShowInfosFragment()).commit()

        //Overview fragment en mode tablette
        if (modeTab){
            supportFragmentManager.beginTransaction()
                    .add(R.id.show_overview, ShowOverviewFragment()).commit()
        }

        //Tabs
        configureTabLayout()

    }

    private fun configureTabLayout() {
        if(!modeTab) show_tab_layout.addTab(show_tab_layout.newTab().setText(getString(R.string.show_tab1)))
        show_tab_layout.addTab(show_tab_layout.newTab().setText(getString(R.string.show_tab2)))
        show_tab_layout.addTab(show_tab_layout.newTab().setText(getString(R.string.show_tab3)))
        show_tab_layout.addTab(show_tab_layout.newTab().setText(getString(R.string.show_tab4)))
        show_tab_layout.addTab(show_tab_layout.newTab().setText(getString(R.string.show_tab5)))

        val adapter =ShowTabPagerAdapter(supportFragmentManager,
                show_tab_layout.tabCount,modeTab)
        show_viewpager.adapter = adapter

        show_viewpager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(show_tab_layout))

        show_tab_layout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                show_viewpager.currentItem = tab.position
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

    override fun onSeasonSelected(season: Season) {
        val intent = Intent(this, SeasonActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("season",season)
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