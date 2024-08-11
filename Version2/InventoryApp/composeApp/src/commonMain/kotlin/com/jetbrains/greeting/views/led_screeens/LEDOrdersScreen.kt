package com.jetbrains.greeting.views.led_screeens

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
import com.jetbrains.greeting.views.BaseContentPresenter

@Composable
fun LEDOrderScreen(
    onNavigate: (DrawerMenuOptions) -> Unit,
) {
    BaseContentPresenter(
        content = { OrderScreenContent() },
        onDrawerButtonPress = { drawerAction ->
            onNavigate(drawerAction)
        }
    )
}

@Composable
fun OrderScreenContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        Text("Orders")
    }
}

//@Composable
//fun InventoryItem(
//    productModel: ProductModel
//){
//    Surface(
//        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        val modifier = Modifier.padding(vertical = 5.dp)
//        var name by rememberSaveable { mutableStateOf("") }
//        var details by rememberSaveable { mutableStateOf("") }
//
//        Column (
//            modifier = modifier.padding(10.dp)
//        ){
//            Text(text = "Item name:")
//            TextField(
//                value = name,
//                onValueChange = {name = it},
//                modifier = modifier
//            )
//            Text(text = "Item details:")
//            TextField(
//                value = details,
//                onValueChange = {details = it},
//                modifier = modifier
//            )
//
//            Button(
//                onClick = { runBlocking { productModel.saveNewProduct(name, details) } }
//            ) {
//                Text(text = "Send to DB")
//            }
//            QueriesList(productModel)
//        }
//    }
//}
//
//@Composable
//fun QueriesList(
//    productModel: ProductModel,
////    onEditClick: (QueryInfo) -> Unit
//) {
//
//    val queries = productModel.getItemDataLive().observeAsState(initial = emptyList()).value
//
//    LazyColumn(
//        verticalArrangement = Arrangement.spacedBy(12.dp),
//        contentPadding = PaddingValues(8.dp),
//        content = {
//            itemsIndexed(queries) { index, itemData ->
//                Row {
//                    Text(text = itemData.name)
//                    Text(text = itemData.attribute)
//                }
//
//            }
//        }
//    )
//}

//@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview(name = "Welcome dark theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun LEDOrderScreenPreview() {
//    MaterialTheme {
//        LEDOrderScreen(
//            onNavigate = {},
//        )
//    }
//}
