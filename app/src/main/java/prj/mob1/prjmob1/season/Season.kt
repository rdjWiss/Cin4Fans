package prj.mob1.prjmob1.season

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import prj.mob1.prjmob1.episode.Episode
import prj.mob1.prjmob1.retrofitUtil.models.CreditResponse

/**
 * Created by sol on 27/03/2018.
 */
data class Season (@SerializedName("id") val id:Int,
                   @SerializedName("season_number") val num_season:String,
                    /*@SerializedName("id")*/ var title_show: String,
                   @SerializedName("episode_count") var nbr_episodes: Int,
                   @SerializedName("air_date") val date_begin: String,
                    /*@SerializedName("id")*/ val date_end: String,
                   @SerializedName("poster_path") val posterId: String,
                   /* @SerializedName("id")*/ val imageId: Int,
                   @SerializedName("overview") var overview: String,
                   @SerializedName("credits") var credits: CreditResponse,
                   @SerializedName("episodes") var episodes: List<Episode>) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readParcelable(CreditResponse::class.java.classLoader),
            parcel.createTypedArrayList(Episode)) {
    }

    constructor() : this(0,"","",0,"","","",0,"",CreditResponse(0, listOf(), listOf()), listOf())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(num_season)
        parcel.writeString(title_show)
        parcel.writeInt(nbr_episodes)
        parcel.writeString(date_begin)
        parcel.writeString(date_end)
        parcel.writeString(posterId)
        parcel.writeInt(imageId)
        parcel.writeString(overview)
        parcel.writeParcelable(credits, flags)
        parcel.writeTypedList(episodes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Season> {
        override fun createFromParcel(parcel: Parcel): Season {
            return Season(parcel)
        }

        override fun newArray(size: Int): Array<Season?> {
            return arrayOfNulls(size)
        }
    }

}