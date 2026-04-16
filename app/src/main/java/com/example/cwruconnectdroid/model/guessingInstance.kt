package com.example.cwruconnectdroid.model
import com.google.gson.annotations.SerializedName

data class guessingInstance(
    @SerializedName("userid") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image_link") val image_link: String,
    @SerializedName("choices") val choices: List<String>,
)