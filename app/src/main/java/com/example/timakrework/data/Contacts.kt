package com.example.timakrework.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Contacts_table")
data class Contacts (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val StellarUserID :String
)