package com.certified.zuritask49contacts.screens.login

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
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.certified.zuritask49contacts.R
import com.certified.zuritask49contacts.databinding.FragmentLoginBinding
import com.certified.zuritask49contacts.room.ContactDatabase

class LoginFragment : Fragment(), View.OnClickListener {

    private var binding: FragmentLoginBinding? = null
    private lateinit var navController: NavController
    private lateinit var loginViewModel: LoginViewModel
    private var savedEmail: String? = null
    private var savedPassword: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = ContactDatabase.getInstance(application).contactsDao

        val viewModelFactory = LoginViewModelFactory(dataSource, application)

        loginViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(LoginViewModel::class.java)

        loginViewModel.credentials.observe(viewLifecycleOwner) {
            savedEmail = it.email
            savedPassword = it.password
        }

        binding?.apply {
            btnLogin.setOnClickListener(this@LoginFragment)
            tvForgotPassword.setOnClickListener(this@LoginFragment)
            tvSignup.setOnClickListener(this@LoginFragment)
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onClick(v: View?) {
        binding?.apply {
            when (v) {
                tvSignup -> navController.navigate(R.id.registerFragment)
                tvForgotPassword -> Toast.makeText(
                    context,
                    "Check back in a bit",
                    Toast.LENGTH_LONG
                ).show()
                btnLogin -> {
                    val email = etEmail.text.toString().trim()
                    val password = etPassword.text.toString().trim()

                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        if (email == savedEmail && password == savedPassword) {
                            etPasswordLayout.error = null
                            etEmailLayout.error = null
                            progressBar.visibility = View.VISIBLE

                            val handler = Handler(Looper.myLooper()!!)
                            handler.postDelayed({
                                progressBar.visibility = View.GONE

                                val navOptions =
                                    NavOptions.Builder().setPopUpTo(R.id.loginFragment, true)
                                        .build()
                                navController.navigate(R.id.contactsFragment, null, navOptions)

//                                startActivity(Intent(requireContext(), MainActivity::class.java))
//                                requireActivity().finish()
                            }, 3000L)
                        } else {
                            etPasswordLayout.error = getString(R.string.error)
                            etEmailLayout.error = getString(R.string.error)
                        }
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