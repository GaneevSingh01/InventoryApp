package com.jetbrains.greeting.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jetbrains.greeting.navigators.DrawerMenuOptions

@Composable
fun BaseContentPresenter(
    pageName: String = "Inventory Manager",
    content: @Composable () -> Unit,
    onDrawerButtonPress: (drawerAction: DrawerMenuOptions) -> Unit
) {
    BaseContentHolder(pageName, content)
}

@Composable
fun BaseContentHolder(
    pageName: String,
    content: @Composable () -> Unit,
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = pageName) },
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ) {
            content()
        }
    }
}