package com.jetbrains.greeting

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.jetbrains.greeting.navigators.MainNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        MainNavigator()
    }
}