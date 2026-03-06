package com.example.cwruconnectdroid.Railway
import com.example.cwruconnectdroid.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("get_my_connections")
    suspend fun getMyConnections(@Query("userID") userId: Int): List<User>

    @GET("get_user")
    suspend fun getMainUser(@Query("userID") userId: Int): User

    @POST("update_user")
    suspend fun updateUser(@Body request: User): Unit
}

object RetrofitClient {
    private const val BASE_URL = "https://cwruconnect-api-production.up.railway.app/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}