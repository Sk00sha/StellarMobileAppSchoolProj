package com.example.timakrework.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Transactions_table")
data class Transactions(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val StellarUserID_from :String,
    val StellarUserID_to :String,
    val amount:String
)
