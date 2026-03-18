package com.example.cwruconnectdroid.model
import android.util.Log
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

    var temp_user_a = User(
        id = UUID.randomUUID().toString(),
        name = "Porky Pig",
        nickname = "Alan",
        caseID = "pp2",
        pronouns = "he/him",
        graduation_year = "1871",
        hometown = "Twisted Towers",
        nationality = "Dutch",
        pronunciation = "Purky Peeg",
        minibio = "That is all folks.",
        fact = "I changed my name to this.",
        is_public_leaderboard = "sure why not",
        image_link = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Flh5.googleusercontent.com%2F-8SjLt8fjuLE%2FTXJE4OkmGrI%2FAAAAAAAADHI%2F4YtL0fqG8Js%2Fs1600%2FPorky_Pig_4.jpg&f=1&nofb=1&ipt=28e6c7228595b63f3b215962ab3bca50cd0d8fbf53f83b76cdef31d01be63b8d"
    )

    var temp_user_b = User(
        id = UUID.randomUUID().toString(),
        name = "13 Feral Hogs",
        nickname = "Alan",
        caseID = "tfh13",
        pronouns = "they/them",
        graduation_year = "2022",
        hometown = "Your Backyard",
        nationality = "American",
        pronunciation = "Pig Noise",
        minibio = "We are larger than you would expect. Just a heads up in case you ever run into us.",
        fact = "Can't think of many.",
        is_public_leaderboard = "sure why not",
        image_link = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.uaex.uada.edu%2Fenvironment-nature%2Far-invasives%2Fimages%2FFeral%2520hogs%2520at%2520feeder%2520skip%2520armes.jpg&f=1&nofb=1&ipt=04081d687af52a9fd0ca148bf6a8415ed1fcf8bed8f57e20e98df1cf02c0921a"
    )

    var temp_user_c = User(
        id = UUID.randomUUID().toString(),
        name = "Gru from Despicable Me",
        nickname = "Alan",
        caseID = "g2",
        pronouns = "he/him",
        graduation_year = "1985",
        hometown = "Cleveland, OH",
        nationality = "None",
        pronunciation = "Grew",
        minibio = "Something something steal the moon something something.",
        fact = "The first movie is better than you remember.",
        is_public_leaderboard = "sure why not",
        image_link = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fstatic.mytuner.mobi%2Fmedia%2Fpodcasts%2F701%2Fthe-informative-gru.jpg&f=1&nofb=1&ipt=4d37e26db229051b9dfe300a3a377c786b20f56bc0cddab8d0e7b1d0bcd14f3e"
    )

    var temp_user_d = User(
        id = UUID.randomUUID().toString(),
        name = "Dave G",
        nickname = "Alan",
        caseID = "dpg33",
        pronouns = "they/them",
        graduation_year = "1292",
        hometown = "London",
        nationality = "English",
        pronunciation = "Dive-id Jee",
        minibio = "Was london around in 1292? Who knows.... Buisness Major 2028",
        fact = "Weak to psychic attacks and fire.",
        is_public_leaderboard = "sure why not",
        image_link = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fmedia.licdn.com%2Fdms%2Fimage%2Fv2%2FD4E03AQH7p3fznwxmHg%2Fprofile-displayphoto-shrink_200_200%2FB4EZOxJJvSGQAg-%2F0%2F1733843787421%3Fe%3D2147483647%26v%3Dbeta%26t%3DmbQKjzdN8-dhAm9wtXVPFYPerrrTMDJRbL7UnVdfQkI&f=1&nofb=1&ipt=40ba672646957b6161967eccf750371b6cdd6a028501c5d51b1c3b76f05c3226"
    )

    var friendList: List<User> = listOf(temp_user_a, temp_user_b, temp_user_c, temp_user_d)

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