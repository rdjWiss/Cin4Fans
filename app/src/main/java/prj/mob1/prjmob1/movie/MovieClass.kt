package prj.mob1.prjmob1.movie

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import prj.mob1.prjmob1.retrofitUtil.models.CreditResponse
import prj.mob1.prjmob1.retrofitUtil.models.ReviewsResponse
import prj.mob1.prjmob1.retrofitUtil.models.SimilarMoviesResponse
import prj.mob1.prjmob1.retrofitUtil.models.VideoResponse

/**
 * Created by sol on 25/03/2018.
 */
@Entity(tableName = "Movie")
data class MovieClass(@SerializedName("id") @PrimaryKey val id: Int,
                      @SerializedName("original_title") val title: String,
                      @SerializedName("release_date") val releaseDate: String,
                      var year: String,
                      @SerializedName("tagline") val tags: String,
                      @SerializedName("runtime") val duration: Int,
                      @SerializedName("poster_path") val posterId: String,
                      @SerializedName("backdrop_path") val imagePath: String,
                      @SerializedName("overview") var overview: String,
                      @SerializedName("vote_average") var rating: Double,
                      @SerializedName("vote_count") var voteCount: Int,
                      @SerializedName("credits") var credits: CreditResponse,
                      @SerializedName("similar") var similar: SimilarMoviesResponse,
                      @SerializedName("reviews") var reviews: ReviewsResponse,
                      @SerializedName("videos")  var videos:VideoResponse
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
            parcel.readParcelable(VideoResponse::class.java.classLoader)) {
    }

    @Ignore
    constructor() : this(0, "NA", "NA", "NA", "NA", 0, "NA","NA", "NA", 0.0, 0, CreditResponse(0, listOf(), listOf()),
            SimilarMoviesResponse(0, listOf()),ReviewsResponse(0, listOf()), VideoResponse(listOf()))

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
        parcel.writeParcelable(credits, flags)
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

