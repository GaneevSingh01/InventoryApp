package com.mongodb.dublinmug_kmm.android.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

enum class SubPageButtons{
    BACK,
    ADD,
    SEARCH
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubPageContentPresenter(
    pageName: String,
    content: @Composable () -> Unit,
    activeButtons: List<SubPageButtons> = emptyList(),
    onBackButtonPress: () -> Unit,
    onAddButtonClicked: () -> Unit = {},
    onSearchButtonPress: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(text = pageName)
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onBackButtonPress() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    for(button in activeButtons) {
                        when (button) {
                            SubPageButtons.SEARCH -> {
                                IconButton(
                                    onClick = { onSearchButtonPress() }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = "Search"
                                    )
                                }
                            }
                            SubPageButtons.ADD -> {
                                IconButton(
                                    onClick = { onAddButtonClicked() }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Add,
                                        contentDescription = "Add"
                                    )
                                }
                            }
                            else -> {}
                        }
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

@Preview("SubPagePresenter")
@Composable
fun SubPageContentPresenterPreview(){
    MaterialTheme{
        SubPageContentPresenter(
            pageName = "Page Name",
            content = { },
            listOf(SubPageButtons.ADD, SubPageButtons.BACK, SubPageButtons.SEARCH),
            onBackButtonPress = {}
        ) { }
    }
}