package prj.mob1.prjmob1.ListItem

import com.google.gson.annotations.SerializedName
import prj.mob1.prjmob1.movie.MovieClass
import prj.mob1.prjmob1.show.TVShow

/**
 * Created by LE on 20/06/2018.
 */
data class ListShow
(
        @SerializedName("results")
        val shows: List<TVShow> = ArrayList<TVShow>()
)