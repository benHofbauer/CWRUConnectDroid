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
        // TODO: Implement API push
    }

    suspend fun updateUserFriendList() {
        // TODO: Implement API push
    }
}