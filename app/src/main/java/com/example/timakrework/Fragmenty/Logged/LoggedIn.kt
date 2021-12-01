package com.example.timakrework.Fragmenty.Logged

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.timakrework.Encryptor

import com.example.timakrework.R
import com.example.timakrework.RegisterStellar
import com.example.timakrework.data.User
import com.example.timakrework.data.ViewModel
import com.example.timakrework.databinding.FragmentLoggedInBinding
import com.example.timakrework.databinding.FragmentRegisterBinding


class LoggedIn : Fragment() {
    private lateinit var binding: FragmentLoggedInBinding

    private lateinit var mUserViewModel:ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_logged_in, container, false)
        val view=binding.getRoot()
        val StellarClass= RegisterStellar()
        val encryptor= Encryptor()
        mUserViewModel= ViewModelProvider(this).get(ViewModel::class.java)

        binding.addAccButton.setOnClickListener {
            val secret_seed = binding.secretSeedInfo.text.toString()
            val pin = binding.PinInfo.text.toString()
            if (!binding.PinInfo.text.toString().isEmpty() && !binding.secretSeedInfo.text.toString().isEmpty()) {
                val data = StellarClass.add_existing_account(secret_seed)
                val hash = encryptor.toMD5(pin) //zahashujeme nas pin
                println("hash " + hash)
                val encrypt = encryptor.encrypt(data.get(1), hash) //encryptime nas seed
                val decrypt = encryptor.decryptWithAES(hash, encrypt) //decryptime nas pin
                println("encrypt " + encrypt)
                println("ENCRYPT" + encrypt.toString())
                println("decrypt " + decrypt)
                mUserViewModel.addUser(User(0, data.get(0), encrypt.toString(), data.get(2)))
                findNavController().navigate(R.id.action_loggedIn_to_register_Fragment)
            }
            else{
                Toast.makeText(requireContext(), "Fill out all the fields please", Toast.LENGTH_LONG).show()
            }
        }

       // textfield.setText(data)
        binding.homeButton.setOnClickListener{
            findNavController().navigate(R.id.action_loggedIn_to_register_Fragment)
        }

        return view
    }


}