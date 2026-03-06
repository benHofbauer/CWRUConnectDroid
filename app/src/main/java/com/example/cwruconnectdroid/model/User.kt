package com.example.cwruconnectdroid.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userID") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("minibio") val bio: String,
    //val profileImageUrl: String = ""
)