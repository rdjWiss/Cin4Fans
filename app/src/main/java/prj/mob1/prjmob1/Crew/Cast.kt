package prj.mob1.prjmob1.Crew

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import prj.mob1.prjmob1.roomComponenets.models.CastRoomAdapter

/**
 * Created by sol on 18/06/2018.
*/
data class Cast (@SerializedName("id") val id:Int,
                 @SerializedName("cast_id") val castId:Int,
                 @SerializedName("name") val name:String,
                 @SerializedName("character") val character:String,
                 @SerializedName("profile_path") val image:String)
   : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    constructor():this(0,0,"NA","NA","NA")

    constructor(cast: CastRoomAdapter):this(cast.id,cast.castId, cast.name,cast.character, cast.image)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(castId)
        parcel.writeString(name)
        parcel.writeString(character)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cast> {
        override fun createFromParcel(parcel: Parcel): Cast {
            return Cast(parcel)
        }

        override fun newArray(size: Int): Array<Cast?> {
            return arrayOfNulls(size)
        }
    }


}