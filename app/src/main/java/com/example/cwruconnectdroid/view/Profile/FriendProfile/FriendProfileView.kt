package com.example.cwruconnectdroid.view.Profile.FriendProfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.cwruconnectdroid.R
import com.example.cwruconnectdroid.model.FriendUser
import com.example.cwruconnectdroid.viewmodel.FriendListViewModel

@Composable
fun FriendProfileView(
    user: FriendUser,
    viewModel: FriendListViewModel
) {
    var qrLink = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=${user.id}"

    Column (
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            Spacer(modifier = Modifier)

            IconButton(onClick = { viewModel.toggleFriendStar(user.id) }) {
                Icon(
                    imageVector = if (user.starred) Icons.Filled.Star else Icons.Default.StarBorder,
                    contentDescription = "Toggle Best Friend",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                )
            }
        }

        AsyncImage(
            model = user.image_link,
            placeholder = painterResource(R.drawable.img_placeholder),
            error = painterResource(R.drawable.img_3743),
            contentDescription = "Profile Photo",
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(percent = 25))
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendProfileScaffold(
    user: FriendUser,
    viewModel: FriendListViewModel,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(user?.name ?: "Profile") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back Button"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            FriendProfileView(
                user,
                viewModel
            )
        }
    }
}