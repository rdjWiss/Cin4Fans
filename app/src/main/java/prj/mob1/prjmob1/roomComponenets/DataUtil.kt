/*
package prj.mob1.prjmob1.roomComponenets

import android.content.Context
import android.graphics.Movie
import android.os.AsyncTask
import android.widget.Toast
import prj.mob1.prjmob1.movie.MovieClass

*/
/**
 * Created by sol on 14/06/2018.
 *//*

class DataUtil {

    companion object {
        //Save the movie in th DB
        fun addMovieToFav(act:Context, movie:MovieClass) {
            object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg voids: Void): Void? {
                    val db = CinFanDB.getInstance(act)
                    val dao = db?.movieDAO()
                    dao?.ajouter(movie)

                    return null
                }


                override fun onPostExecute(result: Void?) {
                    Toast.makeText(act, "Movie added to fav",Toast.LENGTH_SHORT).show()
                }
            }.execute()
        }

        fun getFavMovies(act:Context): List<MovieClass>? {
            var movies:List<MovieClass>? = null
            object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg voids: Void): Void? {
                    val db = CinFanDB.getInstance(act)
                    val dao = db?.movieDAO()
                    movies = dao?.getMovies()


                    return null
                }


                override fun onPostExecute(result: Void?) {
                    Toast.makeText(act, "List Movies",Toast.LENGTH_SHORT).show()
                }
            }.execute()
            return movies
        }

    }
}*/
