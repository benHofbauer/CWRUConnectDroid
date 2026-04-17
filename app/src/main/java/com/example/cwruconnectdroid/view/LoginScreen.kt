package com.example.cwruconnectdroid.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cwruconnectdroid.viewmodel.UserViewModel

@Composable
fun CreateUserScreen(
    //context: Context
) {

    Column() {

    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    CreateUserScreen()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProfileView(
    viewModel: UserViewModel,
    onBack: () -> Unit,
) {
    var name by remember { mutableStateOf( "") }
    var nickname by remember { mutableStateOf("") }
    var pronouns by remember { mutableStateOf("") }
    var graduation_year by remember { mutableStateOf("") }
    var hometown by remember {mutableStateOf("")}
    var nationality by remember { mutableStateOf("") }
    var pronunciation by remember { mutableStateOf("") }
    var minibio by remember {mutableStateOf("")}
    var fact by remember { mutableStateOf("") }
    var is_public_leaderboard by remember { mutableStateOf(false) }
    var image_link by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // create new user and log user id
            }) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Create Account"
                )
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Edit Profile") },
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
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // name
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )


            // nickname
            OutlinedTextField(
                value = nickname,
                onValueChange = { nickname = it },
                label = { Text("Nickname") },
                modifier = Modifier.fillMaxWidth()
            )

            // pronunciation
            OutlinedTextField(
                value = pronunciation,
                onValueChange = { pronunciation = it },
                label = { Text("Pronunciation") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            // pronouns
            OutlinedTextField(
                value = pronouns,
                onValueChange = { pronouns = it },
                label = { Text("Pronouns") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            // minibio
            OutlinedTextField(
                value = minibio,
                onValueChange = { minibio = it },
                label = { Text("Bio") },
                minLines = 3,
                maxLines = 7,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            // fact
            OutlinedTextField(
                value = fact,
                onValueChange = { fact = it },
                label = { Text("Fun Fact") },
                minLines = 2,
                maxLines = 3,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            // grad year
            OutlinedTextField(
                value = graduation_year,
                onValueChange = { graduation_year = it },
                label = { Text("Graduation Year") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            // hometown
            OutlinedTextField(
                value = hometown,
                onValueChange = { hometown = it },
                label = { Text("Hometown") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            // nationality
            OutlinedTextField(
                value = nationality,
                onValueChange = { nationality = it },
                label = { Text("Nationality") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

//          Make this a next screen
//
//            Row (
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                ProfilePhotoUploadRow { imageString ->
//                    viewModel.updateProfilePhoto(imageString)
//                    onBack()
//                }
//            }
//
//            Row (
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                AsyncImage(
//                    model = image_link,
//                    placeholder = painterResource(R.drawable.img_placeholder),
//                    error = painterResource(R.drawable.img_3743),
//                    contentDescription = "Profile Photo",
//                    modifier = Modifier
//                        .size(200.dp),
//                )
//            }
        }
    }
}