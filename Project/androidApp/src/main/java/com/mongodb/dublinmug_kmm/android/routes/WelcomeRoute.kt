package com.mongodb.dublinmug_kmm.android.routes

import androidx.compose.runtime.Composable
import com.mongodb.dublinmug_kmm.android.views.LoginScreen

@Composable
fun WelcomeRoute(
    onNavigateToSignIn: () -> Unit,
) {
    LoginScreen(
        onSignIn = onNavigateToSignIn
    )
}
