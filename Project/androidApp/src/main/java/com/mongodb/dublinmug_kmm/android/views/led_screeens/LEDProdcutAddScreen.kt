package com.mongodb.dublinmug_kmm.android.views.led_screeens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mongodb.dublinmug_kmm.android.models.ProductModel
import com.mongodb.dublinmug_kmm.android.views.SubPageContentPresenter
import com.mongodb.dublinmug_kmm.models.MainViewModel

@Composable
fun AddProductScreen(
    onBackButtonClicked: () -> Unit,
    productModel: ProductModel
){
    SubPageContentPresenter(
        pageName = "Add Product",
        content = {
            AddProduct(
                addProduct = { name, detail -> productModel.saveNewProduct(name, detail)}
            )
        },
        onBackButtonPress = onBackButtonClicked
    )
}
@Composable
fun AddProduct(
    addProduct: (name: String, details: String) -> Unit
){
    Surface(
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
                placeholder = { Text("Enter Product Details") },
                onValueChange = {details = it},
                modifier = modifier
            )

            Button(
                onClick = {
                    addProduct(name, details)
                    name = ""
                    details = ""
                },
                modifier = modifier
            ) {
                Text(text = "Send to DB")
            }
        }
    }
}

@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Welcome dark theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ADDProductScreenPreview() {
    MaterialTheme {
        AddProductScreen({}, ProductModel(MainViewModel()))
    }
}
