package com.example.stopwatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StopwatchApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopwatchApp() {
    var time by remember { mutableStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(10L)
            time += 10L
        }
    }

    val minutes = (time / 60000).toString().padStart(2, '0')
    val seconds = ((time % 60000) / 1000).toString().padStart(2, '0')
    val milliseconds = ((time % 1000) / 10).toString().padStart(2, '0')

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.lightviolet))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(colorResource(id = R.color.purple_200), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$minutes:$seconds:$milliseconds",
                fontSize = 32.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { isRunning = !isRunning },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.purple_200))
            ) {
                Text(text = if (isRunning) "Pause" else "Start")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    isRunning = false
                    time = 0L
                },
                colors = ButtonDefaults.buttonColors( colorResource(id = R.color.purple_200))
            ) {
                Text(text = "Reset")
            }
        }
    }
}
