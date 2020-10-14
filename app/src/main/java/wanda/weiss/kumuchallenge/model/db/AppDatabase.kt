package wanda.weiss.kumuchallenge.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import wanda.weiss.kumuchallenge.model.db.dao.TracksDao
import wanda.weiss.kumuchallenge.model.pojo.Result


    //Providing one general instance of Room Database

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tracksDao(): TracksDao

    companion object {

        //Makes it immediately visible to other thread
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: getDatabase(context).also { INSTANCE = it }
        }

        fun getDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "tracks_database").build()
    }
}
