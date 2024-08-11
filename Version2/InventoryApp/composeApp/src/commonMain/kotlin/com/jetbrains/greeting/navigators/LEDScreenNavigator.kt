package com.jetbrains.greeting.navigators

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetbrains.greeting.models.MainViewModel
import com.jetbrains.greeting.models.ProductModel
import com.jetbrains.greeting.views.led_screeens.AddProductScreen
import com.jetbrains.greeting.views.led_screeens.LEDHomeScreen
import com.jetbrains.greeting.views.led_screeens.LEDOrderScreen
import com.jetbrains.greeting.views.led_screeens.LEDProductScreen
import com.jetbrains.greeting.views.led_screeens.LEDStockScreen
import com.jetbrains.greeting.views.led_screeens.ViewProductScreen

object LEDScreenDestinations {
    const val HOME_ROUTE = "home"
    const val STOCK_ROUTE = "stock"
    const val ORDERS_ROUTE = "orders"
    const val PRODUCTS_ROUTE = "products"
    const val ADD_PRODUCT_ROUTE = "products/add"
    const val VIEW_PRODUCT_ROUTE = "products/view/{product}"
}

enum class LEDMenuOptions{
    STOCK,
    ORDERS,
    PRODUCTS,
}

enum class LEDSubPages {
    PRODUCTS_ADD,
    PRODUCTS_VIEW,
}

@Composable
fun LEDScreenNavigator(
    onDrawerNavigate: (DrawerMenuOptions) -> Unit,
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel
){
    val productModel = ProductModel(mainViewModel)
    NavHost(
        navController = navController,
        startDestination = LEDScreenDestinations.HOME_ROUTE,
    ) {
        composable(LEDScreenDestinations.HOME_ROUTE) {
            LEDHomeScreen(
                onMenuOptionClick = { menuOption ->
                    navController.navigate(
                        getNavigationFromMenu(
                            menuOption
                        )
                    )
                },
                onNavigate = onDrawerNavigate
            )
        }

        composable(LEDScreenDestinations.ORDERS_ROUTE) {
            LEDOrderScreen(
                onNavigate = onDrawerNavigate,
            )
        }

        composable(LEDScreenDestinations.STOCK_ROUTE) {
            LEDStockScreen(
                onNavigate = onDrawerNavigate
            )
        }

        composable(LEDScreenDestinations.PRODUCTS_ROUTE) {
            LEDProductScreen(
                onBackPressed = {
                    navController.popBackStack()
                },
                onAddButtonClicked = {
                    navController.navigate(getNavigationToSubPage(LEDSubPages.PRODUCTS_ADD))
                },
                onProductClicked = {product ->
                    navController.navigate(getNavigationToSubPage(LEDSubPages.PRODUCTS_VIEW, product))
                },
                productModel = productModel
            )
        }

        composable(LEDScreenDestinations.ADD_PRODUCT_ROUTE) {
            AddProductScreen(
                onBackButtonClicked = { navController.popBackStack() },
                productModel = productModel
            )
        }

        composable(LEDScreenDestinations.VIEW_PRODUCT_ROUTE) {
            ViewProductScreen(
                productId = it.arguments?.getString("product") ?: "**Empty**",
                onBackButtonClicked = { navController.popBackStack() },
                productModel = productModel
            )
        }
    }
}

fun getNavigationFromMenu(menuOption: LEDMenuOptions) : String = when (menuOption) {
    LEDMenuOptions.STOCK -> LEDScreenDestinations.STOCK_ROUTE
    LEDMenuOptions.ORDERS -> LEDScreenDestinations.ORDERS_ROUTE
    LEDMenuOptions.PRODUCTS -> LEDScreenDestinations.PRODUCTS_ROUTE
}

fun getNavigationToSubPage(subPages: LEDSubPages, argument: String = "") : String = when (subPages) {
    LEDSubPages.PRODUCTS_ADD -> LEDScreenDestinations.ADD_PRODUCT_ROUTE
    LEDSubPages.PRODUCTS_VIEW -> LEDScreenDestinations.VIEW_PRODUCT_ROUTE.removeSuffix("{product}").plus(argument)
}