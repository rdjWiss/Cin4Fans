package prj.mob1.prjmob1.Settings

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by LE on 22/06/2018.
 */
@Entity(tableName = "Genre")
data class ItemChoice(@SerializedName("id") @PrimaryKey val id: Int,
                      @SerializedName("name") val name: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString())


    @Ignore
    constructor() : this(0, "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<prj.mob1.prjmob1.Settings.ItemChoice> {
        override fun createFromParcel(parcel: Parcel): prj.mob1.prjmob1.Settings.ItemChoice {
            return ItemChoice(parcel)
        }

        override fun newArray(size: Int): Array<prj.mob1.prjmob1.Settings.ItemChoice?> {
            return arrayOfNulls(size)
        }
    }


}

