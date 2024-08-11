package com.jetbrains.greeting.views.led_screeens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.jetbrains.greeting.navigators.DrawerMenuOptions
import com.jetbrains.greeting.navigators.LEDMenuOptions
import com.jetbrains.greeting.views.BaseContentPresenter

@Composable
fun LEDHomeScreen(
    onNavigate: (DrawerMenuOptions) -> Unit,
    onMenuOptionClick: (LEDMenuOptions) -> Unit
) {
    BaseContentPresenter(
        content = { HomeScreenContent(onMenuOptionClick) },
        onDrawerButtonPress = { drawerAction ->
            onNavigate(drawerAction)
        }
    )
}

@Composable
fun HomeScreenContent(
    onMenuOptionClick: (LEDMenuOptions) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
        ) {
            itemsIndexed(LEDMenuOptions.values()) { index, item ->
                Button(
                    onClick = { onMenuOptionClick(item) },
                    shape = RectangleShape,
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(
                        text = item.name,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                }
            }
        }
    }
}


//@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview(name = "Welcome dark theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun LEDScreenPreview() {
//    MaterialTheme {
//        LEDHomeScreen(onNavigate = {}, onMenuOptionClick = {})
//    }
//}
