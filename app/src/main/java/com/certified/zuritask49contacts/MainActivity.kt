package com.certified.zuritask49contacts

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.certified.zuritask49contacts.databinding.ActivityMainBinding
import com.certified.zuritask49contacts.databinding.DialogNewContactBinding
import com.certified.zuritask49contacts.room.Contact
import com.certified.zuritask49contacts.room.ContactDatabase
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.main_nav_host_fragment)

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

        binding?.fab?.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            binding1.apply {
                btnAddContact.setOnClickListener {
                        val name = etName.text.toString()
                        val number = etNumber.text.toString()

                        if (name.isNotEmpty() && number.isNotEmpty()) {
                            mainActivityViewModel.insertContact(Contact(name, number))
                            bottomSheetDialog.dismiss()
                        }
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