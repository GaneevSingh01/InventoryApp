/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.dublinmug_kmm.android.navigators

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mongodb.dublinmug_kmm.android.views.BaseContentPresenter
import com.mongodb.dublinmug_kmm.android.views.DashboardScreen
import com.mongodb.dublinmug_kmm.android.views.LoginScreen
import com.mongodb.dublinmug_kmm.models.MainViewModel

object Destinations {
    const val LOGIN_ROUTE = "login"
    const val DASHBOARD_ROUTE = "dashboard"
    const val LED_ROUTE = "led"
    const val CASETIFY_ROUTE = "casetify"
}

enum class DrawerMenuOptions{
    HOME,
    CASE,
    LED
}

@Composable
fun MainNavigator(
    navController: NavHostController = rememberNavController(),
) {
    val mainViewModel = MainViewModel()

    NavHost(
        navController = navController,
        startDestination = Destinations.LOGIN_ROUTE,
    ) {
        composable(Destinations.LOGIN_ROUTE) {
            LoginScreen(
                onSignIn = {
                    navController.navigate(Destinations.DASHBOARD_ROUTE)
                },
                mainViewModel = mainViewModel
            )
        }

        composable(Destinations.DASHBOARD_ROUTE) {
            DashboardScreen(
                onNavigate = { drawerAction ->
                    navController.navigate(getNavigationFromMenu(drawerAction))
                }
            )
        }

        composable(Destinations.LED_ROUTE) {
            LEDScreenNavigator(
                onNavigate = { drawerAction ->
                    navController.navigate(getNavigationFromMenu(drawerAction))
                },
                mainViewModel = mainViewModel
            )
        }

        composable(Destinations.CASETIFY_ROUTE) {
            BaseContentPresenter(
                content = { Text(text = "CASE") },
                onDrawerButtonPress = { drawerAction ->
                    navController.navigate(getNavigationFromMenu(drawerAction))
                }
            )
        }
    }
}

fun getNavigationFromMenu(drawerActions: DrawerMenuOptions) : String = when (drawerActions) {
    DrawerMenuOptions.HOME -> Destinations.DASHBOARD_ROUTE
    DrawerMenuOptions.LED -> Destinations.LED_ROUTE
    DrawerMenuOptions.CASE -> Destinations.CASETIFY_ROUTE
}
