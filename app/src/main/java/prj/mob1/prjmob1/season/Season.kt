package prj.mob1.prjmob1.season

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by sol on 27/03/2018.
 */
data class Season (val num_season:String,val title_show: String, val nbr_episodes: Int, val date_begin: String,
              val date_end: String, val posterId: Int,val imageId: Int, var overview: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString()) {
    }

    constructor() : this("","",0,"","",0,0,"")
    constructor(num: String, nbr_episodes:Int): this(num,"",nbr_episodes,"","",0,0,"")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(num_season)
        parcel.writeString(title_show)
        parcel.writeInt(nbr_episodes)
        parcel.writeString(date_begin)
        parcel.writeString(date_end)
        parcel.writeInt(posterId)
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