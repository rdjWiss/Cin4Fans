package prj.mob1.prjmob1.movie

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import prj.mob1.prjmob1.R
import android.support.design.widget.TabLayout
import android.support.v7.app.AlertDialog
import android.util.Log

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import android.widget.VideoView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_movie.*
import prj.mob1.prjmob1.ActionsInterface
import prj.mob1.prjmob1.Crew.CrewFragment
import prj.mob1.prjmob1.Person.PersonActivity
import prj.mob1.prjmob1.Util.ConnectivityChecker

import prj.mob1.prjmob1.rating.OnRateClick
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService
import prj.mob1.prjmob1.roomComponenets.RoomDataUtil
import prj.mob1.prjmob1.roomComponenets.models.MovieRoomAdapter
import android.widget.ProgressBar
import android.app.ProgressDialog
import prj.mob1.prjmob1.Util.LoadingDialog


class MovieActivity : AppCompatActivity(), CrewFragment.OnCrewSelected, ActionsInterface {
/*    val MODE_ONLINE = "Online"*/
    val MODE_OFFLINE = "Offline"

    private var modeTab = false

    private var id = 0
    private var movie: MovieClass = MovieClass()
    private var modeConnexion : String?= null

    private lateinit var infosFragment: MovieInfosFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie)

        if (resources.getString(R.string.isLand) == "true") modeTab = true

        val bundle = intent.getBundleExtra("bundle")
        if (bundle != null) {
            id = bundle.getInt("id")
            modeConnexion = bundle.getString("mode")
        } else {
            //TODO: afficher erreur: id manquant / no bundle
            //finish()
            id = 200//550
        }

        if(ConnectivityChecker.isNetworkAvailable(this)) this.getMovieData(id)
        else if(modeConnexion == MODE_OFFLINE) this.getMovieDataOffline()
        else{
            Toast.makeText(this,"Can't get movie infos. No Network Connection",Toast.LENGTH_LONG).show()
            finish()
        }

        //Go back arrow
        movie_back_arrow!!.setOnClickListener {
            finish()
        }

    }

    fun getMovieData(id: Int) {
        val loadingDialog= LoadingDialog.showLoadingDialog(this)

        val apiService: RemoteApiService? = RemoteApiService.create()
        apiService!!.getMovieInfosById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    //                    Toast.makeText(this,"Response ${result.reviews.results}", Toast.LENGTH_LONG).show()
                    //TODO refactore, assign movie var and use it directly
                    movie = result
                    var tags = ""
                    for (tag in movie.genres) tags+=tag.name + ", "
                    movie.tags = tags

                    loadingDialog.dismiss()

                    initMovieInfosFrag(false)
                    initOverviewFragTabMode()
                    configureTabLayout()
                    initTrailer()
                    initBookmarkIcon()


                }, { error ->
                    Toast.makeText(this, "Error ${error.message}", Toast.LENGTH_SHORT).show()
                    error.printStackTrace()
                    loadingDialog.dismiss()
                    finish()

                })


    }

    fun initMovieInfosFrag(inFav:Boolean) {
        //Fragment infos

        /*val */infosFragment =
                MovieInfosFragment.newInstance(movie,inFav)
        supportFragmentManager.beginTransaction().add(R.id.movie_infos, infosFragment).commit()
    }

    fun initOverviewFragTabMode() {
        if (modeTab) {
            val overviewFrag =
                    MovieOverviewFragment.newInstance(movie.overview)
            supportFragmentManager.beginTransaction().add(R.id.movie_overview, overviewFrag).commit()

        }


    }

    private fun configureTabLayout() {
        if (!modeTab) movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Overview"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Crew"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Cinemas"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Comments"))
        movie_tab_layout.addTab(movie_tab_layout.newTab().setText("Related"))

        val adapter = MovieTabPagerAdapter(supportFragmentManager,
                movie_tab_layout.tabCount, modeTab, id, movie)
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

    private fun initTrailer() {
        //Trailer
        val image = findViewById<ImageView>(R.id.movie_image)
        if (movie.imagePath != null) RemoteApiService.getRemoteImage(movie.imagePath, this)!!.into(image)
        if(movie.videos != null){
            val videos = movie.videos.results
            var i = 0
            while (i < videos.size && videos[i].type != "Trailer") i++
            if (i == videos.size) return
            if (videos[i].site != "YouTube") btn_play!!.visibility = View.INVISIBLE
            else {
                val URL = RemoteApiService.getYoutubeURL(videos[i].key)
                btn_play!!.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(URL)))
                }
            }
        }


    }

    private fun initBookmarkIcon(){
        RoomDataUtil.getFavMovieById(this, movie.id, { movieAdapter ->

            if(movieAdapter != null) {
                Log.e("Fin",movieAdapter.id.toString())
//                Toast.makeText(this,movieAdapter.id.toString(),Toast.LENGTH_LONG).show()
                infosFragment.setBookmarkOff()
            }else {
//                Toast.makeText(this,"Not in DB",Toast.LENGTH_LONG).show()
            }
        })


    }


    override fun onCrewSelected(creditId: Int) {
        val intent = Intent(this, PersonActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("personId", creditId)
        bundle.putString("image",movie.imagePath)
        intent.putExtra("bundle", bundle)
        startActivity(intent)
    }

    override fun onRateClick() {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_rating, null)
        val dialogRatingBar = dialogView.findViewById<RatingBar>(R.id.dialogRb)

        builder.setView(dialogView)
                .setPositiveButton("OK") { _, _ ->

                    Toast.makeText(this,
                            "Rated: ${dialogRatingBar.rating}", Toast.LENGTH_SHORT)
                            .show()

                }
                .setNegativeButton("Annuler") { _, _ ->
                }
                .show()
    }

    override fun onAddBookmark() {

         RoomDataUtil.addMovieToFav(this, movie,{  ->
             Snackbar.make(linearLayout,"Added to favorites", Snackbar.LENGTH_SHORT).show()
         })
    }

    override fun onRemoveBookmark() {
         RoomDataUtil.removeMovieFromFav(this, MovieRoomAdapter(movie),{
             Snackbar.make(linearLayout,"Removed from favorites", Snackbar.LENGTH_SHORT).show()
         })

    }

    private fun getMovieDataOffline(){
        RoomDataUtil.getFavMovieById(this, id, { movieAdapter ->
            if(movieAdapter!=null) {
                movie = movieAdapter//MovieClass(movieAdapter)
//                Toast.makeText(this,movieAdapter.tags, Toast.LENGTH_SHORT).show()
//                Toast.makeText(this,movie.tags, Toast.LENGTH_SHORT).show()
                initMovieInfosFrag(true)
//
                initOverviewFragTabMode()
                configureTabLayout()
                initTrailer()
            }else Toast.makeText(this,"NULL", Toast.LENGTH_LONG).show()

        })
    }



}
