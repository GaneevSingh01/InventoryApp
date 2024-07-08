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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mongodb.dublinmug_kmm.Utils.LoginResponse
import com.mongodb.dublinmug_kmm.android.R
import com.mongodb.dublinmug_kmm.models.MainViewModel

class LoginScreenViewModel(val mainViewModel: MainViewModel) {
    fun login(userName: String, password: String): LoginResponse {
        if (userName.isEmpty() || password.isEmpty()) return LoginResponse.InvalidCredentials
        return mainViewModel.login(userName, password)
    }
}

@Composable
fun LoginScreen(
    onSignIn: () -> Unit,
    mainViewModel: MainViewModel
) {
    val loginScreenVM = LoginScreenViewModel(mainViewModel)

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) { innerPadding ->

        var userName by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }

        var loginButtonPressed by rememberSaveable { mutableStateOf(false) }

        var loginStatus by rememberSaveable { mutableStateOf(LoginResponse.NoState) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_header),
                contentDescription = ""
            )

            Text(
                text = "Inventory Manager",
                fontSize = 24.sp
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Username:",
                    modifier = Modifier.padding(10.dp)
                )
                TextField(
                    value = userName,
                    onValueChange = {userName = it},
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = "Password:",
                    modifier = Modifier.padding(10.dp)
                )
                TextField(
                    value = password,
                    onValueChange = {password = it},
                    modifier = Modifier.padding(10.dp)
                )

                if(loginStatus != LoginResponse.LoginSuccessful && loginButtonPressed){
                    Text(
                        text = "Login Status: ${loginStatus.displayMessage}",
                        color = Color.Red
                    )
                }
            }
            Button(
                onClick = {
                    loginButtonPressed = true
                    // TODO: REMOVE
                    if(userName == "" || password == "") {
                        userName = "ganeevsingh1@outlook.com"
                        password = "itguy2022"
                    }
                    
                    loginStatus = loginScreenVM.login(userName, password)
                    if (loginStatus == LoginResponse.LoginSuccessful) {
                        onSignIn()
                    }
                }
            ) {
                Text(text = "Log In")
            }
        }
    }
}

@Preview(name = "Welcome light theme", uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Welcome dark theme", uiMode = UI_MODE_NIGHT_NO)
@Composable
fun WelcomeScreenPreview() {
    MaterialTheme {
        LoginScreen({ }, MainViewModel())
    }
}
