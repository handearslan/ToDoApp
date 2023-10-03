package com.handearslan.todoapphomework.ui.dailynotes.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.handearslan.todoapphomework.R
import com.handearslan.todoapphomework.common.viewBinding
import com.handearslan.todoapphomework.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    private lateinit var sharedPref: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

        val isLogin = sharedPref.getBoolean("isLogin", false)

        if (isLogin) {
            findNavController().navigate(R.id.loginToDailyNotes)
        }

        with(binding) {
            btnLogIn.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                if (checkFields(email, password)) {
                    sharedPref.edit().putBoolean("isLogin", true).apply()
                    findNavController().navigate(R.id.loginToDailyNotes)
                }
            }
        }
    }

    private fun checkFields(email: String, password: String): Boolean {
        return when {
            Patterns.EMAIL_ADDRESS.matcher(email).matches().not() -> {
                binding.tilEmail.error = "E-mail is not valid"
                false
            }

            password.isEmpty() -> {
                binding.tilEmail.isErrorEnabled = false
                binding.tilPassword.error = "Password is empty!"
                false
            }

            password.length < 6 -> {
                binding.tilEmail.isErrorEnabled = false
                binding.tilPassword.error = "Password should be at least 6 character"
                false
            }

            else -> true
        }
    }
}