package prj.mob1.prjmob1.roomComponenets

import android.content.Context
import android.graphics.Movie
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import prj.mob1.prjmob1.movie.MovieClass
import prj.mob1.prjmob1.roomComponenets.models.MovieRoomAdapter

// Created by sol on 14/06/2018.



class RoomDataUtil {

    companion object {
        //Save the movie in th DB
        fun addMovieToFav(act:Context, movie:MovieRoomAdapter,callback: () -> Unit) {
            object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg voids: Void): Void? {
                    val db = CinFanDB.getInstance(act)
                    val dao = db?.movieDAO()
                    dao?.addMovie(movie)

                    return null
                }

                override fun onPostExecute(result: Void?) {
                    Toast.makeText(act, "ADD MOVIE TO FAV",Toast.LENGTH_SHORT).show()
                    callback()
                }
            }.execute()
        }

        fun removeMovieFromFav(act:Context, movie:MovieRoomAdapter,callback: () -> Unit) {
            object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg voids: Void): Void? {
                    val db = CinFanDB.getInstance(act)
                    val dao = db?.movieDAO()
                    dao?.delMovie(movie)

                    return null
                }

                override fun onPostExecute(result: Void?) {
                    Toast.makeText(act, "REMOVE MOVIE FROM FAV",Toast.LENGTH_SHORT).show()
                    callback()
                }
            }.execute()
        }

        fun getFavMoviesList(act:Context,callback: (List<MovieRoomAdapter>?) -> Unit) {
            var movies:List<MovieRoomAdapter>? = null

            object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg voids: Void): Void? {
                    val db = CinFanDB.getInstance(act)
                    val dao = db?.movieDAO()
                    movies = dao?.getMoviesList()
                    return null
                }


                override fun onPostExecute(result: Void?) {
                    Toast.makeText(act, "GET FAV MOVIES LIST",Toast.LENGTH_SHORT).show()
                    callback(movies)
                }
            }.execute()
        }

        fun getFavMovieById(act:Context, movieId:Int,callback: (MovieRoomAdapter?) -> Unit) {
            var movie:MovieRoomAdapter? = null
            object : AsyncTask<Void, Void, MovieRoomAdapter>() {
                override fun doInBackground(vararg voids: Void): MovieRoomAdapter? {
                    val db = CinFanDB.getInstance(act)
                    val dao = db?.movieDAO()
                    movie = dao?.getMovie(movieId)
                    return movie
                }

                override fun onPostExecute(result: MovieRoomAdapter?) {
                    Toast.makeText(act, "GET FAV MOVIE BY ID",Toast.LENGTH_SHORT).show()
                    callback(result)
                }
            }.execute()

        }

    }
}
