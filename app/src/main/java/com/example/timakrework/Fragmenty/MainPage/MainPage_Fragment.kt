package com.example.timakrework.Fragmenty.MainPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.timakrework.R
import com.example.timakrework.data.ViewModel
import com.example.timakrework.databinding.FragmentLoggedInBinding
import com.example.timakrework.databinding.FragmentMainPageBinding
import com.example.timakrework.databinding.FragmentRegisterBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainPage_Fragment : Fragment() {
    private lateinit var binding: FragmentMainPageBinding
    private lateinit var mUserViewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //var textfield:TextView=view.findViewById(R.id.loggedIn_info)

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_main_page, container, false)
        val view=binding.getRoot()
        mUserViewModel= ViewModelProvider(this).get(ViewModel::class.java)
        binding.showAccounts.setOnClickListener{
            findNavController().navigate(R.id.action_register_Fragment_to_accounts_Fragment)
        }
        binding.buttonCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_register_Fragment_to_loginFragment)
        }
        binding.buttonMakeTransaction.setOnClickListener {
            findNavController().navigate(R.id.action_register_Fragment_to_makeTransaction)
        }
        binding.buttonAddAccount.setOnClickListener {
            findNavController().navigate(R.id.action_register_Fragment_to_loggedIn)
        }
        binding.buttonViewContacts.setOnClickListener {
            findNavController().navigate(R.id.action_register_Fragment_to_contacts)
        }
        binding.buttonViewTransactions.setOnClickListener{
            findNavController().navigate(R.id.action_register_Fragment_to_transaction_history)
        }

        return view
    }


}