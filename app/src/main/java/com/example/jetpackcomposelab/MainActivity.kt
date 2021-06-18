package com.example.jetpackcomposelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
        val markeds by remember { mutableStateOf(MutableList(names.size) { false }) }
        LazyColumn(modifier = modifier) {
            itemsIndexed(names) { index, name ->
                Greeting(name = name, markeds[index]) {value -> markeds[index] = value}
                Divider(color = Color.Black)
            }
        }
    }

    @Composable
    fun Greeting(name: String, value: Boolean, onClick: (value: Boolean) -> Unit) {
        var isSelected by remember { mutableStateOf(value) }
        val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)
        Text(text = "Hello $name!", modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable {
                isSelected = !isSelected
                onClick(isSelected)
            }
        )
    }

    @Composable
    fun ClickCounter(count: Int, updateCount: (Int) -> Unit) {
        Text("I've been clicked $count times")
        Button(onClick = { updateCount(count+1) }) {
            Text("Click me")
        }
    }

}