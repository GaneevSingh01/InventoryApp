package com.jetbrains.greeting.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

enum class SubPageButtons{
    BACK,
    ADD,
    SEARCH,
    DELETE
}

@Composable
fun SubPageContentPresenter(
    pageName: String,
    content: @Composable () -> Unit,
    activeButtons: List<SubPageButtons> = emptyList(),
    onBackButtonPress: () -> Unit,
    onAddButtonClicked: () -> Unit = {},
    onSearchButtonPress: () -> Unit = {},
    onDeleteButtonClicked: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary
//                ),
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
                            SubPageButtons.DELETE -> {
                                IconButton(
                                    onClick = { onDeleteButtonClicked() }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = "Delete"
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

//@Preview("SubPagePresenter")
//@Composable
//fun SubPageContentPresenterPreview(){
//    MaterialTheme{
//        SubPageContentPresenter(
//            pageName = "Page Name",
//            content = { },
//            listOf(SubPageButtons.ADD, SubPageButtons.BACK, SubPageButtons.SEARCH),
//            onBackButtonPress = {}
//        ) { }
//    }
//}