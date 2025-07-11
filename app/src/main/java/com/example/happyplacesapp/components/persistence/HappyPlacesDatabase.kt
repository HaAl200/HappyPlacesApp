package com.example.happyplacesapp.components.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.happyplacesapp.components.HappyPlace
import com.example.happyplacesapp.components.data.HappyPlaceDao

@Database(entities = [HappyPlace::class], version = 1)
abstract class HappyPlacesDatabase : RoomDatabase() {

    abstract fun happyPlacesDao(): HappyPlaceDao

    companion object {
        @Volatile
        private var INSTANCE: HappyPlacesDatabase? = null

        fun getDatabase(context: Context): HappyPlacesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HappyPlacesDatabase::class.java,
                    "happy_places_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
