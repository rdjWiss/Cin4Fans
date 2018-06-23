package prj.mob1.prjmob1.show

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import prj.mob1.prjmob1.season.Season
import prj.mob1.prjmob1.episode.Network
import prj.mob1.prjmob1.retrofitUtil.models.*


/**
 * Created by sol on 26/03/2018.
 */

data class TVShow(@SerializedName("id") val id:Int,
                  @SerializedName("name") val title:String,
                  @SerializedName("number_of_episodes") val nbr_episodes: Int,
                  var tags: String,
                  @SerializedName("episode_run_time") val duration: IntArray,
                  @SerializedName("poster_path") val posterId: String,
                  @SerializedName("backdrop_path") val imagePath: String,
                  @SerializedName("overview") var overview: String,
                  @SerializedName("vote_count") var voteCount: Int,
                  @SerializedName("vote_average") var rating: Double,
                  @SerializedName("seasons") var seasons: List<Season>,
                  @SerializedName("credits") var credits: CreditResponse,
                  @SerializedName("similar") var similar: SimilarShowsResponse,
                  @SerializedName("reviews") var reviews: ReviewsResponse,
                  @SerializedName("networks") var networks: List<Network>,
                  @SerializedName("videos")  var videos: VideoResponse,
                  @SerializedName("genres")  var genres:List<Genre>
                    ) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.createIntArray(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.createTypedArrayList(Season),
            parcel.readParcelable(CreditResponse::class.java.classLoader),
            parcel.readParcelable(SimilarShowsResponse::class.java.classLoader),
            parcel.readParcelable(ReviewsResponse::class.java.classLoader),
            parcel.createTypedArrayList(Network),
            parcel.readParcelable(VideoResponse::class.java.classLoader),
            parcel.createTypedArrayList(Genre)) {
    }

    constructor() : this(0,"NA",0,"NA", intArrayOf(),"NA","NA","NA",0,0.0, listOf<Season>(),CreditResponse(0, listOf(), listOf()),
            SimilarShowsResponse(0, listOf()),ReviewsResponse(0, listOf()), listOf(),VideoResponse(listOf()),listOf())


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeInt(nbr_episodes)
        parcel.writeString(tags)
        parcel.writeIntArray(duration)
        parcel.writeString(posterId)
        parcel.writeString(imagePath)
        parcel.writeString(overview)
        parcel.writeInt(voteCount)
        parcel.writeDouble(rating)
        parcel.writeTypedList(genres)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TVShow> {
        override fun createFromParcel(parcel: Parcel): TVShow {
            return TVShow(parcel)
        }

        override fun newArray(size: Int): Array<TVShow?> {
            return arrayOfNulls(size)
        }
    }
}