package com.example.se_al.data


import androidx.lifecycle.LiveData
import com.example.se_al.data.user.User

class Repository(mDatabase: UserDatabase) {

    private val userDao = mDatabase.userDao()
    val allUsers: LiveData<List<User>> = userDao.getAll()

    companion object{
        private var sInstance: Repository? = null
        fun getInstance(database: UserDatabase): Repository {
            return sInstance
                ?: synchronized(this){
                    val instance = Repository(database)
                    sInstance = instance
                    instance
                }
        }
    }

    suspend fun insert(user: User) {
        userDao.insert(user)
    }
}