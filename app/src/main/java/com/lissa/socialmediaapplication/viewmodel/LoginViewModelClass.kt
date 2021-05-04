package com.lissa.socialmediaapplication.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.lissa.socialmediaapplication.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModelClass(val repository: UserRepository) : ViewModel() {

    var email: String? = null
    var password: String? = null
    val navigatetoRegister: LiveData<Boolean>
        get() = _navigatetoRegister
    private val _navigatetoRegister = MutableLiveData<Boolean>()

    private val _navigatetoUserDetails = MutableLiveData<Boolean>()

    val navigatetoUserDetails: LiveData<Boolean>
        get() = _navigatetoUserDetails

    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errotoastUsername: LiveData<Boolean>
        get() = _errorToastUsername

    private val _errorToastInvalidPassword = MutableLiveData<Boolean>()

    val errorToastInvalidPassword: LiveData<Boolean>
        get() = _errorToastInvalidPassword

    fun loginButton() {
        if (email == null || password == null) {
            _errorToast.value = true
        } else {
            viewModelScope.launch {
                val usersNames = repository.getUserName(email!!)
                if (usersNames != null) {
                    if (usersNames.password == password) {
                        email = ""
                        password = ""
                        _navigatetoUserDetails.value = true
                    } else {

                        _errorToastInvalidPassword.value = true
                    }
                } else {
                    _errorToastUsername.value = true
                }
            }
        }
    }

    fun doneNavigatingRegiter() {
        _navigatetoRegister.value = false
    }

    fun doneNavigatingUserDetails() {
        _navigatetoUserDetails.value = false
    }


    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }


    fun donetoastErrorUsername() {
        _errorToastUsername.value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun donetoastInvalidPassword() {
        _errorToastInvalidPassword.value = false
        Log.i("MYTAG", "Done taoasting ")
    }

}

class LoginModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModelClass::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModelClass(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}