package com.jetbrains.greeting.views.led_screeens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetbrains.greeting.models.ProductAttribute
import com.jetbrains.greeting.models.ProductModel
import com.jetbrains.greeting.views.SubPageContentPresenter

@Composable
fun AddProductScreen(
    onBackButtonClicked: () -> Unit,
    productModel: ProductModel
){
    SubPageContentPresenter(
        pageName = "Add Product",
        content = {
            AddProduct (
                addProduct = { name, details -> productModel.saveNewProduct(name, details)}
            )
        },
        onBackButtonPress = onBackButtonClicked
    )
}

@Composable
fun AddProduct(
    addProduct: (name: String, details: Map<String, String>) -> Unit
){
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        val modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
        var name by rememberSaveable { mutableStateOf("") }
        val attributes = remember { mutableStateListOf(ProductAttribute("", "")) }

        Column(
            modifier = modifier.padding(10.dp),
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
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Item details:",
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                )
                IconButton(
                    onClick = {
                        attributes.add(ProductAttribute("",""))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add Item Detail"
                    )
                }
            }

            attributes.forEachIndexed{ index, attribute ->
                ItemAttributeView(
                    attribute = attribute,
                    onAttributeChange = { attributes[index] = it },
                    onDeleteClicked = { attributes.removeAt(index) },
                    modifier = modifier
                )
            }

            Button(
                onClick = {
                    addProduct(name, ProductAttribute.toMap(attributes))
                    name = ""
                    attributes.clear()
                },
                modifier = modifier
            ) {
                Text(text = "Send to DB")
            }
        }
    }
}

@Composable
fun ItemAttributeView(
    attribute: ProductAttribute,
    onAttributeChange: (ProductAttribute) -> Unit,
    onDeleteClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField (
            value = attribute.name,
            onValueChange = { onAttributeChange(attribute.copy(name = it)) },
            modifier = Modifier.weight(1f)
        )
        Text( text = ":")
        TextField (
            value = attribute.value,
            onValueChange = { onAttributeChange(attribute.copy(value = it)) },
            modifier = Modifier.weight(1f)
        )
        IconButton (
            onClick = onDeleteClicked,
            modifier = Modifier.weight(0.15f)
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Attribute Delete",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

//@Preview()
//@Composable
//fun ItemAttributeViewPreview() {
//    MaterialTheme {
//        Surface {
//            ItemAttributeView(
//                attribute = ProductAttribute("", ""),
//                onAttributeChange = {},
//                onDeleteClicked = {}
//            )
//        }
//    }
//}
//
//
//@Preview()
//@Composable
//fun ADDProductScreenPreview() {
//    MaterialTheme {
//        AddProductScreen({}, ProductModel(MainViewModel()))
//    }
//}
