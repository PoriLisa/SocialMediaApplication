package com.lissa.socialmediaapplication.dataBase

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDaoClass {


    @Query("SELECT * FROM userdataBase")

    fun getUserDetails(): Flow<List<UserDataBase>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserDataBase)

    @Query("SELECT * FROM userdataBase WHERE mailId LIKE :userName")
    suspend fun getUsername(userName: String): UserDataBase?
}