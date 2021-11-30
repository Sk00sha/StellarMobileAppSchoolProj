package com.example.timakrework.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addContact(contact:Contacts)

    @Update
    suspend fun updateuser(user:User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user:User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTransaction(transactions: Transactions)

    @Query("SELECT StellarUserID FROM user_table")
    fun readAllData(): LiveData<List<String>>

    @Query("SELECT * FROM user_table order by id asc")
    fun readAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM transactions_table")
    fun readAllTransactions(): LiveData<List<Transactions>>

    @Query("SELECT HashSecretSeed FROM user_table WHERE StellarUserID=:user_id")
    fun select_secret_seed(user_id:String): String

    @Query("SELECT * FROM contacts_table")
    fun readAllContacts(): LiveData<List<Contacts>>

    @Query("SELECT StellarUserID FROM contacts_table")
    fun readAllContactsString(): LiveData<List<String>>

    @Query("SELECT HashSecretSeed FROM user_table")
    fun stellarKey(): String

    @Query("SELECT balance FROM user_table WHERE StellarUserID=:user_id")
    fun get_balance(user_id:String): String

    @Query("SELECT * FROM user_table WHERE StellarUserID=:user_id")
    fun get_single_user(user_id:String): User

}