package com.lissa.socialmediaapplication.socialMediadataClass

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.lissa.socialmediaapplication.repository.PostRepository

class GetPostViewModel(repository: PostRepository) : ViewModel() {

    val getAllPostList: LiveData<List<PostEntityDataClass>> = repository.getAllPost.asLiveData()
}

class GetPostModelFactory(private val repository: PostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GetPostViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GetPostViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
