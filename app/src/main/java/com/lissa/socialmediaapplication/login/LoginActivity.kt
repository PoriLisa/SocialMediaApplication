package com.lissa.socialmediaapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lissa.socialmediaapplication.MainActivity
import com.lissa.socialmediaapplication.Preferences
import com.lissa.socialmediaapplication.R
import com.lissa.socialmediaapplication.application.SocialApplication
import com.lissa.socialmediaapplication.databinding.ActivityLoginBinding
import com.lissa.socialmediaapplication.register.RegisterActivity
import com.lissa.socialmediaapplication.register.RegisterModelFactory
import com.lissa.socialmediaapplication.register.RegisterViewModel
import com.lissa.socialmediaapplication.viewmodel.LoginModelFactory
import com.lissa.socialmediaapplication.viewmodel.LoginViewModelClass

class LoginActivity : AppCompatActivity() {
    lateinit var application: SocialApplication
    lateinit var viewModel: LoginViewModelClass
    lateinit var binding: ActivityLoginBinding
    var preferences: Preferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        application = SocialApplication(this)
        viewModel = ViewModelProviders.of(this, LoginModelFactory(application!!.repository))
            .get(LoginViewModelClass::class.java)
        binding.loginVm = viewModel
        if (preferences == null) {
            preferences = Preferences(this)
        }
        initObserver()
        btnOnClik()
    }

    private fun btnOnClik() {
        binding.tvSignupClick.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun initObserver() {
        viewModel.navigatetoRegister.observe(this, { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "insidi observe")
                /* displayUsersList()*/
                viewModel.doneNavigatingRegiter()
            }
        })

        viewModel.errotoast.observe(this, Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                viewModel.donetoast()
            }
        })

        viewModel.errotoastUsername.observe(this, Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(this, "User doesnt exist,please Register!", Toast.LENGTH_SHORT)
                    .show()
                viewModel.donetoastErrorUsername()
            }
        })

        viewModel.errorToastInvalidPassword.observe(this, Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(this, "Please check your Password", Toast.LENGTH_SHORT).show()
                viewModel.donetoastInvalidPassword()
            }
        })

        viewModel.navigatetoUserDetails.observe(this, Observer { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "insidi observe")
                navigateUserDetails()
                viewModel.doneNavigatingUserDetails()
            }
        })


    }

    private fun navigateUserDetails() {
        Log.e("TAG", "getTheData: "+binding.etEmail.text.toString())
        preferences!!.saveUserName(binding.etEmail.text.toString())
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}