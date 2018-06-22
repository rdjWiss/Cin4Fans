package prj.mob1.prjmob1.roomComponenets

import android.content.Context
import android.graphics.Movie
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import prj.mob1.prjmob1.Crew.Cast
import prj.mob1.prjmob1.movie.MovieClass
import prj.mob1.prjmob1.retrofitUtil.models.CreditResponse
import prj.mob1.prjmob1.roomComponenets.models.CastRoomAdapter
import prj.mob1.prjmob1.roomComponenets.models.MovieRoomAdapter

// Created by sol on 14/06/2018.



class RoomDataUtil {

    companion object {
        //Save the movie in th DB
        fun addMovieToFav(act:Context, movie:MovieClass,callback: () -> Unit) {
            object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg voids: Void): Void? {
                    val db = CinFanDB.getInstance(act)
                    val dao = db?.movieDAO()
                    dao?.addMovie(MovieRoomAdapter(movie))

                    for(cast in movie.credits.cast)
                        db?.castDAO()!!.addCast(CastRoomAdapter(cast,movie.id))

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

        fun getFavMovieById(act:Context, movieId:Int,callback: (MovieClass?) -> Unit) {
            var movie:MovieClass? = null
//            var castList: List<CastRoomAdapter>?
            object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg voids: Void): Void? {
                    val db = CinFanDB.getInstance(act)
                    val dao = db?.movieDAO()
                    val movieAdapter = dao?.getMovie(movieId)

                    if(movieAdapter!= null) {
                        movie = MovieClass(movieAdapter)
                        val castList = db?.castDAO()?.getMovieCastList(movie!!.id)
                        val castCreditList = ArrayList<Cast>()
                        if(castList!=null){
                            for(cast in castList)
                                castCreditList.add(Cast(cast))
                            movie!!.credits = CreditResponse(castCreditList)
                        }

                    }


                    return null
                }

                override fun onPostExecute(result: Void?) {
                    Toast.makeText(act, "GET FAV MOVIE BY ID",Toast.LENGTH_SHORT).show()
                    callback(movie)
                }
            }.execute()

        }

    }
}
