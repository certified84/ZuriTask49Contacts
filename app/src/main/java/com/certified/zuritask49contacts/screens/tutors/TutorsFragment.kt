package com.certified.zuritask49contacts.screens.tutors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.certified.zuritask49contacts.ContactAdapter
import com.certified.zuritask49contacts.R
import com.certified.zuritask49contacts.databinding.FragmentColleaguesBinding
import com.certified.zuritask49contacts.databinding.FragmentTutorsBinding
import com.certified.zuritask49contacts.room.ContactDatabase
import com.certified.zuritask49contacts.screens.colleagues.ColleaguesViewModel
import com.certified.zuritask49contacts.screens.colleagues.ColleaguesViewModelFactory

class TutorsFragment : Fragment() {

    private var binding: FragmentTutorsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_tutors, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = ContactDatabase.getInstance(application).contactsDao

        val viewModelFactory = TutorsViewModelFactory(dataSource, application)

        val tutorsViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(TutorsViewModel::class.java)

        val adapter = ContactAdapter()

        binding!!.viewModel = tutorsViewModel
        binding!!.lifecycleOwner = this
        binding!!.recyclerViewTutors.layoutManager = LinearLayoutManager(activity)
        binding!!.recyclerViewTutors.adapter = adapter

        tutorsViewModel.contacts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}