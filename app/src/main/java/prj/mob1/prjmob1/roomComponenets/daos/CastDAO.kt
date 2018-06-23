package prj.mob1.prjmob1.roomComponenets.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import prj.mob1.prjmob1.roomComponenets.models.CastRoomAdapter

/**
 * Created by sol on 22/06/2018.
 */
@Dao
interface CastDAO {

    @Query("SELECT * FROM Cast WHERE related_id=:movieId ")
    fun getMovieCastList(movieId:Int): List<CastRoomAdapter>

    @Query("SELECT * FROM Cast")
    fun getCastList(): List<CastRoomAdapter>

    @Insert
    fun addCast(cast : CastRoomAdapter)

    @Delete
    fun delCast(cast: CastRoomAdapter)
}