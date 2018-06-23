package prj.mob1.prjmob1.retrofitUtil.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import prj.mob1.prjmob1.Crew.Cast
import prj.mob1.prjmob1.Crew.Crew
import prj.mob1.prjmob1.roomComponenets.models.CastRoomAdapter

/**
 * Created by sol on 18/06/2018.
 */
class CreditResponse(val id:Int,
                     @SerializedName("crew") var crew: List<Crew>
                     ,@SerializedName("cast") var cast:List<Cast>
                    )
    : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.createTypedArrayList(Crew),
            parcel.createTypedArrayList(Cast)) {
    }

    constructor(cast:List<Cast>): this(0, listOf(), cast)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeTypedList(crew)
        parcel.writeTypedList(cast)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CreditResponse> {
        override fun createFromParcel(parcel: Parcel): CreditResponse {
            return CreditResponse(parcel)
        }

        override fun newArray(size: Int): Array<CreditResponse?> {
            return arrayOfNulls(size)
        }
    }
}