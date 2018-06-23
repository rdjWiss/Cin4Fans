package prj.mob1.prjmob1.notification

import android.app.IntentService
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import prj.mob1.prjmob1.ListItem.ListMovies
import prj.mob1.prjmob1.Settings.ItemChoice
import prj.mob1.prjmob1.movie.MovieActivity
import prj.mob1.prjmob1.movie.MovieClass
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService
import prj.mob1.prjmob1.retrofitUtil.models.Genre

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 *
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
class NotificationService : IntentService("NotificationService") {

    private lateinit var genresList : List<ItemChoice>
    private var favGenres = listOf("Romance","Adventure")

    override fun onHandleIntent(intent: Intent?) {
        Log.i("NotificationService", "Service running");

        val apiService: RemoteApiService? = RemoteApiService.create()
        apiService!!.getGenres()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    Log.e("NotificationService",result.toString())
                    genresList = result.body()!!.choices

                    Log.e("NotificationService",genresList.toString())
                    apiService.getNewMovie()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe({ movies ->
                                Log.e("NotificationService",movies.body()!!.toString())
                                handleResponseSuccess(movies.body()!!,intent)
                            }, { error ->

                            })
                }, { error ->

                })


    }

    private fun handleResponseSuccess(result:ListMovies,intent: Intent?){
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val pIntent = PendingIntent.getService(this, System.currentTimeMillis().toInt(), intent, 0)
        val builder = NotificationBuilder.builder(notificationManager, pIntent,this)
        var notif: Notification

        for(movie in result.movies){
            Log.e("NotificationService","Movie ${movie.title}: ")

            if(movieOfFavGenre(movie.genreIds)){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    Log.e("NotificationService","FAV ${movie.id} released on ${movie.releaseDate}")

                    val movieIntent = Intent(this, MovieActivity::class.java)
                    val bundle = Bundle()
                    bundle.putInt("id",movie.id)
                    movieIntent.putExtra("bundle",bundle)
                    val pendingIntent = PendingIntent.getActivity(
                            this,
                            0,
                            movieIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    )
                    notif = builder!!.setContentTitle("New movie of your favorite genre")
                            .setContentText("${movie.title} released on ${movie.releaseDate}")
                            .setContentIntent(pendingIntent)
                            .build()
                    notificationManager.notify(0, notif)
                }
            }
        }

    }

    private fun movieOfFavGenre(movieGenres : IntArray):Boolean{

        var i=0
        var isFav = false
        while (i<movieGenres.size &&!isFav ){
            val genreName:String  = genresList.find{j -> j.id == movieGenres[i]}!!.name
            Log.e("NotificationService","${movieGenres[i]} ${genreName}")
            if(favGenres.indexOf(genreName)!=-1) isFav=true
            i++
        }
        return isFav
    }

//    private fun getGenreName(genre:ItemChoice) = { }

    companion object {
        private val ACTION_SERVICE = "prj.mob1.prjmob1.notification.alarm"

        fun demarrerService(context: Context) {
            val intent = Intent(context, NotificationService::class.java)
            intent.action = ACTION_SERVICE
            context.startService(intent)
        }
    }

    /*override fun onHandleIntent(intent: Intent?) {
        if (intent!=null) {
            val action = intent.action
            if (ACTION_FOO==action) {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionFoo(param1, param2)
            } else if (ACTION_BAZ==action) {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionBaz(param1, param2)
            }
        }
    }

    *//**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     *//*
    private fun handleActionFoo(param1: String, param2: String) {
        // TODO: Handle action Foo
        throw UnsupportedOperationException("Not yet implemented")
    }

    *//**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     *//*
    private fun handleActionBaz(param1: String, param2: String) {
        // TODO: Handle action Baz
        throw UnsupportedOperationException("Not yet implemented")
    }

    companion object {
        // TODO: Rename actions, choose action names that describe tasks that this
        // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
        private val ACTION_FOO = "prj.mob1.prjmob1.notification.action.FOO"
        private val ACTION_BAZ = "prj.mob1.prjmob1.notification.action.BAZ"

        // TODO: Rename parameters
        private val EXTRA_PARAM1 = "prj.mob1.prjmob1.notification.extra.PARAM1"
        private val EXTRA_PARAM2 = "prj.mob1.prjmob1.notification.extra.PARAM2"

        *//**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         *//*
        // TODO: Customize helper method
        fun startActionFoo(context: Context, param1: String, param2: String) {
            val intent = Intent(context, NotificationService::class.java)
            intent.action = ACTION_FOO
            intent.putExtra(EXTRA_PARAM1, param1)
            intent.putExtra(EXTRA_PARAM2, param2)
            context.startService(intent)
        }

        *//**
         * Starts this service to perform action Baz with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         *//*
        // TODO: Customize helper method
        fun startActionBaz(context: Context, param1: String, param2: String) {
            val intent = Intent(context, NotificationService::class.java)
            intent.action = ACTION_BAZ
            intent.putExtra(EXTRA_PARAM1, param1)
            intent.putExtra(EXTRA_PARAM2, param2)
            context.startService(intent)
        }
    }*/
}
