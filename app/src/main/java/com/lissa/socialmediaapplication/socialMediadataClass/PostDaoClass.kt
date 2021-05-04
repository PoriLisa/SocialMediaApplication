package com.lissa.socialmediaapplication.socialMediadataClass

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lissa.socialmediaapplication.socialMediadataClass.PostEntityDataClass
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDaoClass {

    @Query("SELECT * FROM postData")
    fun getpostdetails(): Flow<List<PostEntityDataClass>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: PostEntityDataClass)
}