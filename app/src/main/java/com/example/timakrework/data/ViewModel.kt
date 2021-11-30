package com.example.timakrework.data

import android.app.Application
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.timakrework.Encryptor
import com.example.timakrework.Fragmenty.MakeTransaction.MakeTransaction
import com.example.timakrework.RegisterStellar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ViewModel(application:Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<Contacts>>
    val readAllAccounts: LiveData<List<String>>
    val readAllContactsStrings:LiveData<List<String>>
    val readAllTransactions:LiveData<List<Transactions>>
    val readAllUsers:LiveData<List<User>>
    private val repository: UserRepository
    init {
        val our_dao=UserDatabase.getDatabase(application).DatabaseDao()
        repository=UserRepository(our_dao)
        readAllData=repository.all_contacts
        readAllAccounts=repository.all_data
        readAllContactsStrings=repository.all_contacts_ids
        readAllTransactions=repository.all_transactions
        readAllUsers=repository.allusers
    }

    fun addContact(contacts: Contacts){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addcontact(contacts)
            println("ADED NEW CONTACT")
        }
    }
    fun addUser(user:User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.adduser(user)
            println("ADED NEW USER")
            repository.getall_users()
        }
    }

    fun addTransaction(transaction:Transactions){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addtransaction(transaction)
            println("ADED NEW TRANSACTION")
            println(readAllTransactions)
        }
    }

    fun getBalance(userid:String){
        viewModelScope.launch(Dispatchers.IO) {
            println(repository.get_balance(userid))
        }
    }

    fun getSingleUser(userid:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.get_single_user(userid)
        }
    }

     fun updateUser(userid:String){
        var stellar_controller=RegisterStellar()
        viewModelScope.launch(Dispatchers.IO) {
            val user=repository.get_single_user(userid)
            val stellar_balance=stellar_controller.check_balance_stellar(userid)
            repository.updateUser(User(user.id,user.StellarUserID,user.HashSecretSeed,stellar_balance))
           println("Balance Updated")
        }
    }
    fun makeTransaction(pin:String,user_id:String,reciever_id:String,amount:String){
        var stellar_controller=RegisterStellar()
        viewModelScope.launch(Dispatchers.IO) {
            val user=repository.get_single_user(user_id)
            val seed_hash=user.HashSecretSeed
            var encryptor= Encryptor()
            val hash = encryptor.toMD5(pin) //zahashujeme nas pin
            val secret_seed=encryptor.decryptWithAES(hash,seed_hash) //desifrujeme secret seed z db
            println(secret_seed)
                repository.addtransaction(Transactions(0, user_id, reciever_id, amount))
                stellar_controller.SendPayment(secret_seed.toString(), reciever_id, amount)
                val stellar_balance = stellar_controller.check_balance_stellar(user_id)
                repository.updateUser(
                    User(
                        user.id,
                        user.StellarUserID,
                        user.HashSecretSeed,
                        stellar_balance
                    )
                )

                println("Balance Updated")

        }
    }

}