package com.certified.zuritask49contacts.screens.friends

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
import com.certified.zuritask49contacts.databinding.FragmentFriendsBinding
import com.certified.zuritask49contacts.room.ContactDatabase

class FriendsFragment : Fragment() {

    private var binding: FragmentFriendsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_friends, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = ContactDatabase.getInstance(application).contactsDao

        val viewModelFactory = FriendsViewModelFactory(dataSource, application)

        val friendsViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(FriendsViewModel::class.java)

        val adapter = ContactAdapter()

        binding!!.viewModel = friendsViewModel
        binding!!.lifecycleOwner = this
        binding!!.recyclerViewFriends.layoutManager = LinearLayoutManager(activity)
        binding!!.recyclerViewFriends.adapter = adapter

        friendsViewModel.contacts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}