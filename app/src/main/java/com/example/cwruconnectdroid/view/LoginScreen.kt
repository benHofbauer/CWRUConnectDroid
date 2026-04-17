package com.example.cwruconnectdroid.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cwruconnectdroid.model.newUser
import com.example.cwruconnectdroid.view.Profile.UserProfile.ProfilePhotoUploadRow
import com.example.cwruconnectdroid.viewmodel.UserViewModel

@Composable
fun CreateUserScreen(
    //context: Context
    onCreate: (user: newUser) -> Unit,
    addPhoto: () -> Unit,
) {

    var photoTime by remember { mutableStateOf(false) };

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.size(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Welcome to CWRU Connect",
                fontSize = 20.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            if (!photoTime) {
                Text(
                    text = "Enter your information below to get started",
                    fontSize = 16.sp
                )
            } else {
                Text(
                    text = "Upload your profile photo to finish your account",
                    fontSize = 16.sp
                )
            }
        }

        CreateProfileForm { user: newUser ->
            onCreate(user)
            photoTime = true
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProfileForm(
    onBack: (user: newUser) -> Unit,
) {
    var name by remember { mutableStateOf( "") }
    var caseid by remember { mutableStateOf("") }
    var pronouns by remember { mutableStateOf("") }
    var graduation_year by remember { mutableStateOf("") }
    var hometown by remember {mutableStateOf("")}
    var nationality by remember { mutableStateOf("") }
    var pronunciation by remember { mutableStateOf("") }
    var minibio by remember {mutableStateOf("")}
    var fact by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val user: newUser = newUser(
                    name = name,
                    nickname = "",
                    caseID = caseid,
                    pronouns = pronouns,
                    graduation_year = graduation_year,
                    hometown = hometown,
                    nationality = nationality,
                    pronunciation = pronunciation,
                    minibio = minibio,
                    fact = fact
                )

                onBack(user)

            }) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Create Account"
                )
            }
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


            // caseid
            OutlinedTextField(
                value = caseid,
                onValueChange = { caseid = it },
                label = { Text("Case ID") },
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
        }
    }
}

@Composable
fun CreateNewUserPhoto(
    onBack: (imageString: String) -> Unit,
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        ProfilePhotoUploadRow { imageString ->
            onBack(imageString)
        }
    }
}