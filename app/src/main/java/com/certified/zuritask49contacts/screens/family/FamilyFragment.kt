package com.certified.zuritask49contacts.screens.family

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
import com.certified.zuritask49contacts.databinding.FragmentFamilyBinding
import com.certified.zuritask49contacts.room.ContactDatabase

class FamilyFragment : Fragment() {

    private var binding: FragmentFamilyBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_family, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = ContactDatabase.getInstance(application).contactsDao

        val viewModelFactory = FamilyViewModelFactory(dataSource, application)

        val familyViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(FamilyViewModel::class.java)

        val adapter = ContactAdapter()

        binding!!.viewModel = familyViewModel
        binding!!.lifecycleOwner = this
        binding!!.recyclerViewFamily.layoutManager = LinearLayoutManager(activity)
        binding!!.recyclerViewFamily.adapter = adapter

        familyViewModel.contacts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}