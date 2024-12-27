package com.obsndy.prospectfarmersapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.obsndy.prospectfarmersapp.models.Farmer
import kotlinx.coroutines.flow.Flow

@Dao
interface FarmerDao {

    @Insert
    suspend fun insert(farmer: Farmer)

    @Query("SELECT * FROM farmer WHERE id = :id")
    fun getById(id: String): Flow<Farmer>

    @Query("SELECT * FROM farmer")
    fun getAll(): Flow<List<Farmer>>

    @Query("SELECT * FROM farmer WHERE name LIKE :name")
    fun getByName(name: String): Flow<List<Farmer>>

    @Update
    suspend fun update(farmer: Farmer)

    @Delete
    suspend fun delete(farmer: Farmer)
}