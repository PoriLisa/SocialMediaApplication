package com.lissa.socialmediaapplication.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lissa.socialmediaapplication.socialMediadataClass.PostDaoClass
import com.lissa.socialmediaapplication.socialMediadataClass.PostEntityDataClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [UserDataBase::class, PostEntityDataClass::class], version = 4,exportSchema = false)
abstract class UserRoomDataBase : RoomDatabase() {

    abstract fun wordDao(): UserDaoClass
    abstract fun postDao(): PostDaoClass

    companion object {
        @Volatile
        private var INSTANCE: UserRoomDataBase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): UserRoomDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDataBase::class.java,
                    "user_dataBase"
                )

                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.wordDao(), database.postDao())

                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(wordDao: UserDaoClass, postDao: PostDaoClass) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            // wordDao.deleteAll()

            var word = UserDataBase(1, "heyy", "lis@hmail.com", "yes")
            wordDao.insert(word)
            word = UserDataBase(2, "hii", "w@gmail.com", "yes")
            wordDao.insert(word)
        }
    }
}