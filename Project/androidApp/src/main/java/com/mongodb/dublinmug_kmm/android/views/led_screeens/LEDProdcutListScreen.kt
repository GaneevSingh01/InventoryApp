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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mongodb.dublinmug_kmm.android.models.ProductModel
import com.mongodb.dublinmug_kmm.android.views.SubPageButtons
import com.mongodb.dublinmug_kmm.android.views.SubPageContentPresenter
import com.mongodb.dublinmug_kmm.models.MainViewModel
import com.mongodb.dublinmug_kmm.models.ProductDataModel

@Composable
fun LEDProductScreen(
    onBackPressed: () -> Unit,
    onAddButtonClicked: () -> Unit,
    onProductClicked: (productId: String) -> Unit,
    productModel: ProductModel
) {
    SubPageContentPresenter(
        pageName = "LED Products",
        content = {
            OrderProductContent(
                onProductClicked = onProductClicked,
                productModel
            )
        },
        activeButtons = listOf(SubPageButtons.ADD),
        onAddButtonClicked = onAddButtonClicked,
        onBackButtonPress = onBackPressed
    )
}

@Composable
fun OrderProductContent(
    onProductClicked: (productId: String) -> Unit,
    productModel: ProductModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        val productList = productModel.getItemDataLive().observeAsState(initial = emptyList()).value

        ProductsList(
            productList = productList,
            onProductClicked = onProductClicked
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductsList(
    productList: List<ProductDataModel>,
    onProductClicked: (productId: String) -> Unit
) {
    val modifier = Modifier.padding(10.dp)
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
            itemsIndexed(productList) { index, product ->
                Surface(
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                ) {
                    Product(
                        product = product,
                        modifier,
                        onProductClicked = onProductClicked
                    )
                }
            }
        }
    )
}

@Composable
fun Product(
    product: ProductDataModel,
    modifier: Modifier,
    onProductClicked: (productId: String) -> Unit,
){
    Button(
        onClick = { onProductClicked(product._id) },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        )
    ) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.name,
                    modifier = modifier,
                )
                Text(
                    text = product.attribute,
                    modifier = modifier
                )
            }
        }
    }
}

@Preview(name = "Product Preview")
@Composable
fun ProductPreview(){
    val product = ProductDataModel()
    product.apply {
        name = "Name"
        attribute = "Attribute"
    }

    MaterialTheme{
        Surface {
            Product(product = product, modifier = Modifier, {})
        }
    }
}
@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Welcome dark theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LEDProductScreenPreview() {
    MaterialTheme {
        LEDProductScreen(
            {},
            {},
            {},
            ProductModel(MainViewModel())
        )
    }
}
