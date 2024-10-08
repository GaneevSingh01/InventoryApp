package com.mongodb.dublinmug_kmm.android.views.led_screeens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mongodb.dublinmug_kmm.android.navigators.DrawerMenuOptions
import com.mongodb.dublinmug_kmm.android.views.BaseContentPresenter

@Composable
fun LEDStockScreen(
    onNavigate: (DrawerMenuOptions) -> Unit,
) {
    BaseContentPresenter(
        content = { StockScreenContent() },
        onDrawerButtonPress = { drawerAction ->
            onNavigate(drawerAction)
        }
    )
}

@Composable
fun StockScreenContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        Text("LED Stock")
    }
}


@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Welcome dark theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LEDStockScreenPreview() {
    MaterialTheme {
        LEDStockScreen(onNavigate = {})
    }
}
