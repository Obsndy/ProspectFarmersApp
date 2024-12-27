package com.obsndy.prospectfarmersapp.data.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.obsndy.prospectfarmersapp.data.dao.FarmerDao
import com.obsndy.prospectfarmersapp.models.Farmer

@Database(entities = [Farmer::class], version = 1, exportSchema = false)
abstract class ProspectDatabase:RoomDatabase() {
    abstract fun farmerDao(): FarmerDao
}