package com.example.cwruconnectdroid.view.Profile.UserProfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cwruconnectdroid.model.User
import com.example.cwruconnectdroid.view.Profile.UserProfile.PFPView

@Composable
fun UserProfileView(
    user: User
) {
    val qrLink = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=${user.id}"

    Column (
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PFPView(
            user.image_link ?: "",
            qrLink = qrLink
        )

        Spacer(modifier = Modifier.height(10.dp))

        // ******** NAME ******** //
        Text(
            text = user.name,
            fontSize = 36.sp,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif
        )

        // ********** Pronunciation ******* //
        user.pronunciation?.let {
            //Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = it,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        user.minibio?.let {
            Text(
                text = it,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            user.pronouns?.let {
                Text(
                    text = it,
                    //modifier = Modifier.weight(1f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif,
                )
            }

//            Text(
//                text = "User's Major",
//                //modifier = Modifier.weight(1f),
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Normal,
//                fontFamily = FontFamily.SansSerif,
//            )


            user.graduation_year?.let {
                Text(
                    text = it,
                    //modifier = Modifier.weight(1f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif,
                )
            }
        }

        Row (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            user.hometown?.let {
                Text(
                    text = it,
                    //modifier = Modifier.weight(1f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif,
                )
            }

            user.nationality?.let {
                Text(
                    text = it,
                    //modifier = Modifier.weight(1f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif,
                )
            }
        }
    }
}