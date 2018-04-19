package prj.mob1.prjmob1.Person

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by sol on 16/04/2018.
 */
data class Person (val nom:String, val birthday:String, val origin : String, val biography:String,
              val imageId: Int, val posterId:Int) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nom)
        parcel.writeString(birthday)
        parcel.writeString(origin)
        parcel.writeString(biography)
        parcel.writeInt(imageId)
        parcel.writeInt(posterId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }

    constructor() : this("","","","",0,0)
}