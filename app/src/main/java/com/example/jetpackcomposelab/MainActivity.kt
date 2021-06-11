package com.example.jetpackcomposelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreenContent()
        }
    }

    @Preview
    @Composable
    fun MyScreenContent() {
        val count = remember { mutableStateOf(0) }
        Column(modifier = Modifier.fillMaxHeight()) {
            Column(modifier = Modifier.weight(1f)) {
                    Text("Hello")
                    Divider(color = Color.Black)
                }
            ClickCounter(
                count = count.value,
                updateCount = { newCount ->
                    count.value = newCount
                }
            )
            }
        }

    @Composable
    fun ClickCounter(count: Int, updateCount: (Int) -> Unit) {
        Text("I've been clicked $count times")
        Button(onClick = { updateCount(count+1) }) {
            Text("Click me")
        }
    }

    }