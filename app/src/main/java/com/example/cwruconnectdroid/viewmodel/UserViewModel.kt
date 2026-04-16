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

    fun updateProfilePhoto(encodedImage: String) {
        viewModelScope.launch {
            try {
                //repository.
                fetchMainUserFromDB()
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error updating main user: ${e.message}")
            }
        }
    }
}