package prj.mob1.prjmob1.episode

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by sol on 27/03/2018.
 */
data class Episode (val episode_title: String, val num_episode:String,val title_show: String, val num_season: String, val date: String,
               val channel: String, val posterId: Int, var overview: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString()) {
    }

    constructor() : this("","","","","","",0,"")
    constructor(title:String, num :String, date:String): this(title,num,"","",date,"",0,"")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(episode_title)
        parcel.writeString(num_episode)
        parcel.writeString(title_show)
        parcel.writeString(num_season)
        parcel.writeString(date)
        parcel.writeString(channel)
        parcel.writeInt(posterId)
        parcel.writeString(overview)
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