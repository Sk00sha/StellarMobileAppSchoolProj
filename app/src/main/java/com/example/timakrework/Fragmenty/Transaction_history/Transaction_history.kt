package com.example.timakrework.Fragmenty.Transaction_history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timakrework.R
import com.example.timakrework.data.ViewModel
import com.example.timakrework.databinding.FragmentMakeTransactionBinding
import com.example.timakrework.databinding.FragmentTransactionHistoryBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class Transaction_history : Fragment() {

    private lateinit var mUserVieModel:ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding= DataBindingUtil.inflate<FragmentTransactionHistoryBinding>(inflater,R.layout.fragment_transaction_history, container, false)
        val view = binding.getRoot()
        val adapter=NewAdapter()
        val recyclerView=binding.recyclerviewTransactions
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        mUserVieModel= ViewModelProvider(this).get(ViewModel::class.java)
        mUserVieModel.readAllTransactions.observe(viewLifecycleOwner, Observer {
                contact->adapter.setData(contact)
        })

        binding.homeButton.setOnClickListener{
            findNavController().navigate(R.id.action_transaction_history_to_register_Fragment)
        }


    return view
     }

}