package prj.mob1.prjmob1.Comment

import android.os.Parcel
import android.os.Parcelable
import prj.mob1.prjmob1.roomComponenets.models.CommentRoomAdapter

/**
 * Created by sol on 18/06/2018.
 */
class Review (  val id:String,
                val author:String,
                val content:String,
                val relatedId: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {
    }

    constructor(review : CommentRoomAdapter): this(review.id, review.author, review.content,review.relatedId)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(author)
        parcel.writeString(content)
        parcel.writeInt(relatedId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }
    }
}