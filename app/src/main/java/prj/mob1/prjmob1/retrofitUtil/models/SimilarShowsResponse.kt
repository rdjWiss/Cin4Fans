package prj.mob1.prjmob1.retrofitUtil.models

import android.os.Parcel
import android.os.Parcelable
import prj.mob1.prjmob1.show.TVShow

/**
 * Created by sol on 22/06/2018.
 */
class SimilarShowsResponse( val page: Int,
                            val results: List<TVShow>): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.createTypedArrayList(TVShow)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(page)
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SimilarShowsResponse> {
        override fun createFromParcel(parcel: Parcel): SimilarShowsResponse {
            return SimilarShowsResponse(parcel)
        }

        override fun newArray(size: Int): Array<SimilarShowsResponse?> {
            return arrayOfNulls(size)
        }
    }

}