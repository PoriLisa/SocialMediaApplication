package com.lissa.socialmediaapplication.socialMediadataClass

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lissa.socialmediaapplication.dataBase.UserDataBase
import com.lissa.socialmediaapplication.repository.PostRepository
import com.lissa.socialmediaapplication.repository.UserRepository
import com.lissa.socialmediaapplication.viewmodel.LoginViewModelClass
import kotlinx.coroutines.launch

class CreatePostViewmodelClass(val repository: PostRepository) : ViewModel() {
    var post: String = ""

    lateinit var repository_user: UserRepository
    var userName: String = ""

    fun btnPost() {
        viewModelScope.launch {
            val userNames = repository_user.getUserName("")
            userName = userNames!!.name!!
        }

        if (!TextUtils.isEmpty(post)) {
            Log.e("TAG", "btnPost: "+userName+"::"+post)
            insert(PostEntityDataClass(0, userName, post.toString()))
        }


    }

    fun insert(post: PostEntityDataClass) = viewModelScope.launch {
        repository.insert(post)

    }

    class CreatePostViewModelFactory(private val repository: PostRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CreatePostViewmodelClass::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CreatePostViewmodelClass(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}