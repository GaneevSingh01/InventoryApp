package com.mongodb.dublinmug_kmm.android.routes

import androidx.compose.runtime.Composable
import com.mongodb.dublinmug_kmm.android.views.WelcomeScreen

@Composable
fun WelcomeRoute(
    onNavigateToSignIn: () -> Unit,
//    onNavigateToSignUp: (email: String) -> Unit,
//    onSignInAsGuest: () -> Unit,
) {
    WelcomeScreen(
        onSignIn = onNavigateToSignIn
    )
}
