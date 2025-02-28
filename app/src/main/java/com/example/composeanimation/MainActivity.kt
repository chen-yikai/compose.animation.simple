package com.example.composeanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .padding(10.dp)
            ) {
                item {
                    ColorAnimation()
                    MultiPropAnimation()
                    VisbilityAnimation()
                    CrossFadeAnimation()
                    ContentSizeAnimation()
                }
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

@Composable
fun ContentSizeAnimation() {
    var toggle by remember { mutableStateOf(false) }
    Column {
        Button(onClick = { toggle = !toggle }) {
            Text("Change Size")
        }
//        Text(
//            text = """
//            Lorem ipsum dolor sit amet, proident est consequat et occaecat irure incididunt fugiat proident proident quis qui magna excepteur ea ea esse mollit et consectetur consectetur id nostrud ex laboris cupidatat deserunt dolor ullamco mollit dolor ut consectetur ad ut sed duis sed consequat magna ullamco nostrud consectetur sint deserunt veniam qui ea dolore labore enim irure et enim qui anim magna quis ex in duis id laborum tempor ex nulla fugiat veniam nulla culpa eiusmod dolor mollit elit occaecat nulla adipiscing ut fugiat qui sed elit consequat aute aliquip minim minim excepteur enim id adipiscing est minim magna id ullamco fugiat mollit labore sunt
//            """.trimIndent(),
//            maxLines = if (toggle) Int.MAX_VALUE else 2,
//            modifier = Modifier.animateContentSize(animationSpec = tween(durationMillis = 300))
//        )
        Box(
            Modifier
                .height(50.dp)
                .width(if (toggle) 50.dp else 100.dp)
                .background(Color.Red)
                .animateContentSize()
        )
    }
}