/*
package prj.mob1.prjmob1.roomComponenets

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import prj.mob1.prjmob1.movie.MovieClass

*/
/**
 * Created by sol on 14/06/2018.
 *//*

@Database(entities = arrayOf(MovieClass::class), version = 1)
abstract class CinFanDB() : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO

    //Singleton
    companion object {
        private var instance: CinFanDB? = null

        fun getInstance(context: Context): CinFanDB? {
            if (instance == null) {

                instance = Room.databaseBuilder(context.getApplicationContext(),
                        CinFanDB::class.java, "cinfan.db")
                        .build()

            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}*/
