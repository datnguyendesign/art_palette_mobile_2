package com.example.artpalette.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import androidx.fragment.app.FragmentContainerView
import com.example.artpalette.fragment.LoginFragment
import com.example.artpalette.R
import com.example.artpalette.fragment.SignupFragment
import com.example.artpalette.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LoginFragment.OnTitleClickListener,
    SignupFragment.OnBackwardBtnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, LoginFragment())
            commit()
        }

        binding.loginBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, LoginFragment())
                findViewById<FragmentContainerView>(R.id.fragment).visibility = VISIBLE
                commit()
            }
            binding.loginBtn.visibility = INVISIBLE
            binding.signupBtn.visibility = INVISIBLE
        }

        binding.signupBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, SignupFragment())
                findViewById<FragmentContainerView>(R.id.fragment).visibility = VISIBLE
                commit()
            }
            binding.loginBtn.visibility = INVISIBLE
            binding.signupBtn.visibility = INVISIBLE
        }

    }

    override fun onTitleClick() {
        findViewById<Button>(R.id.loginBtn).visibility = VISIBLE
        findViewById<Button>(R.id.signupBtn).visibility = VISIBLE
    }

    override fun onBackwardClick() {
        findViewById<Button>(R.id.loginBtn).visibility = VISIBLE
        findViewById<Button>(R.id.signupBtn).visibility = VISIBLE
    }

}