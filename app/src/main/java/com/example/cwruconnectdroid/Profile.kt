package com.example.cwruconnectdroid
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cwruconnectdroid.model.User
import com.example.cwruconnectdroid.viewmodel.UserViewModel

@Composable
fun ProfileNavigation(
    viewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val navController = rememberNavController()
    val user by viewModel.user.collectAsState()

    NavHost(navController, startDestination = "profile") {
        composable("profile") {
            ProfileScreen(
                viewModel = viewModel,
                onEditClick = { navController.navigate("edit_profile") }
            )
        }
        composable("edit_profile") {
            EditProfileScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
@Composable
fun ProfileScreen(
    viewModel: UserViewModel,
    onEditClick: () -> Unit
) {
    val user by viewModel.user.collectAsState()
    Box() {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.hsv(210F, 1F, .5F, alpha=.5F)
        ) {

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Profile(user)
        }

        IconButton(
            onClick = onEditClick,
            modifier = Modifier
                .align(Alignment.TopEnd) // Anchors to the top-right corner
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit Profile",
                tint = Color.White
            )
        }

    }
}

@Composable
fun Profile(user: User) {
    val image = painterResource(R.drawable.img_3743)
    Row(
        modifier = Modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = image,
            contentDescription = "Profile Photo",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = user.name,
                fontSize = 36.sp,
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = user.bio,
                fontSize = 12.sp,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun EditProfileScreen(
    viewModel: UserViewModel,
    onNavigateBack: () -> Unit
) {
    val user by viewModel.user.collectAsState()
    var nameInput by remember { mutableStateOf(user.name) }
    var bioInput by remember { mutableStateOf(user.bio) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Name") }
        )

        OutlinedTextField(
            value = bioInput,
            onValueChange = { bioInput = it },
            label = { Text("Email") }
        )

        Button(onClick = {
            viewModel.UpdateUser(user.id, user.name, user.bio)
            onNavigateBack()
        }) {
            Text("Save Changes")
        }

        Button(onClick = {
            onNavigateBack()
        }) {
            Text("Discard Changes")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfile() {
    ProfileNavigation()
}