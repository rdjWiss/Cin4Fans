package prj.mob1.prjmob1.roomComponenets.daos

import android.arch.persistence.room.*
import prj.mob1.prjmob1.movie.MovieClass
import prj.mob1.prjmob1.roomComponenets.models.MovieRoomAdapter

// * Created by sol on 14/06/2018.

@Dao
interface MovieDAO {
    @Query("SELECT * FROM Movie")
    fun getMoviesList(): List<MovieRoomAdapter>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getMovie(id: Int): MovieRoomAdapter

    @Insert
    fun addMovie(movie : MovieRoomAdapter)

    @Delete
    fun delMovie(movie: MovieRoomAdapter)
}
