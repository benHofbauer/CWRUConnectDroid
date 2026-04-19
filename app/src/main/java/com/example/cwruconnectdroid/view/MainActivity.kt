package com.example.cwruconnectdroid.view

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.EmojiPeople
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.edit
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cwruconnectdroid.model.UserRepository
import com.example.cwruconnectdroid.ui.theme.CWRUConnectDroidTheme
import com.example.cwruconnectdroid.view.Profile.FriendProfile.FriendScreenNavigation
import com.example.cwruconnectdroid.view.Game.GuessingGameView
import com.example.cwruconnectdroid.view.Profile.UserProfile.SelfProfileNavigation
import com.example.cwruconnectdroid.viewmodel.UserViewModel

val MyAppIcons = Icons.Rounded
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CWRUConnectDroidTheme {
                MainApp(this)
            }
        }
    }
}

@Composable
fun MainApp(context: Context) {
    val sharedPreference =  context.getSharedPreferences("com.example.cwruconnectdroid", Context.MODE_PRIVATE)
    var userid by remember { mutableStateOf(sharedPreference.getString("userid",  null)) }
    val repository = UserRepository
    val viewModel: UserViewModel = viewModel()

//    with (sharedPreference.edit()) {
//        sharedPreference.edit { clear() }
//        apply()
//    }

    if (userid == null) {
        CreateUserScreen(
            onCreateAndPhoto = { newUser, imageString ->
                viewModel.registerAndUploadPhoto(newUser, imageString) { newId ->
                    if (newId != null) {
                        sharedPreference.edit {
                            putString("userid", newId)
                            apply()
                        }
                        userid = newId
                    } else {
                        //
                    }
                }
            }
        )
    } else {
        repository.main_user_id = userid ?: "9"
        AppNaviation()
    }
}

@Composable
fun AppNaviation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("friends") },
                    label = { Text("Friends") },
                    icon = { Icon(MyAppIcons.EmojiPeople, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("profile") },
                    label = { Text("Profile") },
                    icon = { Icon(MyAppIcons.Person, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("learn") },
                    label = { Text("Learn") },
                    icon = { Icon(MyAppIcons.Book, contentDescription = null) }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "profile",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("profile") {
                SelfProfileNavigation()
            }
            composable("friends") {
                FriendScreenNavigation()
            }
            composable ("learn") {
                GuessingGameView()
            }
        }
    }
}