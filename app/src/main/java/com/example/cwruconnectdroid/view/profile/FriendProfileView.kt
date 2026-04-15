package com.example.cwruconnectdroid.view.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cwruconnectdroid.model.FriendUser
import com.example.cwruconnectdroid.viewmodel.FriendListViewModel
import com.example.cwruconnectdroid.viewmodel.UserViewModel

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
                        Text(
                            text = "BACK"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            FriendProfile(
                user,
                viewModel
            )
        }
    }
}