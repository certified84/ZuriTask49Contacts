package com.certified.zuritask49contacts.screens.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.certified.zuritask49contacts.ContactAdapter
import com.certified.zuritask49contacts.R
import com.certified.zuritask49contacts.databinding.FragmentContactsBinding
import com.certified.zuritask49contacts.room.ContactDatabase

class ContactsFragment : Fragment() {

    private var binding: FragmentContactsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_contacts, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = ContactDatabase.getInstance(application).contactsDao

        val viewModelFactory = ContactsViewModelFactory(dataSource, application)

        val colleagueViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ContactsViewModel::class.java)

        val adapter = ContactAdapter()

        binding!!.viewModel = colleagueViewModel
        binding!!.lifecycleOwner = this
        binding!!.recyclerViewColleagues.layoutManager = LinearLayoutManager(activity)
        binding!!.recyclerViewColleagues.adapter = adapter

        colleagueViewModel.contacts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}