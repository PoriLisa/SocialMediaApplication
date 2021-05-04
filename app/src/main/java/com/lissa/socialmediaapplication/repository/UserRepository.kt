package com.lissa.socialmediaapplication.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.lissa.socialmediaapplication.dataBase.UserDaoClass
import com.lissa.socialmediaapplication.dataBase.UserDataBase
import kotlinx.coroutines.flow.Flow

class UserRepository(private  val wordDao: UserDaoClass) {
    val getAlluser: Flow<List<UserDataBase>> = wordDao.getUserDetails()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: UserDataBase) {
        wordDao.insert(word)
    }

    suspend fun getUserName(userName: String):UserDataBase?{
        Log.i("MYTAG", "inside Repository Getusers fun ")
        return wordDao.getUsername(userName)
    }
}