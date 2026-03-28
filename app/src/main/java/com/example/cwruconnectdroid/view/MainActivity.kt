package com.example.cwruconnectdroid.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cwruconnectdroid.ui.theme.CWRUConnectDroidTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.EmojiPeople
import androidx.compose.material.icons.rounded.Person
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cwruconnectdroid.view.profile.Profile
import com.example.cwruconnectdroid.view.profile.SelfProfile

val MyAppIcons = Icons.Rounded
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CWRUConnectDroidTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
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
                    onClick = { },
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
                SelfProfile()
            }
            composable("friends") {
                FriendScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    CWRUConnectDroidTheme {
        MainApp()
    }
}