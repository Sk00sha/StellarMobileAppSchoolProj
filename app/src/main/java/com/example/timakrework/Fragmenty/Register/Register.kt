package com.example.timakrework.Fragmenty.Register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.timakrework.Encryptor
import com.example.timakrework.R
import com.example.timakrework.RegisterStellar
import com.example.timakrework.data.User
import com.example.timakrework.data.ViewModel

import com.example.timakrework.databinding.FragmentRegisterBinding


class Register : Fragment() {
    private lateinit var mUserViewModel:ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding=DataBindingUtil.inflate<FragmentRegisterBinding>(inflater,R.layout.fragment_register, container, false)
        val view=binding.getRoot()
        val StellarClass= RegisterStellar()
        val encryptor=Encryptor()
        mUserViewModel= ViewModelProvider(this).get(ViewModel::class.java)

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_register_Fragment)
            val data=StellarClass.register_stellar()

            val pin = binding.pin.text.toString()
            val hash = encryptor.toMD5(pin) //zahashujeme nas pin
            val encrypt = encryptor.encrypt(data.get(1),hash) //encryp nas pin

            mUserViewModel.addUser(User(0,data.get(0),encrypt.toString(),data.get(2)))
            Toast.makeText(requireContext(),"Account created", Toast.LENGTH_LONG).show()

            }
        binding.floatingListAccounts.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_accounts_Fragment)
        }
        binding.homeButton.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_register_Fragment)
        }

        return view
    }

}