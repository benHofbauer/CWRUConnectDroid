package com.example.cwruconnectdroid.view.Profile.UserProfile

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.cwruconnectdroid.R

@Composable
fun PFPView(
    imageLink: String,
    qrLink: String
) {
    var showing = remember { mutableStateOf(true) }

    ElevatedButton(
        onClick = {
            showing.value = !showing.value
                  },
        modifier = Modifier
            .size(200.dp),
        shape = RoundedCornerShape(percent = 25)
    ) {
        AsyncImage(
            model = if (showing.value) imageLink else qrLink,
            placeholder = painterResource(R.drawable.img_placeholder),
            error = painterResource(R.drawable.img_3743),
            contentDescription = "Profile Photo",
            modifier = Modifier
                .size(200.dp)
        )
    }
}