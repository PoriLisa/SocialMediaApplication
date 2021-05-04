package com.lissa.socialmediaapplication.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "userdataBase")

data class UserDataBase(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "mailId")
    var mailId: String? = null,

    @ColumnInfo(name = "password")
    var password: String? = null
)