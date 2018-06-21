package prj.mob1.prjmob1.retrofitUtil.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by sol on 21/06/2018.
 */
/*

"videos":{
"results":[
{"id":"533ec652c3a36854480001b1",
"iso_639_1":"en",
"iso_3166_1":"US",
"key":"N1XmtdMZdL8",
"name":"Star Trek IX: Insurrection - Trailer",
"site":"YouTube","size":720,"type":"Trailer"}
]}}
 */
class VideoResponse (val results:List<Video>):Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Video)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VideoResponse> {
        override fun createFromParcel(parcel: Parcel): VideoResponse {
            return VideoResponse(parcel)
        }

        override fun newArray(size: Int): Array<VideoResponse?> {
            return arrayOfNulls(size)
        }
    }
}

data class Video (  val id:String,
                    val key:String,
                    val name:String,
                    val site:String,
                    val type:String):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(key)
        parcel.writeString(name)
        parcel.writeString(site)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Video> {
        override fun createFromParcel(parcel: Parcel): Video {
            return Video(parcel)
        }

        override fun newArray(size: Int): Array<Video?> {
            return arrayOfNulls(size)
        }
    }
}

