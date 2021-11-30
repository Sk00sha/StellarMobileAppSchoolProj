package com.example.timakrework.Fragmenty.Balance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timakrework.R
import com.example.timakrework.RegisterStellar
import com.example.timakrework.databinding.FragmentBalanceBinding
import com.example.timakrework.databinding.FragmentMainPageBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class BalanceFragment : Fragment() {
    private lateinit var mUserViewModel:com.example.timakrework.data.ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val balance_binding=DataBindingUtil.inflate<FragmentBalanceBinding>(inflater,R.layout.fragment_balance, container, false)
        val view=balance_binding.getRoot()
        mUserViewModel= ViewModelProvider(this).get(com.example.timakrework.data.ViewModel::class.java)
        mUserViewModel.readAllAccounts.observe(viewLifecycleOwner, Observer {
                item->val adapter=ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,item)
            balance_binding.idSpinner.adapter=adapter
        })
        balance_binding.getBalance.setOnClickListener {
            mUserViewModel.updateUser(balance_binding.idSpinner.selectedItem.toString())
        }
        balance_binding.realDbBalance.setOnClickListener {
            mUserViewModel.getBalance(balance_binding.idSpinner.selectedItem.toString())
        }

        return view
    }


}