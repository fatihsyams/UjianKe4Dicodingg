package com.example.ujianke4dicoding.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ujianke4dicoding.response.ResultsItem
import com.example.ujianke4dicoding.response.responsetv.ResultsItemss


@Database(entities = [ResultsItem::class, ResultsItemss::class], version = 7)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvDao() : TvDao

    companion object{
        var INSTANCE : AppDatabase? = null

        fun getDB(applicationContext: Context): AppDatabase {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    "database-name")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build()
            }
            return INSTANCE as AppDatabase
        }
    }

}