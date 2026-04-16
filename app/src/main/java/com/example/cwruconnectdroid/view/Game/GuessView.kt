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
import coil3.compose.AsyncImage
import com.example.cwruconnectdroid.R
import com.example.cwruconnectdroid.model.guessingInstance

@Composable
fun GuessView(
    instance: guessingInstance,
    onGuessMade: (isCorrect: Boolean) -> Unit
) {

    var correctIdx: Int = -1

    for (i in 0..3) {
        if (instance.choices[i] == instance.name) {
            correctIdx = i
        }
    }

    var guessState by remember(instance) { mutableStateOf<Boolean>(false) }

    Column (
        modifier = Modifier
            .height(600.dp)
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = instance.image_link,
            placeholder = painterResource(R.drawable.img_placeholder),
            error = painterResource(R.drawable.img_3743),
            contentDescription = "Photo to guess",
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(percent = 25))
        )

        Spacer(Modifier.size(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ElevatedButton(
                onClick = {
                    guessState = true
                    if (correctIdx == 0) {
                        onGuessMade(true)
                    } else {
                        onGuessMade(false)
                    }
                },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(percent = 25),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = when (guessState) {
                        true -> if (correctIdx == 0) Color.Green else Color.Red
                        false -> MaterialTheme.colorScheme.surface
                    }
                )
            ) {
                Text(instance.choices[0])
            }

            ElevatedButton(
                onClick = {
                    guessState = true
                    if (correctIdx == 1) {
                        onGuessMade(true)
                    } else {
                        onGuessMade(false)
                    }
                },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(percent = 25),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = when (guessState) {
                        true -> if (correctIdx == 1) Color.Green else Color.Red
                        false -> MaterialTheme.colorScheme.surface
                    }
                )
            ) {
                Text(instance.choices[1])
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ElevatedButton(
                onClick = {
                    guessState = true
                    if (correctIdx == 2) {
                        onGuessMade(true)
                    } else {
                        onGuessMade(false)
                    }
                },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(percent = 25),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = when (guessState) {
                        true -> if (correctIdx == 2) Color.Green else Color.Red
                        false -> MaterialTheme.colorScheme.surface
                    }
                )
            ) {
                Text(instance.choices[2])
            }

            ElevatedButton(
                onClick = {
                    guessState = true
                    if (correctIdx == 3) {
                        onGuessMade(true)
                    } else {
                        onGuessMade(false)
                    }
                },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(percent = 25),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = when (guessState) {
                        true -> if (correctIdx == 3) Color.Green else Color.Red
                        false -> MaterialTheme.colorScheme.surface
                    }
                )
            ) {
                Text(instance.choices[3])
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GamePreview() {
    var instance = guessingInstance(
        id = "4",
        name = "Billy",
        image_link = "abc",
        choices = listOf("Bob", "Billy", "Joe", "Joie")
    )
    GuessView(instance, {})
}