package com.mongodb.dublinmug_kmm.android.views.led_screeens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mongodb.dublinmug_kmm.android.models.ProductModel
import com.mongodb.dublinmug_kmm.android.views.SubPageButtons
import com.mongodb.dublinmug_kmm.android.views.SubPageContentPresenter
import com.mongodb.dublinmug_kmm.models.MainViewModel
import com.mongodb.dublinmug_kmm.models.ProductDataModel
import io.realm.kotlin.ext.copyFromRealm
import io.realm.kotlin.ext.realmDictionaryOf

@Composable
fun ViewProductScreen(
    productId: String,
    productDataModel: ProductDataModel? = null,
    onBackButtonClicked: () -> Unit,
    productModel: ProductModel
){
    val product = productDataModel ?: productModel.getProduct(productId)
    if (product == null) return

    val openAlertDialog = remember { mutableStateOf(false) }

    SubPageContentPresenter (
        pageName = "View Product",
        content = {
            ViewProduct(
                product = product,
                onUpdate = {println(it.name)}
            )
            if (openAlertDialog.value) {
                ConfirmDeleteProductDialog (
                    onConfirmDelete = {
                        productModel.deleteProduct(product)
                        openAlertDialog.value = false
                        onBackButtonClicked()
                    },
                    onCancel = { openAlertDialog.value = false }
                )
            }
        },
        activeButtons = listOf(SubPageButtons.DELETE),
        onBackButtonPress = onBackButtonClicked,
        onDeleteButtonClicked = { openAlertDialog.value = true }
    )
}

@Composable
fun ConfirmDeleteProductDialog(
    onConfirmDelete: () -> Unit,
    onCancel: () -> Unit
){
    AlertDialog(
        title = {
            Text(text = "Are you sure you want to delete?")
        },
        icon = {
               Icon(
                   imageVector = Icons.Rounded.Delete,
                   contentDescription = "Delete"
               )
        },
        onDismissRequest = {
            onCancel()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmDelete()
                }
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onCancel()
                }
            ) {
                Text("Cancel")
            }
        },
        tonalElevation = 10.dp
    )
}

@Composable
fun ViewProduct(
    product: ProductDataModel,
    onUpdate: (ProductDataModel) -> Unit
){
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        val modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
        var name by rememberSaveable { mutableStateOf(product.name) }
//        var details by rememberSaveable { mutableStateOf("product.attribute") }

        Column (
            modifier = modifier.padding(10.dp)
        ){
            Text (
                text = "Item name:",
                textAlign = TextAlign.Left,
                modifier = modifier
            )

            TextField (
                value = name,
                onValueChange = {name = it},
                modifier = modifier
            )

            Text (
                text = "Item details:",
                textAlign = TextAlign.Left,
                modifier = modifier
            )

            for(attribute in product.attributes){
                Row (
                    modifier = modifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    TextField (
                        value = attribute.key,
                        onValueChange = {},
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                    )
                    TextField (
                        value = attribute.value,
                        onValueChange = {},
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                    )
                }
            }

            Button(
                onClick = {
                    val productClone = product.copyFromRealm()
                    if (product.name != name) {
                        productClone.apply { this.name = name; /*attribute = details*/ }
                    }
                    onUpdate(productClone)
                },
                modifier = modifier
            ) {
                Text(text = "Update")
            }
        }
    }
}

@Preview
@Composable
fun ViewDeleteDialog(){
    MaterialTheme{
        ConfirmDeleteProductDialog(onConfirmDelete = { }) {}
    }

}
@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ViewProductScreenPreview() {
    MaterialTheme {
        ViewProductScreen(
            "Test Name",
            ProductDataModel().apply {
                name = "Name"
                attributes = realmDictionaryOf(
                    Pair("Length","1m"),
                    Pair("Color","Red")
                )
            },
            {},
            ProductModel(MainViewModel()))
    }
}
