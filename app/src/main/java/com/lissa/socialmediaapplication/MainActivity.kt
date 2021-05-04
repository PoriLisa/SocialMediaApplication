package com.lissa.socialmediaapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.lissa.socialmediaapplication.adapter.PostListAdapter
import com.lissa.socialmediaapplication.application.SocialApplication
import com.lissa.socialmediaapplication.databinding.ActivityMainBinding
import com.lissa.socialmediaapplication.login.LoginActivity
import com.lissa.socialmediaapplication.repository.UserRepository
import com.lissa.socialmediaapplication.socialMediadataClass.CreatePostViewmodelClass

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: CreatePostViewmodelClass
    lateinit var application: SocialApplication
    lateinit var adapter: PostListAdapter
    var preferences: Preferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        application = SocialApplication(this)
        viewModel = ViewModelProviders.of(
            this,
            CreatePostViewmodelClass.CreatePostViewModelFactory(application!!.postrepository)
        )
            .get(CreatePostViewmodelClass::class.java)
        binding.postVm = viewModel
        if (preferences == null) {
            preferences = Preferences(this)
        }
        val dao = application.userDao
        val repository = UserRepository(dao)
        viewModel.repository_user = repository
        viewModel.userName = preferences!!.getUserName()

        binding.tvGoNexr.setOnClickListener {
            val intent = Intent(this, GetPostActivity::class.java)
            startActivity(intent)
            finish()
        }



    }


}