package com.jetbrains.greeting.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetbrains.greeting.navigators.DrawerMenuOptions

@Composable
fun MainDrawer(
    onDestinationSelect: (DrawerMenuOptions) -> Unit
) {
    val drawerActions = listOf(DrawerMenuOptions.HOME, DrawerMenuOptions.CASE, DrawerMenuOptions.LED)
    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(end = 50.dp)
    ){
        Column (
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            val modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth()

            Text(
                text = "Inventory Manager",
                modifier = modifier,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 35.sp),
                color = MaterialTheme.colors.onSurface,
            )

            for (action in drawerActions){
                Spacer(modifier = modifier.height(40.dp))

                Button(
                    modifier = modifier,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colors.onPrimary,
                        backgroundColor = MaterialTheme.colors.primary
                        ),
                    shape = RectangleShape,
                    onClick = {
                        onDestinationSelect(action)
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = action.name,
                        style = TextStyle(fontSize = 20.sp)
                    )
                }
            }
        }
    }

}

//@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview(name = "Welcome dark theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun DashboardTopBarPreview() {
//    MaterialTheme {
//        MainDrawer({})
//    }
//}