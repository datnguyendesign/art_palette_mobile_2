package com.example.artpalette.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.artpalette.activity.PrimaryActivity
import com.example.artpalette.DatabaseHelper
import com.example.artpalette.R

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var listener: OnTitleClickListener? = null
    private lateinit var databaseHelper: DatabaseHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnTitleClickListener
            ?: throw RuntimeException("$context must implement OnSignupClickListener")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleTv = view.findViewById<TextView>(R.id.titleTv)
        titleTv.setOnClickListener {
            view.visibility = INVISIBLE
            listener?.onTitleClick()
        }

        val signupSwitchBtn = view.findViewById<Button>(R.id.signupSwitchBtn)
        signupSwitchBtn.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.fragment, SignupFragment())?.addToBackStack(null)?.commit()
        }

        databaseHelper = DatabaseHelper(requireContext())
        view.findViewById<Button>(R.id.loginFragmentBtn).setOnClickListener {
            val emailField = view.findViewById<EditText>(R.id.emailEdt).text.toString()
            val passwordField = view.findViewById<EditText>(R.id.passwordEdt).text.toString()
            loginDatabase(passwordField, emailField)
        }
    }

    private fun loginDatabase(password: String, email: String) {
        val userExists = databaseHelper.readUser(password, email)
        if (userExists) {
            Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), PrimaryActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }

    interface OnTitleClickListener {
        fun onTitleClick()
    }
}
