package com.mongodb.dublinmug_kmm.android.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mongodb.dublinmug_kmm.android.navigators.DrawerMenuOptions
import kotlinx.coroutines.launch

@Composable
fun BaseContentPresenter(
    pageName: String = "Inventory Manager",
    content: @Composable () -> Unit,
    onDrawerButtonPress: (drawerAction: DrawerMenuOptions) -> Unit
) {
    var drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = {
            MainDrawer(
                onDestinationSelect = {
                    drawerActions -> onDrawerButtonPress(drawerActions)
                }
            )
        },
        content = { BaseContentHolder(pageName, content, drawerState) },
        drawerState = drawerState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseContentHolder(
    pageName: String,
    content: @Composable () -> Unit,
    drawerState: DrawerState
){
    val coroutineScope = rememberCoroutineScope()
    val toggleDrawer = { coroutineScope.launch { if (drawerState.isOpen) drawerState.close() else drawerState.open()} }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text(text = pageName) },
                navigationIcon = {
                    IconButton(
                        onClick = { toggleDrawer() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu"
                        )
                    }
                }
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

@Preview("BaseContentPresenter")
@Composable
fun BaseContentPresenterPreview(){
    MaterialTheme{
        BaseContentPresenter(content = { }) { }
    }
}