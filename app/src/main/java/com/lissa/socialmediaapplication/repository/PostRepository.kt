package com.lissa.socialmediaapplication.repository

import androidx.annotation.WorkerThread
import com.lissa.socialmediaapplication.dataBase.UserDaoClass
import com.lissa.socialmediaapplication.dataBase.UserDataBase
import com.lissa.socialmediaapplication.socialMediadataClass.PostDaoClass
import com.lissa.socialmediaapplication.socialMediadataClass.PostEntityDataClass
import kotlinx.coroutines.flow.Flow

class PostRepository(private val wordDao: PostDaoClass) {
    val getAllPost: Flow<List<PostEntityDataClass>> = wordDao.getpostdetails()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(post: PostEntityDataClass) {
        wordDao.insert(post)
    }

}