package com.lissa.socialmediaapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.lissa.socialmediaapplication.adapter.PostListAdapter
import com.lissa.socialmediaapplication.application.SocialApplication
import com.lissa.socialmediaapplication.databinding.ActivityGetPostBinding
import com.lissa.socialmediaapplication.register.RegisterModelFactory
import com.lissa.socialmediaapplication.register.RegisterViewModel
import com.lissa.socialmediaapplication.socialMediadataClass.GetPostModelFactory
import com.lissa.socialmediaapplication.socialMediadataClass.GetPostViewModel

class GetPostActivity : AppCompatActivity() {
    lateinit var binding: ActivityGetPostBinding
    lateinit var viewModel: GetPostViewModel
    lateinit var application: SocialApplication
    lateinit var adapter: PostListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_get_post)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_get_post)
        application = SocialApplication(this)
        viewModel = ViewModelProviders.of(this, GetPostModelFactory(application!!.postrepository))
            .get(GetPostViewModel::class.java)
        binding.getpostVm = viewModel
        binding.lifecycleOwner = this
        initAdapter()
        initObserver()
    }

    private fun initAdapter() {
        adapter = PostListAdapter()
        binding.rvListPost.adapter = adapter
    }

    private fun initObserver() {
        viewModel!!.getAllPostList.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            Log.e("TAG", "initObserver: " + words)
            words.let { adapter!!.submitList(it) }
        }

    }
}