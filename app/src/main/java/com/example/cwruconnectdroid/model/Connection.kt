package com.example.cwruconnectdroid.model

import com.google.gson.annotations.SerializedName

data class NewConnection(
    @SerializedName("userid") val userid: String,
    @SerializedName("targetid") val friendid: String,
)

data class OldConnection(
    @SerializedName("userid") val userid: String,
    @SerializedName("friendid") val targetid: String,
)