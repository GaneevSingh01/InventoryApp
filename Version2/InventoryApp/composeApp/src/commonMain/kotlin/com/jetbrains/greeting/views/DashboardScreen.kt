package com.jetbrains.greeting.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetbrains.greeting.navigators.DrawerMenuOptions

@Composable
fun DashboardScreen(
    onNavigate: (DrawerMenuOptions) -> Unit
) {
    BaseContentPresenter(
        content = { DashboardContent() },
        onDrawerButtonPress = { drawerAction -> onNavigate(drawerAction)}
    )
}

@Composable
fun DashboardContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        Text(
            text = "Welcome to the Inventory Manager",
        )
        Text(text = "Please select an inventory to manage")
    }
}


//@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview(name = "Welcome dark theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun DashboardScreenPreview() {
//    MaterialTheme {
//        DashboardScreen {}
//    }
//}
