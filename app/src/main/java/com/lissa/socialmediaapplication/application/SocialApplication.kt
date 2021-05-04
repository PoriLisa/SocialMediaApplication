/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lissa.socialmediaapplication.application

import android.app.Application
import android.content.Context
import com.lissa.socialmediaapplication.dataBase.UserDataBase
import com.lissa.socialmediaapplication.dataBase.UserRoomDataBase
import com.lissa.socialmediaapplication.repository.PostRepository
import com.lissa.socialmediaapplication.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SocialApplication(private  val context: Context) : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { UserRoomDataBase.getDatabase(context, applicationScope) }
    val repository by lazy { UserRepository(database.wordDao()) }
    val postrepository by lazy { PostRepository(database.postDao()) }
    val userDao by lazy { database.wordDao() }
}
