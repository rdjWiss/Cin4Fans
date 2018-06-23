package prj.mob1.prjmob1.ListItem

import android.os.Parcel
import android.os.Parcelable


/**
 * Created by LE on 20/04/2018.
 */

/*data class Item (val id:Int,val poster: String, val year:String, val title:String, val tag:String)
{
    constructor() : this(200,"","","","")
}*/


data class Item (val id:Int,val poster: String?,val title:String):Parcelable
{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString()) {
    }

    constructor() : this(200,"","")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(poster)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}
