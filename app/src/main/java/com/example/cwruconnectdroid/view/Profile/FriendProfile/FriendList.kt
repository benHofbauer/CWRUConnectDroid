package com.example.cwruconnectdroid.view.Profile.FriendProfile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Block
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.cwruconnectdroid.model.FriendUser
import com.example.cwruconnectdroid.viewmodel.FriendListViewModel

sealed class Screen(val route: String) {
    object FriendsList : Screen("friends_list")
    object UserProfile : Screen("user_profile/{userId}") {
        fun createRoute(userId: String) = "user_profile/$userId"
    }
}

@Composable
fun FriendScreenNavigation (
    viewModel: FriendListViewModel = viewModel()
) {
    val navController = rememberNavController()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("addFriends")
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Friends")
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "friendsList",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("friendsList") {
                FriendListNavigation(viewModel)
            }
            composable("addFriends") {
                AddNewFriend(
                    viewModel,
                    { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
fun FriendListNavigation(
    viewModel: FriendListViewModel
) {
    val navController = rememberNavController()

    val userList by viewModel.friends.collectAsStateWithLifecycle()

    NavHost(navController = navController, startDestination = Screen.FriendsList.route) {

        composable(Screen.FriendsList.route) {
            FriendListView(
                users = userList,
                viewModel,
                onUserClick = { userId ->
                    navController.navigate(Screen.UserProfile.createRoute(userId))
                }
            )
        }

        composable(Screen.UserProfile.route) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            val user = userList.find { it.id == userId }

            if (user != null) {
                FriendProfileScaffold(
                    user = user,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() }
                )
            } else {
                Text(text = "Loading...")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendListView (
    users: List<FriendUser>,
    viewModel: FriendListViewModel,
    onUserClick: (String) -> Unit
) {
    var editing: Boolean by remember { mutableStateOf(false)}

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Friends") },
                actions = {
                    IconButton(onClick = { editing = !editing }) {
                        Icon(
                            imageVector = Icons.Filled.Block,
                            contentDescription = "Remove Friend",
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(users) { user ->
                if (editing) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BlockingProfileView(user)

                        Column (
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = { viewModel.removeFriend(user.id) }) {
                                Row() {
                                    Text(
                                        text = "Remove Friend "
                                    )
                                    Icon(
                                        imageVector = Icons.Filled.Block,
                                        contentDescription = "Remove Friend",
                                        modifier = Modifier
                                            .size(20.dp),
                                        tint = Color(red = 255, green = 75, blue = 75)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.size(16.dp))
                    }
                } else {
                    Box(modifier = Modifier.clickable { onUserClick(user.id) } .fillMaxWidth()) {
                        MiniProfileEntry(user)
                    }
                }
            }
        }
    }
}

@Composable
fun MiniProfileEntry (
    user: FriendUser
) {
    Row(
        modifier = Modifier
            .padding(16.dp),
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

@Composable
fun BlockingProfileView(
    user: FriendUser
) {
    Row(
        modifier = Modifier
            .padding(16.dp),
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
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFriends() {
    FriendScreenNavigation()
}