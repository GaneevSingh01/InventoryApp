package com.mongodb.dublinmug_kmm.android.views.led_screeens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mongodb.dublinmug_kmm.android.models.ProductModel
import com.mongodb.dublinmug_kmm.android.navigators.DrawerMenuOptions
import com.mongodb.dublinmug_kmm.android.navigators.LEDMenuOptions
import com.mongodb.dublinmug_kmm.android.views.BaseContentPresenter
import com.mongodb.dublinmug_kmm.models.MainViewModel
import com.mongodb.dublinmug_kmm.models.ProductDataModel
import kotlinx.coroutines.runBlocking

@Composable
fun LEDProductScreen(
    onNavigate: (DrawerMenuOptions) -> Unit,
    onMenuOptionClick: (LEDMenuOptions) -> Unit,
    productModel: ProductModel
) {
    BaseContentPresenter(
        content = { OrderProductContent(onMenuOptionClick, productModel) },
        onDrawerButtonPress = { drawerAction ->
            onNavigate(drawerAction)
        }
    )
}

@Composable
fun OrderProductContent(
    onMenuOptionClick: (LEDMenuOptions) -> Unit,
    productModel: ProductModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        AddProduct(productModel)
        ProductsList(productModel)
    }
}

@Composable
fun AddProduct(
    productModel: ProductModel
){
    Surface(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
        modifier = Modifier.fillMaxWidth()
    ) {
        val modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
        var name by rememberSaveable { mutableStateOf("") }
        var details by rememberSaveable { mutableStateOf("") }

        Column (
            modifier = modifier.padding(10.dp)
        ){
            Text(
                text = "Add New Product",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Item name:",
                textAlign = TextAlign.Left,
                modifier = modifier
            )

            TextField(
                value = name,
                placeholder = {Text("Enter Product Name")},
                onValueChange = {name = it},
                modifier = modifier
            )

            Text(
                text = "Item details:",
                textAlign = TextAlign.Left,
                modifier = modifier
            )

            TextField(
                value = details,
                placeholder = {Text("Enter Product Details")},
                onValueChange = {details = it},
                modifier = modifier
            )

            Button(
                onClick = { runBlocking { productModel.saveNewProduct(name, details) } },
                modifier = modifier
            ) {
                Text(text = "Send to DB")
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductsList(
    productModel: ProductModel,
//    onEditClick: (QueryInfo) -> Unit
) {

    val queries = productModel.getItemDataLive().observeAsState(initial = emptyList()).value
    
    val modifier = Modifier.padding(10.dp)
    Text(
        text = "Existing Products",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier

    )
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(8.dp),
        content = {
            stickyHeader {
                Surface(
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Text(text = "Product Name")
                        Text(text = "Product Details")
                    }
                }
            }
            itemsIndexed(queries) { index, product ->
                Surface(
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                ) {
                    Product(product = product, modifier)
                }
            }
        }
    )
}

@Composable
fun Product(
    product: ProductDataModel,
    modifier: Modifier
){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = product.name, modifier = modifier)
        Text(text = product.attribute, modifier = modifier)
    }
}
@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Welcome dark theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LEDProductScreenPreview() {
    MaterialTheme {
        LEDProductScreen(
            onNavigate = {},
            onMenuOptionClick = {},
            ProductModel(MainViewModel())
        )
    }
}
