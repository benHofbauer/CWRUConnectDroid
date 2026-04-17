package com.example.cwruconnectdroid.view.Profile.UserProfile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.cwruconnectdroid.R
import com.example.cwruconnectdroid.model.User
import com.example.cwruconnectdroid.model.processProfilePhoto
import com.example.cwruconnectdroid.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelfProfileNavigation(
    viewModel: UserViewModel = viewModel()
) {
    val user by viewModel.user.collectAsStateWithLifecycle()
    val navController = rememberNavController()

    Scaffold(
        floatingActionButton = {
            if (
                navController.currentBackStackEntryAsState().value?.destination?.route != "edit"
                &&
                user != null
            ) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate("edit")
                    }
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit Profile")
                }
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
        UserProfileView(user = user)
    } else {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditView(
    user: User,
    viewModel: UserViewModel,
    onBack: () -> Unit,
) {
    var name by remember { mutableStateOf(user.name ?: "") }
    var nickname by remember { mutableStateOf(user.nickname ?: "") }
    var pronouns by remember { mutableStateOf(user.pronouns ?: "") }
    var graduation_year by remember { mutableStateOf(user.graduation_year ?: "") }
    var hometown by remember {mutableStateOf(user.hometown ?: "")}
    var nationality by remember { mutableStateOf(user.nationality ?: "") }
    var pronunciation by remember { mutableStateOf(user.pronunciation ?: "") }
    var minibio by remember {mutableStateOf(user.minibio ?: "")}
    var fact by remember { mutableStateOf(user.fact ?: "") }
    var is_public_leaderboard by remember { mutableStateOf(user.is_public_leaderboard ?: false) }
    var image_link by remember { mutableStateOf(viewModel.user.value?.image_link ?: "") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val updatedUser = user.copy(
                    name = name,
                    nickname = nickname,
                    pronouns = if (pronouns == "") null else pronouns,
                    graduation_year = if (graduation_year == "") null else graduation_year,
                    hometown = if (hometown == "") null else hometown,
                    nationality = if (nationality == "") null else nationality,
                    minibio = if (minibio == "") null else minibio,
                    fact = if (fact == "") null else fact,
                    pronunciation = if (pronunciation == "") null else pronunciation
                )
                // performSave(updatedUser)
                viewModel.updateUserProfile(updatedUser)
                onBack()
            }) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Save Changes"
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

//
//            // nickname
//            OutlinedTextField(
//                value = nickname,
//                onValueChange = { nickname = it },
//                label = { Text("Nickname") },
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(Modifier.size(8.dp))

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

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                ProfilePhotoUploadRow { imageString ->
                    viewModel.updateProfilePhoto(imageString)
                    onBack()
                }
            }

            Row (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = image_link,
                    placeholder = painterResource(R.drawable.img_placeholder),
                    error = painterResource(R.drawable.img_3743),
                    contentDescription = "Profile Photo",
                    modifier = Modifier
                        .size(200.dp),
                )
            }
        }
    }
}

@Composable
fun ProfilePhotoUploadRow(
    onBase64Ready: (String) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Set up the Photo Picker Launcher
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        // This block runs when the user selects a photo (or cancels)
        if (uri != null) {
            // Launch a coroutine to process the image off the main thread
            coroutineScope.launch {
                val base64Image = processProfilePhoto(context, uri)
                if (base64Image != null) {
                    // Pass the final Base64 string to your API
                    onBase64Ready(base64Image)
                } else {
                    // TODO: Handle Error
                }
            }
        }
    }

    // Your original UI Row
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            // Trigger the picker, filtering for images only
            photoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }) {
            Text("Upload Profile Photo")
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