package com.example.cwruconnectdroid.view.profile

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cwruconnectdroid.model.User
import com.example.cwruconnectdroid.viewmodel.UserViewModel

@Composable
fun SelfProfile(
    viewModel: UserViewModel = viewModel()
) {
    // Lifecycle-aware collection. Using 'by' delegates the value directly.
    val user by viewModel.user.collectAsStateWithLifecycle()

    if (user != null) {
        Profile(user = user!!)
    } else {
        CircularProgressIndicator()
    }
}