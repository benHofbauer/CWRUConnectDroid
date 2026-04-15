package com.example.cwruconnectdroid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cwruconnectdroid.model.FriendUser
import com.example.cwruconnectdroid.model.UserRepository
import com.example.cwruconnectdroid.model.guessingInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GuessingGameViewModel : ViewModel() {
    private val repository = UserRepository
    private val _friends = MutableStateFlow<List<guessingInstance>>(emptyList())

    private val _currentGuess = MutableStateFlow<guessingInstance?>(null)
    val currentGuess: StateFlow<guessingInstance?> = _currentGuess.asStateFlow()
    private var gamePointer: Int = 0

    init {
        refreshGuesses()
        gamePointer = 0
    }

    fun refreshGuesses() {
        viewModelScope.launch {
            try {
                repository.updateGuessingGame()
                val newGuesses = repository.getGuessingGame()
                _friends.value = newGuesses

                gamePointer = 0

                getNextGuess()

            } catch (e: Exception) {
                Log.e("GuessingGameViewModel", "Error getting friends: ${e.message}")
            }
        }
    }

    fun getNextGuess() {
        val currentList = _friends.value

        if (gamePointer < currentList.size) {
            _currentGuess.value = currentList[gamePointer]
            gamePointer++
        } else {
            _currentGuess.value = null
            Log.d("GuessingGameViewModel", "No more guesses available!")
        }
    }
}