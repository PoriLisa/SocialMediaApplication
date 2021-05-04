package com.lissa.socialmediaapplication.socialMediadataClass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "postData")

class PostEntityDataClass(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

 /*   @ColumnInfo(name = "userIds")
    var postedByUserId: Int? = null,*/
    @ColumnInfo(name = "userName")
    var postedByUserName: String? = null,

    @ColumnInfo(name = "post")
    var post: String? = null,

    ) {
    override fun toString(): String {
        return "PostEntityDataClass(id=$id, postedByUserName=$postedByUserName, post=$post)"
    }
}