package com.example.cwruconnectdroid.view.profile

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.cwruconnectdroid.R
import com.example.cwruconnectdroid.model.User
import com.example.cwruconnectdroid.viewmodel.UserViewModel

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

@Preview(showBackground = true)
@Composable
fun PreviewPFPView() {
    PFPView(
        imageLink = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Flh5.googleusercontent.com%2F-8SjLt8fjuLE%2FTXJE4OkmGrI%2FAAAAAAAADHI%2F4YtL0fqG8Js%2Fs1600%2FPorky_Pig_4.jpg&f=1&nofb=1&ipt=28e6c7228595b63f3b215962ab3bca50cd0d8fbf53f83b76cdef31d01be63b8d",
        qrLink = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=4"
    )
}