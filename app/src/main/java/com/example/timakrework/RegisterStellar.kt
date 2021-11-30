package com.example.timakrework

import java.io.InputStream
import java.net.URL
import java.util.*
import android.net.Network
import org.stellar.sdk.*
import org.stellar.sdk.Network.TESTNET
import org.stellar.sdk.responses.AccountResponse
import org.stellar.sdk.responses.SubmitTransactionResponse
import org.stellar.sdk.responses.operations.PaymentOperationResponse

import org.stellar.sdk.AssetTypeCreditAlphaNum

import org.stellar.sdk.AssetTypeNative
import org.stellar.sdk.responses.operations.OperationResponse
import org.stellar.sdk.requests.EventListener;
import org.stellar.sdk.requests.PaymentsRequestBuilder
import java.lang.StringBuilder


class RegisterStellar {
    //Thread wrapper for registerring stellar account
    fun register_stellar():List<String>{
        var data=listOf<String>()
    val thread = Thread {
        try {
            data=CreateAccount()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
        thread.start()
        thread.join()
        return data
    }
    //Thread wrapper for checking balance
    fun check_balance_stellar(id:String):String{
        var balance:String=""
        val thread = Thread {
            try {
                balance=check_balance(id)

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        thread.start()
        thread.join()
        return balance
    }

    fun add_existing_account(secret_seed:String):List<String>{
        val account_id=KeyPair.fromSecretSeed(secret_seed)
        val return_list = listOf<String>(account_id.accountId,secret_seed)
        return return_list
    }
    //DAT PREC
    fun CreateAccount():List<String> {

        val pair = KeyPair.random();

        val secret_seed=(String(pair.getSecretSeed()));

        val account_id=(pair.getAccountId());

        val friendbotUrl = "https://friendbot.stellar.org/?addr=" + pair.accountId
        println("SECRET SEED "+secret_seed+"PUBLIC KEY"+account_id)

        val response: InputStream = URL(friendbotUrl).openStream()
        val body: String = Scanner(response, "UTF-8").useDelimiter("\\A").next()
        val server = Server("https://horizon-testnet.stellar.org")
        val account = server.accounts().account(account_id)
        var balance_return=""
        println("Balances for account " + account_id)
        for (balance in account.balances) {
            println("Type:"+balance.assetType+"Code:"+balance.assetCode+" Balance:"+balance.balance)
            balance_return=balance.balance
        }
       return listOf(account_id,secret_seed,balance_return)

    }
    fun check_balance(account_id:String):String{
        val server = Server("https://horizon-testnet.stellar.org")
        val account = server.accounts().account(account_id)
        for (balance in account.balances) {
            println("Type:"+balance.assetType+"Code:"+balance.assetCode+" Balance:"+balance.balance)
            return balance.balance
        }
        return ""
    }

    fun SendPayment(sourceInput: String, destinationInput: String,amount:String){
        val server = Server("https://horizon-testnet.stellar.org")

        val source: KeyPair = KeyPair.fromSecretSeed(sourceInput)
        val destination: KeyPair = KeyPair.fromAccountId(destinationInput)

        server.accounts().account(destination.getAccountId())
        val sourceAccount: AccountResponse = server.accounts().account(source.getAccountId())
        val transaction: Transaction = Transaction.Builder(sourceAccount, TESTNET)
            .addOperation(
                PaymentOperation.Builder(
                    destination.getAccountId(),
                    AssetTypeNative(),
                    amount
                ).build()
            )

            .addMemo(Memo.text("Test Transaction")) // Wait a maximum of three minutes for the transaction
            .setTimeout(180) // Set the amount of lumens you're willing to pay per operation to submit your transaction
            .setBaseFee(Transaction.MIN_BASE_FEE)
            .build()

        transaction.sign(source)

        try {
            val response: SubmitTransactionResponse = server.submitTransaction(transaction)
            println("Success!")
            println(response)
        } catch (e: java.lang.Exception) {
            println("Something went wrong!")
            println(e.message)

        }

    }/*
    fun receivePayment(){
        val server = Server("https://horizon-testnet.stellar.org")
        val account = KeyPair.fromAccountId("GC2BKLYOOYPDEFJKLKY6FNNRQMGFLVHJKQRGNSSRRGSMPGF32LHCQVGF")

// Create an API call to query payments involving the account.

// Create an API call to query payments involving the account.
        val paymentsRequest = server.payments().forAccount(account.accountId)

        val lastToken= null
        if (lastToken != null) {
            paymentsRequest.cursor(lastToken)
        }

// `stream` will send each recorded payment, one by one, then keep the
// connection open and continue to send you new payments as they occur.

// `stream` will send each recorded payment, one by one, then keep the
// connection open and continue to send you new payments as they occur.
        paymentsRequest.stream(EventListener<OperationResponse> {
            fun onEvent(payment: OperationResponse) {
                // Record the paging token so we can start from here next time.
                //savePagingToken(payment.pagingToken)

                // The payments stream includes both sent and received payments. We only
                // want to process received payments here.
                if (payment is PaymentOperationResponse) {
                    if ((payment as PaymentOperationResponse).to == account.accountId) {
                        return
                    }
                    val amount = payment.amount
                    val asset = payment.asset
                    val assetName: String
                    assetName = if (asset.equals(AssetTypeNative())) {
                        "lumens"
                    } else {
                        val assetNameBuilder = StringBuilder()
                        assetNameBuilder.append((asset as AssetTypeCreditAlphaNum).code)
                        assetNameBuilder.append(":")
                        assetNameBuilder.append((asset as AssetTypeCreditAlphaNum).issuer)
                        assetNameBuilder.toString()
                    }
                    val output = StringBuilder()
                    output.append(amount)
                    output.append(" ")
                    output.append(assetName)
                    output.append(" from ")
                    output.append(payment.from)
                    println(output.toString())
                }
            }

            fun onFailure(optional: Optional<Throwable?>?, optional1: Optional<Int?>?) {}
        })
    }*/
}