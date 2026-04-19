package com.example.cwruconnectdroid.model
import android.R
import com.google.gson.annotations.SerializedName

data class FriendUser(
    @SerializedName("userid") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("caseID") val caseID: String,
    @SerializedName("pronouns") val pronouns: String?,
    @SerializedName("graduation_year") val graduation_year: String?,
    @SerializedName("hometown") val hometown: String?,
    @SerializedName("nationality") val nationality: String?,
    @SerializedName("pronunciation") val pronunciation: String?,
    @SerializedName("minibio") val minibio: String?,
    @SerializedName("fact") val fact: String?,
    @SerializedName("is_public_leaderboard") val is_public_leaderboard: Boolean?,
    @SerializedName("image_link") val image_link: String,
    @SerializedName("note") val note: String?,
    @SerializedName("starred") val starred: Boolean,
    @SerializedName("matchedAt") val matched_at: String,
)

data class PotentialNewFriend(
    @SerializedName("userid") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("is_reconnection") val reconnect: Boolean,
    @SerializedName("image_link") val image_link: String,
)