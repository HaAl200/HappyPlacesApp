package com.example.happyplacesapp.components.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.happyplacesapp.components.HappyPlace
import kotlinx.coroutines.flow.Flow


@Dao
interface HappyPlaceDao {

    @Query("SELECT * FROM happyplaces ORDER BY id DESC")
    fun getAllPlaces(): Flow<List<HappyPlace>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(place: HappyPlace)

    @Delete
    suspend fun deletePlace(place: HappyPlace)

    @Update
    suspend fun updatePlace(place: HappyPlace)
}
