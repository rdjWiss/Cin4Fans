package prj.mob1.prjmob1.roomComponenets.models

import android.arch.persistence.room.*
import prj.mob1.prjmob1.Crew.Cast

/**
 * Created by sol on 22/06/2018.
 */
@Entity(tableName = "Cast",
        foreignKeys = arrayOf(ForeignKey(entity = MovieRoomAdapter::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("related_id"),
                onDelete = ForeignKey.CASCADE)))
class CastRoomAdapter(@PrimaryKey()  val id:Int,
                      @ColumnInfo(name ="cast_id") val castId:Int,
                      @ColumnInfo(name ="name") val name:String,
                      @ColumnInfo(name ="character") val character:String,
                      @ColumnInfo(name ="profile_path") val image:String,
                      @ColumnInfo(name ="related_id") val relatedId:Int) {

    @Ignore
    constructor(cast: Cast,movieId:Int):this(cast.id,cast.castId, cast.name,cast.character, cast.image, movieId )
}