package com.example.timakrework.Fragmenty.Transaction_history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.timakrework.R
import com.example.timakrework.data.Transactions
import com.example.timakrework.databinding.CustomRow2Binding
import com.example.timakrework.databinding.CustomRowBinding

class NewAdapter:RecyclerView.Adapter<NewAdapter.MyViewHolder>() {
    private var transactionList= emptyList<Transactions>()

    class MyViewHolder(binding: CustomRow2Binding):RecyclerView.ViewHolder(binding.root) {
        var titleView: TextView = binding.viewFrom
        var descView: TextView = binding.viewTo
        var id:TextView=binding.idTransaction
        var amount:TextView=binding.amountMoney
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding= CustomRow2Binding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val currentItem=transactionList[position]
        holder.titleView.text=currentItem.StellarUserID_from
        holder.descView.text=currentItem.StellarUserID_to
        holder.id.text=currentItem.id.toString()
        holder.amount.text=currentItem.amount
    }
    fun setData(transactions: List<Transactions>){
        this.transactionList=transactions
        notifyDataSetChanged()
    }

}
