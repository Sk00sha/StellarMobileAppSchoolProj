package com.example.timakrework.Fragmenty.MakeTransaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs

import com.example.timakrework.data.ViewModel


import android.content.Context
import android.widget.*
import androidx.databinding.DataBindingUtil

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.timakrework.Encryptor

import com.example.timakrework.Fragmenty.Contacts.ListAdapter
import com.example.timakrework.R
import com.example.timakrework.RegisterStellar
import com.example.timakrework.data.Contacts
import com.example.timakrework.data.Transactions
import com.example.timakrework.databinding.FragmentMakeTransactionBinding


class MakeTransaction : Fragment() {
    private lateinit var mUserViewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding=DataBindingUtil.inflate<FragmentMakeTransactionBinding>(inflater,R.layout.fragment_make_transaction, container, false)
        val view = binding.getRoot()
        mUserViewModel= ViewModelProvider(this).get(ViewModel::class.java)
        mUserViewModel.readAllContactsStrings.observe(viewLifecycleOwner, Observer {
                item->val adapter=ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,item)
            binding.spinnerTo.adapter=adapter
        })
        mUserViewModel.readAllAccounts.observe(viewLifecycleOwner, Observer {
            item->val adapter=ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,item)
            binding.spinnerFrom.adapter=adapter
        })
        binding.runTransaction.setOnClickListener {
            val stellar_controller=RegisterStellar()
            val pin=binding.pinInput.text.toString()
            val amount=binding.getAmount.text.toString()
            val stellar_balance=stellar_controller.check_balance_stellar(binding.spinnerFrom.selectedItem.toString())
            if(!pin.isEmpty() && binding.pinInput!=null  && binding.getAmount!=null && !amount.isEmpty() && stellar_balance.toFloat()>=amount.toFloat()){
                //mUserViewModel.makeTransaction(pin,binding.spinnerFrom.selectedItem.toString(),binding.spinnerTo.selectedItem.toString(),amount)
                mUserViewModel.readAllUsers.observe(viewLifecycleOwner, Observer {
                        user->run{for (item in user) {
                    if(item.StellarUserID==binding.spinnerFrom.selectedItem.toString()){
                        var encryptor= Encryptor()
                        val hash = encryptor.toMD5(pin) //zahashujeme nas pin
                        val secret_seed=encryptor.decryptWithAES(hash,item.HashSecretSeed)
                        if(secret_seed==null){
                            Toast.makeText(requireContext(),"Transaction cant be made",Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(requireContext(),"Transaction made",Toast.LENGTH_LONG).show()
                            mUserViewModel.makeTransaction(pin,binding.spinnerFrom.selectedItem.toString(),binding.spinnerTo.selectedItem.toString(),amount)
                            findNavController().navigate(R.id.action_makeTransaction_to_transaction_history)
                        }
                    }
                }
                }
                })
                //Toast.makeText(requireContext(),"Redirecting to transactions",Toast.LENGTH_LONG).show()
                //findNavController().navigate(R.id.action_makeTransaction_to_transaction_history)
            }
            if(amount.isEmpty() || pin.isEmpty()){
                Toast.makeText(requireContext(),"Enter desired amount and pin please",Toast.LENGTH_LONG).show()
            }
            else if(stellar_balance.toFloat()<amount.toFloat()){
                Toast.makeText(requireContext(),"You dont have enough funds to send",Toast.LENGTH_LONG).show()
            }


        }
        binding.homeButton.setOnClickListener{
            findNavController().navigate(R.id.action_makeTransaction_to_register_Fragment)
        }

        return view
    }




}