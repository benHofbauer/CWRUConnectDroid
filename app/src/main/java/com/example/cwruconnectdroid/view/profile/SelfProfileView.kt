package com.example.cwruconnectdroid.view.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cwruconnectdroid.model.User
import com.example.cwruconnectdroid.viewmodel.UserViewModel

@Composable
fun SelfProfile(
    viewModel: UserViewModel = viewModel()
) {
    val user = viewModel.user.collectAsState().value
    Profile(user)
}