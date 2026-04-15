package com.example.cwruconnectdroid.model
import android.util.Log
import com.example.cwruconnectdroid.railway.RetrofitClient

object UserRepository {

    private val api = RetrofitClient.instance

    var main_user_id = "4"

    var main_user: User? = null
        private set

    var friendList: List<FriendUser> = emptyList()
        private set

    var guessingList: List<guessingInstance> = emptyList()
        private set

    suspend fun getMainUser(): User? {
        if (main_user == null) {
            reloadMainUser()

            // Only fetch friends if the main user loaded successfully
            if (main_user != null) {
                reloadFriendList()
            }
        }
        return main_user
    }

    fun getUsersFriends(): List<FriendUser> {
        return friendList
    }

    fun getGuessingGame(): List<guessingInstance> {
        return guessingList
    }

    suspend fun reloadMainUser() {
        Log.d("API", "Fetching main user...")
        try {
            main_user = api.getUser(main_user_id)
        } catch (e: Exception) {
            Log.e("API_ERROR", "Failed to fetch user: ${e.message}")
        }
    }

    suspend fun reloadFriendList() {
        Log.d("API", "Fetching user's friends...")
        try {
            friendList = api.getFriends(main_user?.id ?: main_user_id)
        } catch (e: Exception) {
            Log.e("API_ERROR", "Failed to fetch friends: ${e.message}")
        }
    }

    suspend fun updateMainUser(user: User) {
        Log.d("API", "Updating user...")
        try {
            api.updateUser(user)
        } catch (e: Exception) {
            Log.e("API_ERROR", "Failed to update user: ${e.message}")
        }
        reloadMainUser()
    }

    suspend fun addUserFriend(friendID: String) {
        Log.d("API", "Adding friend...")
        try {
            val connection: NewConnection = NewConnection(userid = main_user?.id ?: "", friendID)
            api.addConnection(connection)
        } catch (e: Exception) {
            Log.e("API_ERROR", "Failed to add connection: ${e.message}")
        }
        reloadFriendList()
    }

    suspend fun removeUserFriend(friendID: String) {
        Log.d("API", "Removing friend...")
        try {
            val connection: OldConnection = OldConnection(userid = main_user?.id ?: "", friendID)
            api.removeConnection(connection)
        } catch (e: Exception) {
            Log.e("API_ERROR", "Failed to remove connection: ${e.message}")
        }
        reloadFriendList()
    }

    suspend fun toggleFriendStar(friendID: String) {
        Log.d("API", "Starring friend...")
        try {
            val connection: OldConnection = OldConnection(userid = main_user?.id ?: "", friendID)
            api.toggleStar(connection)
        } catch (e: Exception) {
            Log.e("API_ERROR", "Failed to star connection: ${e.message}")
        }
        reloadFriendList()
    }

    suspend fun updateGuessingGame() {
        Log.d("API", "Starting Guessing Game")
        try {
            guessingList = api.getGameDeck(main_user_id)
        } catch (e: Exception) {
            Log.e("API_ERROR", "Failed to start guessing game: ${e.message}")
        }
    }
}