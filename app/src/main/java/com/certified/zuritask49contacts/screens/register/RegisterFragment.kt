package com.certified.zuritask49contacts.screens.register

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.certified.zuritask49contacts.Category
import com.certified.zuritask49contacts.R
import com.certified.zuritask49contacts.databinding.FragmentRegisterBinding
import com.certified.zuritask49contacts.room.ContactDatabase
import com.certified.zuritask49contacts.room.Credential
import com.certified.zuritask49contacts.screens.category.CategoryAdapter

class RegisterFragment : Fragment(), View.OnClickListener {

    private var binding: FragmentRegisterBinding? = null
    private lateinit var navController: NavController
    private lateinit var registersViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_register, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = ContactDatabase.getInstance(application).contactsDao

        val viewModelFactory = RegisterViewModelFactory(dataSource, application)

        registersViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(RegisterViewModel::class.java)

        binding?.apply{
            btnSignUp.setOnClickListener(this@RegisterFragment)
            tvForgotPassword.setOnClickListener(this@RegisterFragment)
            tvLogin.setOnClickListener(this@RegisterFragment)
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onClick(v: View?) {
        binding?.apply{
            when (v) {
                tvLogin -> navController.navigate(R.id.loginFragment)
                tvForgotPassword  -> Toast.makeText(context, "Check back in a bit", Toast.LENGTH_LONG).show()
                btnSignUp -> {
                    val email = etEmail.text.toString().trim()
                    val password = etPassword.text.toString().trim()
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        etPasswordLayout.error = null
                        etEmailLayout.error = null
                        progressBar.visibility = View.VISIBLE

                        val credential = Credential(email, password)
                        credential.id = 0
                        registersViewModel.register(credential)

                        val handler = Handler(Looper.myLooper()!!)
                        handler.postDelayed({
                            progressBar.visibility = View.GONE
                            Toast.makeText(context, "Registration successful", Toast.LENGTH_LONG).show()
                            navController.navigate(R.id.loginFragment)
                        }, 3000L)
                    } else {
                        etPasswordLayout.error = getString(R.string.required)
                        etEmailLayout.error = getString(R.string.required)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}