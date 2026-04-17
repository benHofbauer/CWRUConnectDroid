package com.example.cwruconnectdroid.view.Profile.FriendProfile

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.cwruconnectdroid.R
import com.example.cwruconnectdroid.model.FriendUser
import com.example.cwruconnectdroid.viewmodel.FriendListViewModel
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewFriend(
    viewModel: FriendListViewModel,
    onBack: () -> Unit
) {
    val navController = rememberNavController()

    var scannedUserID by remember { mutableStateOf("-1") }
    val context = LocalContext.current

    // Configure the scanner to only look for QR codes
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
        .build()

    // Initialize the scanner client
    val scanner = GmsBarcodeScanning.getClient(context, options)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add New Friend") },
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
        NavHost(
            navController = navController,
            startDestination = "qrScanner",
            modifier = Modifier.padding(padding)
        ) {
            composable("qrScanner") {
                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // QR Scanner Button
                        Button(onClick = {
                            scanner.startScan()
                                .addOnSuccessListener { barcode ->
                                    // Extract the raw string embedded in the QR code
                                    val scannedValue = barcode.rawValue

                                    // Safely parse the integer since you mentioned it's just an int
                                    val parsedId = scannedValue?.toIntOrNull()

                                    scannedUserID = parsedId.toString()

                                    if (scannedUserID != "null") {
                                        viewModel.addFriend(scannedUserID)
                                        navController.navigate("success")
                                    }

                                }
                                .addOnCanceledListener {
                                    // User backed out of the scanner UI - do nothing
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(
                                        context,
                                        "Scan failed: ${e.message}",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                        }) {
                            Text("Scan QR Code")
                        }
                    }
                }
            }

            composable("success") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val friend: FriendUser? = viewModel.getFriendById(scannedUserID)
                    if (friend != null) {
                        Column() {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text("Added ${friend.name}")
                            }

                            Spacer(modifier = Modifier.padding(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                AsyncImage(
                                    model = friend.image_link,
                                    placeholder = painterResource(R.drawable.img_placeholder),
                                    error = painterResource(R.drawable.img_3743),
                                    contentDescription = "Profile Photo",
                                    modifier = Modifier
                                        .size(200.dp)
                                )
                            }
                        }
                    } else {
                        Column (
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row (
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}