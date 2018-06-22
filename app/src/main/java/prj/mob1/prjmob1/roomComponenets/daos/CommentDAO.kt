package prj.mob1.prjmob1.roomComponenets.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import prj.mob1.prjmob1.roomComponenets.models.CastRoomAdapter
import prj.mob1.prjmob1.roomComponenets.models.CommentRoomAdapter

/**
 * Created by sol on 22/06/2018.
 */
@Dao
interface CommentDAO {

    @Query("SELECT * FROM Comment WHERE related_id=:movieId ")
    fun getMovieCommentList(movieId:Int): List<CommentRoomAdapter>

    @Query("SELECT * FROM Comment")
    fun getCommentList(): List<CommentRoomAdapter>

    @Insert
    fun addComment(comment : CommentRoomAdapter)

    @Delete
    fun delComment(comment: CommentRoomAdapter)
}