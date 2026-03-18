package com.example.cwruconnectdroid.model
import android.util.Log
import com.example.cwruconnectdroid.railway.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID;

object UserRepository {

    // main user object
    // list of user's friends
    var main_user = User(
        id = UUID.randomUUID().toString(),
        name = "Alan Slice III",
        nickname = "Alan",
        caseID = "ass92",
        pronouns = "it/it",
        graduation_year = "1912",
        hometown = "Twisted Towers",
        nationality = "Refuse to Answer",
        pronunciation = "All-an Sl-I-ce",
        minibio = "You know what I like to do. If you see me around, feel free to say hi. I love to meet new people!",
        fact = "Resistant to psychic attacks.",
        is_public_leaderboard = "sure why not",
        image_link = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdn.stickers.gg%2Fstickers%2F2422-wet-owl.png&f=1&nofb=1&ipt=bfbfa89a71668a96cf1f218ca191efdfea6fe49fe662f3aa21628e46d7699b28"
    )

    var friendList: List<User> = listOf(main_user.copy(), main_user.copy(), main_user.copy())

    // getter methods

    fun getMainUser(): User {
        return main_user
    }

    fun getUsersFriends(): List<User> {
        return friendList
    }

    // function to refresh the main user object from API
    // function to refresh the list of user's friends and corresponding objects from API

    suspend fun reloadMainUser() {
//        Log.d("API","get main user call")
//        try {
//            val result = RetrofitClient.api.getMainUser(main_user.id)
//            main_user = result
//        } catch (e: Exception) {
//            Log.d("SQL", e.toString())
//        }
    }

    suspend fun reloadFriendList() {
//        Log.d("API","get users friends call")
//        try {
//            val result = RetrofitClient.api.getMyConnections(main_user.id)
//            friendList = result
//        } catch (e: Exception) {
//            Log.d("SQL", e.toString())
//        }
    }

    // update the main user object and push changes to API
    // update the users list of friends and push changes to API

    suspend fun updateMainUser(user: User) {

    }

    suspend fun updateUserFriendList() {

    }
}