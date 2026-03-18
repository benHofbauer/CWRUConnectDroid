package com.example.cwruconnectdroid.view
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cwruconnectdroid.R
import com.example.cwruconnectdroid.model.User
import com.example.cwruconnectdroid.viewmodel.UserViewModel
import coil3.compose.AsyncImage

@Composable
fun Profile(user: User) {
    val image =
    Column (
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ******** PROFILE IMAGE ******** //
        AsyncImage(
            model = "https://example.com/image.jpg",
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

            Text(
                text = "User's Major",
                //modifier = Modifier.weight(1f),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
            )


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
//    Row(
//        modifier = Modifier
//            .fillMaxHeight()
//            .fillMaxWidth()
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//
//        Spacer(modifier = Modifier.width(10.dp))
//        Column(
//            modifier = Modifier
//                .padding(16.dp),
//            horizontalAlignment = Alignment.Start
//        ) {
//
//        }
//    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProfile() {
    var viewModel: UserViewModel = viewModel()
    val user by viewModel.user.collectAsState()
    Profile(user)
}