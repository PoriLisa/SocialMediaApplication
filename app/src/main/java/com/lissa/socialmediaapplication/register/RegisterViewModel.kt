package com.lissa.socialmediaapplication.register

import android.content.Context
import android.service.autofill.UserData
import android.util.Log
import androidx.lifecycle.*
import com.lissa.socialmediaapplication.Preferences
import com.lissa.socialmediaapplication.dataBase.UserDataBase
import com.lissa.socialmediaapplication.repository.UserRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    var email: String = ""
    var name: String = ""
    var password: String = ""
    var confirmpassword: String = ""
    var isLogggedIn: Boolean = false
    var preferences: Preferences? = null
    val navigateto: LiveData<Boolean>
        get() = _navigatetologin
    private val _navigatetologin = MutableLiveData<Boolean>()
    private val _errorToast = MutableLiveData<Boolean>()
    private val _errorToastUsername = MutableLiveData<Boolean>()
     val regiser_complete = MutableLiveData<Boolean>()
    val errotoast: LiveData<Boolean>
        get() = _errorToast
    val errotoastUsername: LiveData<Boolean>
        get() = _errorToastUsername


    fun sumbitButton() {
        Log.i("MYTAG", "Inside SUBMIT BUTTON")
        if (name == null || email == null || password == null || confirmpassword == null) {
            _errorToast.value = true
        } else {
            viewModelScope.launch {

                val usersNames = repository.getUserName(email)

                Log.i("MYTAG", usersNames.toString() + "------------------")
                if (usersNames != null) {
                    _errorToastUsername.value = true
                    Log.i("MYTAG", "Inside if Not null")
                } else {

                    insert(UserDataBase(0, name, email, password))

                    regiser_complete.value =true
                    name = ""
                    email = ""
                    password = ""
                    confirmpassword = ""
                    _navigatetologin.value = true
                }
            }
        }
    }

    fun doneNavigating() {
        _navigatetologin.value = false
        Log.i("MYTAG", "Done navigating ")
    }

    fun donetoastUserName() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting  username")
    }

    fun insert(user: UserDataBase) = viewModelScope.launch {
        repository.insert(user)

    }
}

class RegisterModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
