package com.example.cwruconnectdroid.view.Game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.cwruconnectdroid.R
import com.example.cwruconnectdroid.model.guessingInstance
import com.example.cwruconnectdroid.viewmodel.GuessingGameViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

@Composable
fun GuessingGameView(
    viewModel: GuessingGameViewModel = viewModel()
) {

    var currentGuess: guessingInstance? = viewModel.currentGuess.collectAsState().value
    val coroutineScope = rememberCoroutineScope()
    var correctCounter by remember { mutableStateOf(0) }
    var answerCounter by remember { mutableStateOf(0) }

    Column (
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (currentGuess != null) {
            GuessView(
                currentGuess
            ) { isCorrect ->
                // 2. Launch a coroutine so we don't block the UI thread!
                coroutineScope.launch {
                    if (isCorrect) {
                        // let em know
                        correctCounter++
                    } else {
                        // let em know
                    }
                    answerCounter++

                    // 3. Suspend for 2 seconds (UI remains completely responsive and redraws)
                    delay(2000)

                    viewModel.getNextGuess()
                }
            }
        } else {
            Text("$correctCounter/$answerCounter correct")
            TextButton(
                { viewModel.refreshGuesses() }
            ) {
                Text("Play again")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GGPreview() {
    var instance = guessingInstance(
        id = "4",
        name = "Billy",
        image_link = "abc",
        choices = listOf("Bob", "Billy", "Joe", "Joie")
    )
    GuessView(instance, {})
}