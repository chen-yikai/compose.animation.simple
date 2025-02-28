package com.example.composeanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .padding(10.dp)
            ) {
                ColorAnimation()
                MultiPropAnimation()
                VisbilityAnimation()
                CrossFadeAnimation()
            }
        }
    }
}

@Composable
fun ColorAnimation() {
    var toggle: Boolean by remember { mutableStateOf(false) }
    val color by animateColorAsState(if (toggle) Color.Red else Color.Yellow, label = "")

    Column {
        Button(onClick = {
            toggle = !toggle
        }) { Text("Click to change color") }
        Box(
            Modifier
                .background(color)
                .size(100.dp)
        )
    }
}

@Composable
fun MultiPropAnimation() {
    var toggle: Boolean by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = toggle, label = "")
    val color by transition.animateColor(label = "") { s ->
        when (s) {
            true -> Color.Red
            false -> Color.Yellow
        }
    }
    val size by transition.animateDp(label = "") { s ->
        when (s) {
            true -> 200.dp
            false -> 100.dp
        }
    }

    Column {
        Button(onClick = {
            toggle = !toggle
        }) { Text("Click to change Color & Size") }
        Box(
            Modifier
                .background(color)
                .size(size)
        )
    }
}

@Composable
fun VisbilityAnimation() {
    var toggle: Boolean by remember { mutableStateOf(false) }

    Column {
        Button(onClick = {
            toggle = !toggle
        }) { Text("Click to Show & Hide") }
        AnimatedVisibility(toggle) {
            Box(
                Modifier
                    .background(Color.Red)
                    .size(100.dp)
            )
        }
    }
}

@Composable
fun CrossFadeAnimation() {
    var toggle by remember { mutableStateOf(false) }
    Column {
        Button(onClick = { toggle = !toggle }) {
            Text("Toggle to see CrossFade")
        }
        Crossfade(toggle) { toggle ->
            when (toggle) {
                true -> Icon(Icons.Default.Add, contentDescription = "")
                false -> Icon(Icons.Default.Call, contentDescription = "")
            }
        }
    }
}