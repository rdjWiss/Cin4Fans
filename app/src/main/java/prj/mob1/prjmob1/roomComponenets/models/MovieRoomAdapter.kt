package prj.mob1.prjmob1.roomComponenets.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import prj.mob1.prjmob1.movie.MovieClass

/**
 * Created by sol on 22/06/2018.
 */
@Entity(tableName = "Movie")
class MovieRoomAdapter(@PrimaryKey() var id: Int,
                            @ColumnInfo(name = "title")var title: String,
                            @ColumnInfo(name = "release_date")var releaseDate: String,
                            var year: String,
                            @ColumnInfo(name = "tags") var tags: String,
                            @ColumnInfo(name = "duration")var duration: Int,
                            @ColumnInfo(name = "poster_path")var posterId: String,
                            @ColumnInfo(name = "image_path")var imagePath: String,
                            @ColumnInfo(name = "overview")var overview: String,
                            @ColumnInfo(name = "vote_average")var rating: Double,
                            @ColumnInfo(name = "vote_count")var voteCount: Int) {
    @Ignore
    constructor(movie:MovieClass):this(movie.id, movie.title, movie.releaseDate,movie.year, movie.tags,movie.duration, movie.posterId,
            movie.imagePath,movie.overview,movie.rating,movie.voteCount)


    @Ignore
    constructor():this(0,"NA","NA","NA","NA",0,"NA","NA","NA",0.0,0)
}