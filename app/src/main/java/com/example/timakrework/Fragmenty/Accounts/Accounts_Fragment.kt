package com.example.timakrework.Fragmenty.Accounts

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
import com.example.timakrework.databinding.FragmentAccountsBinding
import com.example.timakrework.databinding.FragmentAddContactBinding

class Accounts_Fragment : Fragment() {
  lateinit var mUserViewModel:ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= DataBindingUtil.inflate<FragmentAccountsBinding>(inflater, R.layout.fragment_accounts_, container, false);
        val view= binding.getRoot()
        var adapter= AccountAdapter()
        val recyclerView=binding.recyclerviewAccounts
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        mUserViewModel= ViewModelProvider(this).get(ViewModel::class.java)
        mUserViewModel.readAllUsers.observe(viewLifecycleOwner, Observer {
                contact->adapter.setData(contact)
        })
        binding.homeButton.setOnClickListener{
            findNavController().navigate(R.id.action_accounts_Fragment_to_register_Fragment)
        }
        return view
    }


}