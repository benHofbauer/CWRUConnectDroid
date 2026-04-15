package com.example.cwruconnectdroid.view.profile

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cwruconnectdroid.model.User
import com.example.cwruconnectdroid.view.FriendScreen
import com.example.cwruconnectdroid.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelfProfile(
    viewModel: UserViewModel = viewModel()
) {
    val user by viewModel.user.collectAsStateWithLifecycle()
    val navController = rememberNavController()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (user != null) {
                        navController.navigate("edit")
                    }
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "profile",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("profile") {
                SelfProfileView(user)
            }
            composable("edit") {
                if (user != null) {
                    ProfileEditView(
                        user!!,
                        viewModel
                    ) { navController.navigate("profile") }
                } else {
                    navController.navigate("profile")
                }
            }
        }
    }
}

@Composable
fun SelfProfileView(
    user: User?
) {
    if (user != null) {
        UserProfile(user = user)
    } else {
        CircularProgressIndicator()
    }
}

@Composable
fun ProfileEditView(
    user: User,
    viewModel: UserViewModel,
    onBack: () -> Unit,
) {
    var name by remember { mutableStateOf(user.name ?: "") }
    var nickname by remember { mutableStateOf(user.nickname ?: "") }
    var caseid by remember {mutableStateOf(user.caseID ?: "")}
    var pronouns by remember { mutableStateOf(user.pronouns ?: "") }
    var graduation_year by remember { mutableStateOf(user.graduation_year ?: "") }
    var hometown by remember {mutableStateOf(user.hometown ?: "")}
    var nationality by remember { mutableStateOf(user.nationality ?: "") }
    var pronunciation by remember { mutableStateOf(user.pronunciation ?: "") }
    var minibio by remember {mutableStateOf(user.minibio ?: "")}
    var fact by remember { mutableStateOf(user.fact ?: "") }
    var is_public_leaderboard by remember { mutableStateOf(user.is_public_leaderboard ?: false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // name
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.size(8.dp))

        // nickname
        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { Text("Nickname") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.size(8.dp))

        // caseid
        // TODO: Default value shows up for case ID
        OutlinedTextField(
            value = caseid,
            onValueChange = { caseid = it },
            label = { Text("case id") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.size(8.dp))

        // pronouns
        OutlinedTextField(
            value = pronouns,
            onValueChange = { pronouns = it },
            label = { Text("Pronouns") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.size(8.dp))

        // grad year
        OutlinedTextField(
            value = graduation_year,
            onValueChange = { graduation_year = it },
            label = { Text("Graduation Year") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.size(8.dp))

        // hometown
        OutlinedTextField(
            value = hometown,
            onValueChange = { hometown = it },
            label = { Text("Hometown") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.size(8.dp))

        // nationality
        OutlinedTextField(
            value = nationality,
            onValueChange = { nationality = it },
            label = { Text("Nationality") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.size(16.dp))

        Button(onClick = {
            val updatedUser = user.copy(
                name = name,
                nickname = nickname,
                caseID = caseid,
                pronouns = if (pronouns == "") null else pronouns,
                graduation_year = if (graduation_year == "") null else graduation_year,
                hometown = if (hometown == "") null else hometown,
                nationality = if (nationality == "") null else nationality,
            )
            // performSave(updatedUser)
            viewModel.updateUserProfile(updatedUser)
            onBack()
        }) {
            Text("Save Changes")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    var user = User(
        id = "4",
        name = "Ham Bone",
        nickname = "Hammy",
        caseID = "hjb22",
        pronouns = null,
        graduation_year = null,
        hometown = null,
        nationality = null,
        pronunciation = null,
        minibio = null,
        fact = null,
        is_public_leaderboard = false,
        image_link = "null"
    )

    ProfileEditView(user!!, viewModel()) {}
}