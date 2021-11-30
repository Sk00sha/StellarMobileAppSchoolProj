package com.example.timakrework.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao:DatabaseDao) {

    val all_data:LiveData<List<String>> =  userDao.readAllData()
    val all_contacts:LiveData<List<Contacts>> = userDao.readAllContacts()
    val all_contacts_ids:LiveData<List<String>> = userDao.readAllContactsString()
    val all_transactions:LiveData<List<Transactions>> = userDao.readAllTransactions()
    val allusers:LiveData<List<User>> = userDao.readAllUsers()

    suspend fun adduser(user:User){
        userDao.addUser(user)
    }
    suspend fun updateUser(user:User){
        userDao.updateuser(user)
    }
    suspend fun addcontact(contacts: Contacts){
        userDao.addContact(contacts)
    }
    suspend fun addtransaction(transaction: Transactions){
        userDao.addTransaction(transaction)
    }
    fun get_balance(id:String):String{
        return userDao.get_balance(id)
    }

    fun get_single_user(id:String):User{
        return userDao.get_single_user(id)
    }

    fun get_stellar_hash(id:String):String{
        return userDao.select_secret_seed(id)
    }

    fun getStellarIdFromUser():String{
        return userDao.stellarKey()
    }
    fun getall_users(){
        println(userDao.readAllData())
    }
}