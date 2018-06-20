package prj.mob1.prjmob1.season

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by sol on 27/03/2018.
 */
data class Season ( @SerializedName("id") val id:Int,
                    @SerializedName("season_number") val num_season:String,
                    /*@SerializedName("id")*/ val title_show: String,
                    @SerializedName("episode_count") val nbr_episodes: Int,
                    @SerializedName("air_date") val date_begin: String,
                    /*@SerializedName("id")*/ val date_end: String,
                    @SerializedName("poster_path") val posterId: String,
                   /* @SerializedName("id")*/ val imageId: Int,
                    /*@SerializedName("id")*/ var overview: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString()) {
    }

    constructor() : this(0,"","",0,"","","",0,"")
    constructor(num: String, nbr_episodes:Int): this(0,num,"",nbr_episodes,"","","",0,"")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(num_season)
        parcel.writeString(title_show)
        parcel.writeInt(nbr_episodes)
        parcel.writeString(date_begin)
        parcel.writeString(date_end)
        parcel.writeString(posterId)
        parcel.writeInt(imageId)
        parcel.writeString(overview)
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