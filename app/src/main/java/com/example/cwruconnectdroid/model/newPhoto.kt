package com.example.cwruconnectdroid.model

import com.google.gson.annotations.SerializedName

data class NewPhoto(
    @SerializedName("userid") val userid: String,
    @SerializedName("image") val image: String,
)

data class UploadedPhoto(
    @SerializedName("status") val status: String,
    @SerializedName("image_link") val imageLink: String,
)