package prj.mob1.prjmob1.roomComponenets.models

import android.arch.persistence.room.*
import prj.mob1.prjmob1.Comment.Review

/**
 * Created by sol on 22/06/2018.
 */
@Entity(tableName = "Comment",
        foreignKeys = arrayOf(ForeignKey(entity = MovieRoomAdapter::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("related_id"),
                onDelete = ForeignKey.CASCADE)))
class CommentRoomAdapter (@PrimaryKey() val id:String,
                          @ColumnInfo(name ="author") val author:String,
                          @ColumnInfo(name ="content") val content:String,
                          @ColumnInfo(name ="related_id") val relatedId: Int) {
    @Ignore
    constructor(review:Review, relatedId:Int): this(review.id, review.author, review.content,relatedId)
}