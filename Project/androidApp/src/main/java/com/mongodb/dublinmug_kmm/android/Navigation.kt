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

package com.mongodb.dublinmug_kmm.android

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mongodb.dublinmug_kmm.android.views.BaseContentPresenter
import com.mongodb.dublinmug_kmm.android.views.DashboardScreen
import com.mongodb.dublinmug_kmm.android.views.LoginScreen

object Destinations {
    const val LOGIN_ROUTE = "login"
    const val DASHBOARD_ROUTE = "dashboard"
    const val LED_ROUTE = "led"
    const val CASETIFY_ROUTE = "casetify"
}

enum class DrawerActions{
    HOME,
    CASE,
    LED
}

@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.LOGIN_ROUTE,
    ) {
        composable(Destinations.LOGIN_ROUTE) {
            LoginScreen(
                onSignIn = {
                    navController.navigate(Destinations.DASHBOARD_ROUTE)
                }
            )
        }

        composable(Destinations.DASHBOARD_ROUTE) {
            DashboardScreen(
                onNavigate = {drawerAction ->
                    navController.navigate(getNavigationFromDrawer(drawerAction))
                }
            )
        }

        composable(Destinations.LED_ROUTE) {
            BaseContentPresenter(
                content = { Text(text = "LED") },
                onDrawerButtonPress = { drawerAction -> navController.navigate(getNavigationFromDrawer(drawerAction))}
            )
        }

        composable(Destinations.CASETIFY_ROUTE) {
            BaseContentPresenter(
                content = { Text(text = "CASE") },
                onDrawerButtonPress = { drawerAction -> navController.navigate(getNavigationFromDrawer(drawerAction))}
            )
        }
    }
}

fun getNavigationFromDrawer(drawerActions: DrawerActions) : String = when (drawerActions) {
    DrawerActions.HOME -> Destinations.DASHBOARD_ROUTE
    DrawerActions.LED -> Destinations.LED_ROUTE
    DrawerActions.CASE -> Destinations.CASETIFY_ROUTE
}
