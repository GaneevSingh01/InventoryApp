package com.mongodb.dublinmug_kmm.android.views.led_screeens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mongodb.dublinmug_kmm.android.navigators.DrawerMenuOptions
import com.mongodb.dublinmug_kmm.android.navigators.LEDMenuOptions
import com.mongodb.dublinmug_kmm.android.views.BaseContentPresenter
import com.mongodb.dublinmug_kmm.models.ItemModel
import com.mongodb.dublinmug_kmm.models.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun LEDOrderScreen(
    onNavigate: (DrawerMenuOptions) -> Unit,
    onMenuOptionClick: (LEDMenuOptions) -> Unit,
    itemModel: ItemModel
) {
    BaseContentPresenter(
        content = { OrderScreenContent(onMenuOptionClick, itemModel) },
        onDrawerButtonPress = { drawerAction ->
            onNavigate(drawerAction)
        }
    )
}

@Composable
fun OrderScreenContent(
    onMenuOptionClick: (LEDMenuOptions) -> Unit,
    itemModel: ItemModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        InventoryItem(itemModel)
    }
}

@Composable
fun InventoryItem(
    itemModel: ItemModel
){
    val coroutineScope = rememberCoroutineScope()

    Surface(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
        modifier = Modifier.fillMaxWidth()
    ) {
        val modifier = Modifier.padding(vertical = 5.dp)
        var name by rememberSaveable { mutableStateOf("") }

        Column (
            modifier = modifier.padding(10.dp)
        ){
            TextField(
                value = name,
                onValueChange = {name = it},
                modifier = modifier
            )
//            Text(
//                text = "Quantity",
//                modifier = modifier
//            )
            Button(
                onClick = { coroutineScope.launch { itemModel.sendToDB(name) } }
            ) {
                Text(text = "Send to DB")
            }
        }
    }
}


@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Welcome dark theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LEDOrderScreenPreview() {
    MaterialTheme {
        LEDOrderScreen(
            onNavigate = {},
            onMenuOptionClick = {},
            ItemModel(MainViewModel())
        )
    }
}
