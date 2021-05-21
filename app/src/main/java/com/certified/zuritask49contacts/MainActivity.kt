package com.certified.zuritask49contacts

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.certified.zuritask49contacts.databinding.ActivityMainBinding
import com.certified.zuritask49contacts.databinding.DialogNewContactBinding
import com.certified.zuritask49contacts.room.Contact
import com.certified.zuritask49contacts.room.ContactDatabase
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val bottomSheet = layoutInflater.inflate(R.layout.dialog_new_contact, null)
        val binding1 = DialogNewContactBinding.inflate(
            layoutInflater, bottomSheet as ViewGroup, false
        )

        val application = requireNotNull(this).application

        val dataSource = ContactDatabase.getInstance(application).contactsDao

        val viewModelFactory = MainActivityViewModelFactory(dataSource, application)

        val mainActivityViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(MainActivityViewModel::class.java)

        val categoryList = ArrayList<String>()
        val adapterCategory = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categoryList
        )

        categoryList.add("Select a category")
        categoryList.add("Colleagues")
        categoryList.add("Family")
        categoryList.add("Friends")
        categoryList.add("Tutors")

        adapterCategory.notifyDataSetChanged()
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding?.fab?.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            binding1.apply {
                spinnerCategory.adapter = adapterCategory
                btnAddContact.setOnClickListener {
                    val category = spinnerCategory.selectedItem.toString()
                    if (category != "Select a category") {
                        val name = etName.text.toString()
                        val number = etNumber.text.toString()

                        if (name.isNotEmpty() && number.isNotEmpty()) {
                            mainActivityViewModel.insertContact(Contact(name, number, category))
                            bottomSheetDialog.dismiss()
                        }
                    } else
                        Toast.makeText(this@MainActivity, "Select a category", Toast.LENGTH_LONG).show()
                }
            }

            when((binding1.root).parent) {
                null -> null
                else -> (binding1.root).parent as ViewGroup
            }?.removeView(binding1.root)

            bottomSheetDialog.setContentView(binding1.root)
            bottomSheetDialog.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}