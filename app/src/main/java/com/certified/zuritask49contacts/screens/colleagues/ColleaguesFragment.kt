package com.certified.zuritask49contacts.screens.colleagues

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
import com.certified.zuritask49contacts.databinding.FragmentColleaguesBinding
import com.certified.zuritask49contacts.room.ContactDatabase

class ColleaguesFragment : Fragment() {

    private var binding: FragmentColleaguesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_colleagues, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = ContactDatabase.getInstance(application).contactsDao

        val viewModelFactory = ColleaguesViewModelFactory(dataSource, application)

        val colleagueViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ColleaguesViewModel::class.java)

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