package com.example.cwruconnectdroid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cwruconnectdroid.model.FriendUser
import com.example.cwruconnectdroid.model.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.enums.enumEntries

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


    fun addFriend(friendToAdd: String) {
        viewModelScope.launch {
            try {
                repository.addUserFriend(friendToAdd)
                refreshUsers()
            } catch (e: Exception) {
                Log.e("FriendListViewModel", "Error adding friend: ${e.message}")
            }
        }
    }

    fun removeFriend(friendToRemove: String) {
        viewModelScope.launch {
            try {
                repository.removeUserFriend(friendToRemove)
                refreshUsers()
            } catch (e: Exception) {
                Log.e("FriendListViewModel", "Error removing freidn: ${e.message}")
            }
        }
    }

    fun toggleFriendStar(friendToToggle: String) {
        viewModelScope.launch {
            try {
                repository.toggleFriendStar(friendToToggle)
                refreshUsers()
            } catch (e: Exception) {
                Log.e("FriendListViewModel", "Error toggling friend star: ${e.message}")
            }
        }
    }

    fun getFriendById(friendID: String): FriendUser? {
        return friends.value.firstOrNull() { it.id == friendID }
    }
}