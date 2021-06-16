package com.example.jetpackcomposelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreenContent()
        }
    }

    @Preview
    @Composable
    fun MyScreenContent(names: List<String> = List(100) { "Hello Android #$it" }) {
        val count = remember { mutableStateOf(0) }
        Column(modifier = Modifier.fillMaxHeight()) {
            NameList(names, Modifier.weight(1f))
            ClickCounter(
                count = count.value,
                updateCount = { newCount ->
                    count.value = newCount
                }
            )
        }
    }

    @Composable
    fun NameList(names: List<String>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(names) { name ->
                Greeting(name = name)
                Divider(color = Color.Black)
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
    }

    @Composable
    fun ClickCounter(count: Int, updateCount: (Int) -> Unit) {
        Text("I've been clicked $count times")
        Button(onClick = { updateCount(count+1) }) {
            Text("Click me")
        }
    }

}