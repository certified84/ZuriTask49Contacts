package com.certified.zuritask49contacts.screens.colleagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.certified.zuritask49contacts.Contact
import com.certified.zuritask49contacts.ContactAdapter
import com.certified.zuritask49contacts.R
import com.certified.zuritask49contacts.databinding.DialogNewContactBinding
import com.certified.zuritask49contacts.databinding.FragmentColleaguesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class ColleaguesFragment : Fragment() {

    private val contactList = mutableListOf<Contact>()
//    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentColleaguesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_colleagues, container, false
        )

        val viewModelFactory = ColleaguesViewModelFactory(contactList)

        val collegeViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ColleaguesViewModel::class.java)

        contactList.add(0, Contact("Sammie", "08136108482"))
        contactList.add(1, Contact("Janie", "08037383050"))
        contactList.add(2, Contact("Troy", "08037730072"))
        contactList.add(3, Contact("Jay", "09024756184"))
        contactList.add(4, Contact("Nega", "07056130309"))
        contactList.add(5, Contact("Dannie", "08037721252"))

        binding.viewModel = collegeViewModel
        binding.lifecycleOwner = this
        binding.recyclerViewColleagues.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewColleagues.adapter = ContactAdapter()

        val binding1: DialogNewContactBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.dialog_new_contact,
            ConstraintLayout(requireContext()), false
        )
        binding.fab.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            binding1.apply {
                btnAddContact.setOnClickListener {
                    val name = etName.text.toString()
                    val number = etNumber.text.toString()

                    if (name.isNotEmpty() && number.isNotEmpty()) {
                        contactList.add(Contact(name, number))
                        bottomSheetDialog.dismiss()
                    }
                }
            }
            bottomSheetDialog.setContentView(binding1.root)
            bottomSheetDialog.show()
        }

        return binding.root
    }

    private fun launchDialog() {
//        val view = layoutInflater.inflate(
//            R.layout.dialog_new_contact,
//            ConstraintLayout(requireContext())
//        )
    }
}