package com.example.cwruconnectdroid.railway
import com.example.cwruconnectdroid.model.FriendUser
import com.example.cwruconnectdroid.model.NewConnection
import com.example.cwruconnectdroid.model.NewPhoto
import com.example.cwruconnectdroid.model.OldConnection
import com.example.cwruconnectdroid.model.UploadedPhoto
import com.example.cwruconnectdroid.model.User
import com.example.cwruconnectdroid.model.guessingInstance
import com.example.cwruconnectdroid.model.newUser
import com.example.cwruconnectdroid.model.newUserID
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApiService {
    @GET("get_user_stats/{id}")
    suspend fun getUser(@Path("id") userId: String): User

    @GET("get_connections/{id}")
    suspend fun getFriends(@Path("id") userId: String): List<FriendUser>

    @PUT("update_user")
    suspend fun updateUser(@Body user: User)

    @POST("add_connection")
    suspend fun addConnection(@Body connection: NewConnection)

    @POST("remove_connection")
    suspend fun removeConnection(@Body connection: OldConnection)

    @POST("toggle_star")
    suspend fun toggleStar(@Body connection: OldConnection)

    @GET("get_game_deck/{id}")
    suspend fun getGameDeck(@Path("id") userId: String): List<guessingInstance>

    @POST("update_profile_photo")
    suspend fun updatePhoto(@Body newPhoto: NewPhoto): UploadedPhoto

    @POST("create_user")
    suspend fun createUser(@Body user: newUser): newUserID
}

object RetrofitClient {
    private const val BASE_URL = "https://7hsxg16oej.execute-api.us-east-2.amazonaws.com/"

    val instance: UserApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiService::class.java)
    }
}