package com.example.cwruconnectdroid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cwruconnectdroid.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.cwruconnectdroid.repository.UserRepository
import kotlinx.coroutines.launch

class FriendListViewModel : ViewModel() {
    private val repository = UserRepository
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users = _users.asStateFlow()

    init {
        fetchUsersFromDB()
    }

    private fun fetchUsersFromDB() {
        viewModelScope.launch {
            try {
                Log.d("SQL", "Getting Users From Repository (ViewModel)")
                val result = repository.getUsers()
                _users.value = result
            } catch (e: Exception) {
                Log.d("SQL", e.toString())
            }
        }
    }

}

class UserViewModel : ViewModel() {
    private val repository = UserRepository

    private val _user = MutableStateFlow<User>(User(id = -1, name = "-1", bio = "-1"))
    val user = _user.asStateFlow()

    init {
        fetchMainUserFromDB()
    }

    fun UpdateUser(id: Int, name: String, bio: String) {
        updateUserInDB(id, name, bio)
    }

    private fun updateUserInDB(id: Int, name: String, bio: String) {
        viewModelScope.launch {
            try {
                val success = repository.updateUser(id, name, bio)
                if (success) {
                    fetchMainUserFromDB()
                }
            } catch (e: Exception) {
                Log.d("SQL", e.toString())
            }
        }
    }

    private fun fetchMainUserFromDB() {
        // viewModelScope ensures the request is canceled if the user leaves the screen
        viewModelScope.launch {
            try {
                Log.d("SQL", "Getting Main User From Repository (ViewModel)")
                val result = repository.getMainUser()
                _user.value = result
            } catch (e: Exception) {
                Log.d("SQL", e.toString())
            }
        }
    }

}