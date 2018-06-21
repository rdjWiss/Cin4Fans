package prj.mob1.prjmob1.ListItem

import com.google.gson.annotations.SerializedName
import prj.mob1.prjmob1.movie.MovieClass


/**
 * Created by LE on 19/06/2018.
 */

data class ListMovies (
    @SerializedName("results")
     val movies: List<MovieClass> = ArrayList<MovieClass>()
)