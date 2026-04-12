package com.example.cwruconnectdroid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cwruconnectdroid.model.FriendUser
import com.example.cwruconnectdroid.model.User
import com.example.cwruconnectdroid.model.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FriendListViewModel : ViewModel() {
    private val repository = UserRepository

    private val _friends = MutableStateFlow<List<FriendUser>>(emptyList())
    val friends = _friends.asStateFlow()

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                _friends.value = repository.getUsersFriends()
            } catch (e: Exception) {
                Log.e("FriendListViewModel", "Error getting friends: ${e.message}")
            }
        }
    }

    fun refreshUsers() {
        viewModelScope.launch {
            try {
                repository.reloadFriendList()
                fetchUsers() // Update StateFlow with newly cached data
            } catch (e: Exception) {
                Log.e("FriendListViewModel", "Error refreshing friends: ${e.message}")
            }
        }
    }

    fun updateUserList() {
        viewModelScope.launch {
            try {
                repository.updateUserFriendList()
                fetchUsers()
            } catch (e: Exception) {
                Log.e("FriendListViewModel", "Error pushing friend list: ${e.message}")
            }
        }
    }
}

class UserViewModel : ViewModel() {
    private val repository = UserRepository

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    init {
        fetchMainUserFromDB()
    }

    fun fetchMainUserFromDB() {
        viewModelScope.launch {
            try {
                _user.value = repository.getMainUser()
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error getting main user: ${e.message}")
            }
        }
    }

    fun refreshMainUser() {
        viewModelScope.launch {
            try {
                repository.reloadMainUser()
                fetchMainUserFromDB()
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error refreshing user: ${e.message}")
            }
        }
    }

    fun updateUserProfile(userToUpdate: User) {
        viewModelScope.launch {
            try {
                repository.updateMainUser(userToUpdate)

                fetchMainUserFromDB()
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error updating main user: ${e.message}")
            }
        }
    }
}