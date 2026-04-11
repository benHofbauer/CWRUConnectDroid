package com.example.cwruconnectdroid.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.cwruconnectdroid.R
import com.example.cwruconnectdroid.model.User
import com.example.cwruconnectdroid.view.profile.FriendProfile
import com.example.cwruconnectdroid.viewmodel.FriendListViewModel

sealed class Screen(val route: String) {
    object FriendsList : Screen("friends_list")
    object UserProfile : Screen("user_profile/{userId}") {
        fun createRoute(userId: String) = "user_profile/$userId"
    }
}

@Composable
fun FriendScreen(
    viewModel: FriendListViewModel = viewModel()
) {
    val navController = rememberNavController()

    val userList by viewModel.friends.collectAsStateWithLifecycle()

    NavHost(navController = navController, startDestination = Screen.FriendsList.route) {

        composable(Screen.FriendsList.route) {
            FriendsListScreen(
                users = userList,
                onUserClick = { userId ->
                    navController.navigate(Screen.UserProfile.createRoute(userId))
                }
            )
        }

        composable(Screen.UserProfile.route) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            val user = userList.find { it.id == userId }

            //  Explicitly handle the null state instead of drawing a blank screen
            if (user != null) {
                FriendProfile(
                    user = user,
                    onBack = { navController.popBackStack() }
                )
            } else {
                // Fallback UI if the user isn't found or is still loading
                Text(text = "Loading...")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendsListScreen(users: List<User>, onUserClick: (String) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("My Friends") }) }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(users) { user ->
                Box(modifier = Modifier.clickable { onUserClick(user.id) }) {
                    MiniProfile(user)
                }
            }
        }
    }
}

@Composable
fun MiniProfile(
    user: User
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = user.image_link,
            placeholder = painterResource(R.drawable.img_placeholder),
            error = painterResource(R.drawable.img_3743),
            contentDescription = "Profile Photo",
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(percent = 25))
        )

        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = user.name,
                fontSize = 24.sp,
                style = MaterialTheme.typography.headlineMedium,
            )
            user.graduation_year?.let {
                Text(
                    text = it,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFriends() {
    FriendScreen()
}