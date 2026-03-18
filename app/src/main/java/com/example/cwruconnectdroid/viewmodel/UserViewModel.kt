package com.example.cwruconnectdroid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cwruconnectdroid.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.cwruconnectdroid.model.UserRepository
import kotlinx.coroutines.launch

class FriendListViewModel : ViewModel() {
    private val repository = UserRepository
    private val _friends = MutableStateFlow<List<User>>(emptyList())
    val users = _friends.asStateFlow()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                Log.d("Model Call", "Getting Users From Repository (ViewModel)")
                val result = repository.getUsersFriends()
                _friends.value = result
            } catch (e: Exception) {
                Log.d("Model Call", e.toString())
            }
        }
    }

    private fun refreshUsers() {
        viewModelScope.launch {
            try {
                Log.d("Model Call", "Refresh User List (ViewModel)")
                val result = repository.reloadFriendList()
                fetchUsers()
            } catch (e: Exception) {
                Log.d("Model Call", e.toString())
            }
        }
    }

    private fun updateUserList() {
        viewModelScope.launch {
            try {
                Log.d("Model Call", "Updating Friend List (ViewModel)")
                val result = repository.updateUserFriendList()
                fetchUsers()
            } catch (e: Exception) {
                Log.d("Model Call", e.toString())
            }
        }
    }

}

class UserViewModel : ViewModel() {
    private val repository = UserRepository

    private val _user = MutableStateFlow<User>(UserRepository.getMainUser())
    val user = _user.asStateFlow()

    init {
        fetchMainUserFromDB()
    }

    private fun fetchMainUserFromDB() {
        viewModelScope.launch {
            try {
                Log.d("Model Call", "Getting Main User From Repository (ViewModel)")
                val result = repository.getMainUser()
                _user.value = result
            } catch (e: Exception) {
                Log.d("Model Call", e.toString())
            }
        }
    }

    private fun refreshMainUser() {
        viewModelScope.launch {
            try {
                Log.d("Model Call", "Refresh User List (ViewModel)")
                val result = repository.reloadMainUser()
                fetchMainUserFromDB()
            } catch (e: Exception) {
                Log.d("Model Call", e.toString())
            }
        }
    }

    private fun updateUserList() {
        viewModelScope.launch {
            try {
                Log.d("Model Call", "Updating Main User List (ViewModel)")
                val result = repository.updateMainUser(user = user.value)
                fetchMainUserFromDB()
            } catch (e: Exception) {
                Log.d("Model Call", e.toString())
            }
        }
    }

}