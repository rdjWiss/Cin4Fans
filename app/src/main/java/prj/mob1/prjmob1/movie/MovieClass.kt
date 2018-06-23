package prj.mob1.prjmob1.movie

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import prj.mob1.prjmob1.retrofitUtil.models.*
import prj.mob1.prjmob1.roomComponenets.models.CastRoomAdapter
import prj.mob1.prjmob1.roomComponenets.models.MovieRoomAdapter

/**
 * Created by sol on 25/03/2018.
 */
data class MovieClass(@SerializedName("id") val id: Int,
                      @SerializedName("original_title") val title: String,
                      @SerializedName("release_date") val releaseDate: String,
                      var year: String,
                      /*@SerializedName("tagline")*/ var tags: String,
                      @SerializedName("runtime") val duration: Int,
                      @SerializedName("poster_path") val posterId: String,
                      @SerializedName("backdrop_path") val imagePath: String,
                      @SerializedName("overview") var overview: String,
                      @SerializedName("vote_average") var rating: Double,
                      @SerializedName("vote_count") var voteCount: Int,
                      @SerializedName("credits") var credits: CreditResponse,
                      @SerializedName("similar") var similar: SimilarMoviesResponse,
                      @SerializedName("reviews") var reviews: ReviewsResponse,
                      @SerializedName("videos")  var videos:VideoResponse,
                      @SerializedName("genres")  var genres:List<Genre>,
                      @SerializedName("genre_ids") var genreIds:IntArray
                      ) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readParcelable(CreditResponse::class.java.classLoader),
            parcel.readParcelable(SimilarMoviesResponse::class.java.classLoader),
            parcel.readParcelable(ReviewsResponse::class.java.classLoader),
            parcel.readParcelable(VideoResponse::class.java.classLoader),
            parcel.createTypedArrayList(Genre),
            parcel.createIntArray()) {
    }

    constructor() : this(0, "NA", "NA", "NA", "NA", 0, "NA","NA", "NA", 0.0, 0, CreditResponse(0, listOf(), listOf()),
            SimilarMoviesResponse(0, listOf()),ReviewsResponse(0, listOf()),
            VideoResponse(listOf()),listOf(), intArrayOf())

    constructor(movie:MovieRoomAdapter?)
            :this(movie!!.id,movie.title,movie.releaseDate,movie.year,movie.tags,movie.duration,
            movie.posterId,movie.imagePath,movie.overview,movie.rating,movie.voteCount,CreditResponse(0, listOf(), listOf()),
            SimilarMoviesResponse(0, listOf()),ReviewsResponse(0, listOf()), VideoResponse(listOf()),listOf(),
            intArrayOf())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(releaseDate)
        parcel.writeString(year)
        parcel.writeString(tags)
        parcel.writeInt(duration)
        parcel.writeString(posterId)
        parcel.writeString(imagePath)
        parcel.writeString(overview)
        parcel.writeDouble(rating)
        parcel.writeInt(voteCount)
        /*parcel.writeParcelable(credits, flags)
        parcel.writeParcelable(similar, flags)
        parcel.writeParcelable(reviews, flags)
        parcel.writeParcelable(videos, flags)*/
        parcel.writeTypedList(genres)
        parcel.writeIntArray(genreIds)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieClass> {
        override fun createFromParcel(parcel: Parcel): MovieClass {
            return MovieClass(parcel)
        }

        override fun newArray(size: Int): Array<MovieClass?> {
            return arrayOfNulls(size)
        }
    }




}

