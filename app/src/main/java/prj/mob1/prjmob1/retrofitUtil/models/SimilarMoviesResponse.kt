package prj.mob1.prjmob1.retrofitUtil.models

import android.os.Parcel
import android.os.Parcelable
import prj.mob1.prjmob1.movie.MovieClass

/**
 * Created by sol on 18/06/2018.
 */
class SimilarMoviesResponse( val page: Int,
                             val results: List<MovieClass>):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.createTypedArrayList(MovieClass)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(page)
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SimilarMoviesResponse> {
        override fun createFromParcel(parcel: Parcel): SimilarMoviesResponse {
            return SimilarMoviesResponse(parcel)
        }

        override fun newArray(size: Int): Array<SimilarMoviesResponse?> {
            return arrayOfNulls(size)
        }
    }
}