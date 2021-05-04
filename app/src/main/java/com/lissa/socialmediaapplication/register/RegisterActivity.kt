package com.lissa.socialmediaapplication.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lissa.socialmediaapplication.Preferences
import com.lissa.socialmediaapplication.R
import com.lissa.socialmediaapplication.application.SocialApplication
import com.lissa.socialmediaapplication.databinding.ActivityRegisterBinding
import com.lissa.socialmediaapplication.login.LoginActivity




class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var application: SocialApplication
    lateinit var viewModel: RegisterViewModel
    var preferences: Preferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        application = SocialApplication(this)
        viewModel = ViewModelProviders.of(this, RegisterModelFactory(application!!.repository))
            .get(RegisterViewModel::class.java)
        binding.lifecycleOwner = this
        binding.registerVm = viewModel
        viewModel
        if (preferences == null){
            preferences = Preferences(this)
        }
        getTheData()
        initObserver()
        btnOnClickListner()
    }

    private fun getTheData() {
        Log.e("TAG", "getTheData: "+binding.etEmail.text.toString())
        preferences!!.saveUserName(binding.etEmail.text.toString())
    }

    private fun btnOnClickListner() {
        binding.tvMoveToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initObserver() {
        viewModel.errotoast.observe(this, Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                viewModel.doneNavigating()
            }
        })

        viewModel.errotoastUsername.observe(this, Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(this, "UserName Already taken", Toast.LENGTH_SHORT).show()
                viewModel.donetoastUserName()
            }
        })


        viewModel.regiser_complete.observe(this, Observer { hasError ->
            if (hasError == true) {
                preferences!!.setLogin(true)
            }
        })

    }




}