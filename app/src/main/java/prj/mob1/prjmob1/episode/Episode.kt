package prj.mob1.prjmob1.episode

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by sol on 27/03/2018.
 */
data class Episode (@SerializedName("name") val episode_title: String,
                    @SerializedName("episode_number") val num_episode:String,
                    /*@SerializedName("id") */var title_show: String,
                    @SerializedName("season_number") val num_season: String,
                    @SerializedName("air_date") val date: String,
                    /*@SerializedName("id")*/ var channel: String,
                    @SerializedName("still_path") val posterId: String,
                    @SerializedName("overview") var overview: String,
                    @SerializedName("vote_count") var voteCount: Int,
                    @SerializedName("vote_average") var rating: Double
                    ) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readDouble()) {
    }

    constructor() : this("","","","","","","","",0,0.0)
    constructor(title:String, num :String, date:String): this(title,num,"","",date,"","","",0,0.0)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(episode_title)
        parcel.writeString(num_episode)
        parcel.writeString(title_show)
        parcel.writeString(num_season)
        parcel.writeString(date)
        parcel.writeString(channel)
        parcel.writeString(posterId)
        parcel.writeString(overview)
        parcel.writeInt(voteCount)
        parcel.writeDouble(rating)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Episode> {
        override fun createFromParcel(parcel: Parcel): Episode {
            return Episode(parcel)
        }

        override fun newArray(size: Int): Array<Episode?> {
            return arrayOfNulls(size)
        }
    }


}