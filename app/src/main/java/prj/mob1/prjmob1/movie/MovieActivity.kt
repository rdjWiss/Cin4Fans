package prj.mob1.prjmob1.movie

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import prj.mob1.prjmob1.R
import android.support.design.widget.TabLayout
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.RatingBar
import android.widget.Toast
import android.widget.VideoView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_movie.*
import prj.mob1.prjmob1.ActionsInterface
import prj.mob1.prjmob1.Crew.CrewFragment
import prj.mob1.prjmob1.Person.PersonActivity
import prj.mob1.prjmob1.rating.OnRateClick
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService

class MovieActivity : AppCompatActivity(), CrewFragment.OnCrewSelected, OnRateClick, ActionsInterface {

    private var modeTab = false

    private var id = 0
    private var movie :MovieClass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie)

        if (resources.getString(R.string.isLand) == "true") modeTab = true

        val bundle = intent.getBundleExtra("bundle")
        if(bundle != null){
            id  = bundle.getInt("id")
        }else{
            //TODO: afficher erreur: id manquant / no bundle
            //finish()
            id = 200//550
        }

        //Get les infos du films
        this.getMovieData(id)

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



    }

    fun getMovieData(id:Int){
        val apiService: RemoteApiService? = RemoteApiService.create()
        apiService!!.getMovieInfosById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    result ->
//                    Toast.makeText(this,"Response ${result.reviews.results}", Toast.LENGTH_LONG).show()
                    //TODO refactore, assign movie var and use it directly
                    initMovieInfosFrag(result)
                    initOverviewFragTabMode(result.overview)
                    configureTabLayout(result)
                    movie = result
                }, { error ->
                    Toast.makeText(this,"Error ${error.message}", Toast.LENGTH_LONG).show()
                    error.printStackTrace()

                })


    }

    fun initMovieInfosFrag(movie: MovieClass){
        //Fragment infos
        val infosFragment =
                MovieInfosFragment.newInstance(movie)
        supportFragmentManager.beginTransaction().add(R.id.movie_infos, infosFragment).commit()
    }

    fun initOverviewFragTabMode(overview:String){
        if (modeTab){
            val overviewFrag =
                    MovieOverviewFragment.newInstance(overview)
            supportFragmentManager.beginTransaction().add(R.id.movie_overview, overviewFrag).commit()

        }


    }

    private fun configureTabLayout(movie: MovieClass) {
        if(!modeTab) movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Overview"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Crew"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Cinemas"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Comments"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Related"))

        val adapter = MovieTabPagerAdapter(supportFragmentManager,
                movie_tab_layout.tabCount,modeTab,id,movie)
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


    override fun onCrewSelected(creditId:Int) {
        Toast.makeText(this,
                creditId.toString(), Toast.LENGTH_SHORT )
                .show()
        val intent = Intent(this, PersonActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("personId",creditId)
        intent.putExtra("bundle",bundle)
        startActivity(intent)
    }

    override fun onRateClick(){
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_rating, null)
        val dialogRatingBar = dialogView.findViewById<RatingBar>(R.id.dialogRb)

        builder.setView(dialogView)
                .setPositiveButton("OK") { _, _ ->

                    Toast.makeText(this,
                            "Rated: ${dialogRatingBar.rating}", Toast.LENGTH_SHORT )
                            .show()

                }
                .setNegativeButton("Annuler") { dialogInterface, i ->
                }
                .show()
    }

    override fun onAddBookmark(){

    }

    override fun onRemoveBookmark(){

    }







}
