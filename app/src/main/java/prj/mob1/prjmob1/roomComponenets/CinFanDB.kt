package prj.mob1.prjmob1.roomComponenets

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import prj.mob1.prjmob1.roomComponenets.daos.MovieDAO
import prj.mob1.prjmob1.roomComponenets.models.MovieRoomAdapter

/**
 * Created by sol on 22/06/2018.
 */
@Database(entities = arrayOf(MovieRoomAdapter::class), version = 1, exportSchema = false)
abstract class CinFanDB() : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO

    companion object {
        private var instance: CinFanDB? = null

        fun getInstance(context: Context): CinFanDB? {
            if (instance == null) {

                instance = Room.databaseBuilder(context.applicationContext,
                        CinFanDB::class.java, "cinfan.db")
                        .build()

            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}