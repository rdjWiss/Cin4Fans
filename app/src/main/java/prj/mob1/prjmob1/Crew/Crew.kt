package prj.mob1.prjmob1.Crew

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by sol on 13/04/2018.
 */

data class Crew(@SerializedName("id") val id: Int,
                @SerializedName("name")val name: String,
                @SerializedName("department") val departement: String,
                @SerializedName("profile_path") val image: String,
                @SerializedName("credit_id") val creditId: String,
                @SerializedName("job") val job: String)

:Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    constructor():this(0,"","","","","")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(departement)
        parcel.writeString(image)
        parcel.writeString(creditId)
        parcel.writeString(job)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Crew> {
        override fun createFromParcel(parcel: Parcel): Crew {
            return Crew(parcel)
        }

        override fun newArray(size: Int): Array<Crew?> {
            return arrayOfNulls(size)
        }
    }

}
