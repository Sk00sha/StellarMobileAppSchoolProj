package com.example.timakrework.Fragmenty.Accounts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.timakrework.Fragmenty.Transaction_history.NewAdapter
import com.example.timakrework.R
import com.example.timakrework.data.Transactions
import com.example.timakrework.data.User
import com.example.timakrework.databinding.CustomUserRowBinding

class AccountAdapter: RecyclerView.Adapter<AccountAdapter.MyViewHolder>() {
    private var accountlist= emptyList<User>()

    class MyViewHolder(binding: CustomUserRowBinding): RecyclerView.ViewHolder(binding.root) {
        var title: TextView = binding.AccountId
        var descView: TextView = binding.StellarUserId
        var amount: TextView =binding.balanceNumber
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=CustomUserRowBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return accountlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=accountlist[position]
        holder.title.text=currentItem.id.toString()
        holder.descView.text=currentItem.StellarUserID
        holder.amount.text=currentItem.balance
    }

    fun setData(users: List<User>){
        if(!users.isEmpty()) {
            this.accountlist = users
            notifyDataSetChanged()
        }
    }

}