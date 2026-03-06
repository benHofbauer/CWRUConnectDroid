package com.example.cwruconnectdroid.repository

import android.util.Log
import com.example.cwruconnectdroid.Railway.RetrofitClient
import com.example.cwruconnectdroid.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object UserRepository {
    var main_user = User(id = 2, name = "Alan S", bio = "zxyxwvutsrqponmqabcdefghijkl");

    suspend fun getUsers(): List<User> {
        Log.d("SQL", "get users call")
        return withContext(Dispatchers.IO) {
            try {
                val result = RetrofitClient.api.getMyConnections(main_user.id)
                Log.d("SQL", result.toString())
                result
            } catch (e: Exception) {
                Log.d("SQL", e.toString())
                listOf(User(id = -1, name = "Tee  H.", bio = "sample user bio"))
            }
        }
    }

    suspend fun getMainUser(): User {
        Log.d("SQL","get main user call")
        return withContext(Dispatchers.IO) {
            try {
                val result = RetrofitClient.api.getMainUser(main_user.id)
                result
            } catch (e: Exception) {
                Log.d("SQL", e.toString())
                main_user
            }
        }
    }

    suspend fun updateUser(id: Int, name: String, bio: String): Boolean {
        Log.d("SQL","updating main user call")
        return withContext(Dispatchers.IO) {
            try {
                val new_user = User(id, name, bio)
                RetrofitClient.api.updateUser(new_user)
                main_user = new_user
                true
            } catch (e: Exception) {
                Log.d("SQL", e.toString())
                false
            }
        }
    }
}