package com.example.cwruconnectdroid.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userid") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("caseid") val caseID: String,
    @SerializedName("pronouns") val pronouns: String?,
    @SerializedName("graduation_year") val graduation_year: String?,
    @SerializedName("hometown") val hometown: String?,
    @SerializedName("nationality") val nationality: String?,
    @SerializedName("pronunciation") val pronunciation: String?,
    @SerializedName("minibio") val minibio: String?,
    @SerializedName("fact") val fact: String?,
    @SerializedName("is_public_leaderboard") val is_public_leaderboard: Boolean?,
    @SerializedName("image_link") val image_link: String?
)

data class newUser(
    @SerializedName("name") val name: String,
    @SerializedName("nickname") val nickname: String?,
    @SerializedName("caseid") val caseID: String,
    @SerializedName("pronouns") val pronouns: String?,
    @SerializedName("graduation_year") val graduation_year: String?,
    @SerializedName("hometown") val hometown: String?,
    @SerializedName("nationality") val nationality: String?,
    @SerializedName("pronunciation") val pronunciation: String?,
    @SerializedName("minibio") val minibio: String?,
    @SerializedName("fact") val fact: String?,
)

data class newUserID(
    @SerializedName("userID") val userid: String,
    @SerializedName("status") val status: String
)