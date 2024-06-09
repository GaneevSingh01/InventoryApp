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

package com.mongodb.dublinmug_kmm.android.views

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mongodb.dublinmug_kmm.Utils.LoginResponse
import com.mongodb.dublinmug_kmm.android.MainViewModel

@Composable
fun WelcomeScreen(
    onSignIn: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth()
    ) { innerPadding ->

        var userName by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }

        var loginStatus by rememberSaveable { mutableStateOf(LoginResponse.NoState) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(
                text = "Login",
                fontSize = 24.sp
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Username: $userName")
                TextField(value = userName, onValueChange = {userName = it})
                Text(text = "Password: $password")
                TextField(value = password, onValueChange = {password = it})

                if(loginStatus != LoginResponse.NoState){
                    Text(text = "Login Status: $loginStatus", color = Color.Red)
                }
            }

            Button(
                onClick = { loginStatus = login(userName, password) }
            ) {
                Text(text = "Log In")
            }
        }
    }
}

fun login(userName: String, password: String): LoginResponse {
    return MainViewModel().login(userName, password)
}

@Preview(name = "Welcome light theme", uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Welcome dark theme", uiMode = UI_MODE_NIGHT_NO)
@Composable
fun WelcomeScreenPreview() {
    MaterialTheme {
        WelcomeScreen(
            onSignIn = {}
        )
    }
}
