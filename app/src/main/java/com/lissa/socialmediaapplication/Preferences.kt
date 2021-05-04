package com.lissa.socialmediaapplication

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log


class Preferences   ( private  var context: Context){
    var mcontext: Context? = null
    lateinit var sharedPreferences: SharedPreferences
    val editor: SharedPreferences.Editor
    private val PREF_NAME = "TEST"
    init {
        this.mcontext = context
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

    }
    fun saveUserName(userName: String) {
        editor.putString( USER_NAME, userName)
        editor.apply()
    }

    fun getUserName(): String {
        val registeredMobile = sharedPreferences.getString(USER_NAME, USER_NAME)
        return registeredMobile ?: USER_NAME
    }


    fun setLogin(isLoggedIn: Boolean) {
        editor!!.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)

        // commit changes
        editor!!.commit()
        Log.d(ContentValues.TAG, "User login session modified!")
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences!!.getBoolean(KEY_IS_LOGGED_IN, false)
    }
    companion object{
        private const val USER_NAME = "user_name"
        private val KEY_IS_LOGGED_IN = "IS_LOGGED_IN"

    }
}