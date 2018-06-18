package prj.mob1.prjmob1.retrofitUtil.models

import android.os.Parcel
import android.os.Parcelable
import prj.mob1.prjmob1.Comment.Review

/**
 * Created by sol on 18/06/2018.
 */
class ReviewsResponse(val page:Int,
                      val results:List<Review>):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.createTypedArrayList(Review)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(page)
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReviewsResponse> {
        override fun createFromParcel(parcel: Parcel): ReviewsResponse {
            return ReviewsResponse(parcel)
        }

        override fun newArray(size: Int): Array<ReviewsResponse?> {
            return arrayOfNulls(size)
        }
    }
}