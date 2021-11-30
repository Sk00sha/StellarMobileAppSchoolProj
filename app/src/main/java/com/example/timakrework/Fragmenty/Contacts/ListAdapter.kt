package com.example.timakrework.Fragmenty.Contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.timakrework.Fragmenty.Accounts.AccountAdapter
import com.example.timakrework.R
import com.example.timakrework.data.Contacts
import com.example.timakrework.databinding.CustomRowBinding
import com.example.timakrework.databinding.CustomUserRowBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var contactList= emptyList<Contacts>()
    class MyViewHolder(binding: CustomRowBinding):RecyclerView.ViewHolder(binding.root) {
        var titleView: TextView = binding.idTxt
        var descView: TextView = binding.stellarUserIdTxt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding= CustomRowBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=contactList[position]
        holder.titleView.text=currentItem.id.toString()
        holder.descView.text=currentItem.StellarUserID
    }
    fun setData(contact:List<Contacts>){
        this.contactList=contact
        notifyDataSetChanged()
    }


}