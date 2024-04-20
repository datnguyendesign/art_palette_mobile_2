package com.example.artpalette.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.artpalette.DatabaseHelper
import com.example.artpalette.R

class SignupFragment : Fragment(R.layout.fragment_signup) {
    private var listener: OnBackwardBtnClickListener? = null
    private lateinit var databaseHelper: DatabaseHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnBackwardBtnClickListener
            ?: throw RuntimeException("$context must implement OnSignupClickListener")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginSwitchBtn = view.findViewById<Button>(R.id.loginSwitchBtn)
        loginSwitchBtn.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.fragment, LoginFragment())?.addToBackStack(null)?.commit()
        }

        val backwardBtn = view.findViewById<Button>(R.id.backwardBtn)
        backwardBtn.setOnClickListener {
            view.visibility = INVISIBLE
            listener?.onBackwardClick()
        }

        databaseHelper = DatabaseHelper(requireContext())
        view.findViewById<Button>(R.id.signupFragmentBtn).setOnClickListener {
            val emailField = view.findViewById<EditText>(R.id.emailSignUpEt).text.toString()
            val firstNameField = view.findViewById<EditText>(R.id.firstNameSignUpEt).text.toString()
            val lastNameField = view.findViewById<EditText>(R.id.lastNameSignUpEt).text.toString()
            val userName = "$firstNameField $lastNameField"
            val dateField = view.findViewById<EditText>(R.id.dateSignUpEt).text.toString()
            val passwordField =view.findViewById<EditText>(R.id.passwordSignUpEt).text.toString()
            signupDatabase(userName, passwordField, emailField, dateField)
        }
    }

    private fun signupDatabase(username: String, password: String, email: String, date: String) {
        val insertedRowId = databaseHelper.insertUser(username, password, email, date)
        if (insertedRowId != -1L) {
            Toast.makeText(requireContext(), "Signup Successful", Toast.LENGTH_SHORT).show()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment, LoginFragment())
                addToBackStack(null)
                commit()
            }
        } else {
            Toast.makeText(requireContext(), "Signup Failed", Toast.LENGTH_SHORT).show()
        }
    }

    interface OnBackwardBtnClickListener {
        fun onBackwardClick()
    }
}