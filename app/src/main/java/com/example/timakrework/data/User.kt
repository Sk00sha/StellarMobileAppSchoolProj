package com.example.timakrework.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="User_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val StellarUserID :String,
    val HashSecretSeed :String,
    val balance:String
)



