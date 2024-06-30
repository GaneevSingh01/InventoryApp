package com.mongodb.dublinmug_kmm.android.navigators

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mongodb.dublinmug_kmm.android.models.ProductModel
import com.mongodb.dublinmug_kmm.android.views.led_screeens.LEDHomeScreen
import com.mongodb.dublinmug_kmm.android.views.led_screeens.LEDOrderScreen
import com.mongodb.dublinmug_kmm.android.views.led_screeens.LEDProductScreen
import com.mongodb.dublinmug_kmm.android.views.led_screeens.LEDStockScreen
import com.mongodb.dublinmug_kmm.models.MainViewModel

object LEDScreenDestinations {
    const val HOME_ROUTE = "home"
    const val STOCK_ROUTE = "Stock"
    const val ORDERS_ROUTE = "Orders"
    const val PRODUCTS_ROUTE = "Products"
}

enum class LEDMenuOptions{
    STOCK,
    ORDERS,
    PRODUCTS,
}
@Composable
fun LEDScreenNavigator(
    onNavigate: (DrawerMenuOptions) -> Unit,
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel
){
    NavHost(
        navController = navController,
        startDestination = LEDScreenDestinations.HOME_ROUTE,
    ) {
        composable(LEDScreenDestinations.HOME_ROUTE) {
            LEDHomeScreen(
                onMenuOptionClick = { menuOption ->
                    navController.navigate(getNavigationFromMenu(menuOption))
                },
                onNavigate = onNavigate
            )
        }

        composable(LEDScreenDestinations.ORDERS_ROUTE) {
            LEDOrderScreen(
                onMenuOptionClick = { menuOption ->
                    navController.navigate(getNavigationFromMenu(menuOption))
                },
                onNavigate = onNavigate,
            )
        }

        composable(LEDScreenDestinations.STOCK_ROUTE) {
            LEDStockScreen(
                onMenuOptionClick = { menuOption ->
                    navController.navigate(getNavigationFromMenu(menuOption))
                },
                onNavigate = onNavigate
            )
        }

        composable(LEDScreenDestinations.PRODUCTS_ROUTE) {
            LEDProductScreen(
                onMenuOptionClick = { menuOption ->
                    navController.navigate(getNavigationFromMenu(menuOption))
                },
                onNavigate = onNavigate,
                productModel = ProductModel(mainViewModel)
            )
        }
    }
}

fun getNavigationFromMenu(menuOption: LEDMenuOptions) : String = when (menuOption) {
    LEDMenuOptions.STOCK -> LEDScreenDestinations.STOCK_ROUTE
    LEDMenuOptions.ORDERS -> LEDScreenDestinations.ORDERS_ROUTE
    LEDMenuOptions.PRODUCTS -> LEDScreenDestinations.PRODUCTS_ROUTE
}