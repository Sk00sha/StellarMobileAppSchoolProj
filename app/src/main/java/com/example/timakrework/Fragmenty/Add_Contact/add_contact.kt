package com.example.timakrework.Fragmenty.Add_Contact

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
import com.example.timakrework.R
import com.example.timakrework.data.Contacts
import com.example.timakrework.data.ViewModel
import com.example.timakrework.databinding.FragmentAddContactBinding
import com.example.timakrework.databinding.FragmentRegisterBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class add_contact : Fragment() {
    //private lateinit var binding: FragmentAddContactBinding
    private lateinit var mUserViewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mUserViewModel= ViewModelProvider(this).get(ViewModel::class.java)
        val binding=DataBindingUtil.inflate<FragmentAddContactBinding>(inflater, R.layout.fragment_add_contact, container, false);
        val view= binding.getRoot()
        binding.addContactToDb.setOnClickListener {
            val user_stellar_id=binding.CONTACTID.text.toString()
            println("Added User"+user_stellar_id)
            mUserViewModel.addContact(Contacts(0,user_stellar_id))
            Toast.makeText(requireContext(),"Added new contact",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_add_contact_to_register_Fragment)
        }
        binding.homeButton.setOnClickListener{
            findNavController().navigate(R.id.action_add_contact_to_register_Fragment)
        }
        return view
    }

}