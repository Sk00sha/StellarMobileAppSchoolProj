package com.example.timakrework.Fragmenty.Contacts

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
import com.example.timakrework.databinding.FragmentContactsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Contacts : Fragment() {
    private lateinit var mUserViewModel:ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= DataBindingUtil.inflate<FragmentContactsBinding>(inflater,R.layout.fragment_contacts, container, false)
        val view=binding.root
        var adapter=ListAdapter()
        val recyclerView=binding.recyclerview
        binding.homeButton.setOnClickListener{
            findNavController().navigate(R.id.action_contacts_to_register_Fragment)
        }
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        mUserViewModel=ViewModelProvider(this).get(ViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            contact->adapter.setData(contact)
        })

        binding.buttonAddContact.setOnClickListener{
            findNavController().navigate(R.id.action_contacts_to_add_contact)
        }

        return view
    }

}