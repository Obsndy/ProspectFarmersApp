package com.obsndy.prospectfarmersapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Farmer(
    @PrimaryKey(autoGenerate = false) val id: String,
    val name: String,
    val phoneNumber: String,
    val location: String,
)
