package com.ys.banquemisr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ys.banquemisr.ui.theme.BanqueMisrTheme
import com.ys.banquemisr.ui.theme.Cherry

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BanqueMisrTheme {
                LogIn()
            }
        }
    }
}

@Composable
fun LogIn(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 64.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.bm_icon),
                contentDescription = stringResource(R.string.banque_misr_icon)
            )
            Text(
                text = stringResource(R.string.ar_translation),
                fontWeight = FontWeight.Bold,
                color = Cherry,
                fontSize = 16.sp,
                // fontFamily = FontFamily(Font(R.font.change, FontWeight.Normal))
            )
        }
        var usernameField by remember { mutableStateOf("Username") }
        var passwordField by remember { mutableStateOf("Password") }
        var showUsernameLabel by remember { mutableStateOf(false) }
        var showPasswordLabel by remember { mutableStateOf(false) }
        var isFocusedUser by remember { mutableStateOf(false) }
        var isFocusedPass by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = usernameField,
            onValueChange = { usernameField = it },
            label = {
                if (showUsernameLabel)
                    Text(
                        text = "Username",
                        color = Color.DarkGray
                    )
            },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp, bottom = 16.dp)
                .fillMaxWidth()
                .height(64.dp)
                .onFocusChanged {
                    isFocusedUser = it.isFocused && !isFocusedUser
                    if (isFocusedUser) {
                        showUsernameLabel = true
                        if (usernameField == "Username")
                            usernameField = ""
                    }
                },
            textStyle =
            if (usernameField == "Username") TextStyle(Color.LightGray, fontSize = 16.sp)
            else TextStyle(Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray
            )
        )
        var passwordVisible by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = passwordField,
            onValueChange = { passwordField = it },
            label = {
                if (showPasswordLabel)
                    Text(
                        text = "Password",
                        color = Color.DarkGray
                    )
            },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .height(64.dp)
                .onFocusChanged {
                    isFocusedPass = it.isFocused && !isFocusedPass
                    if (isFocusedPass) {
                        showPasswordLabel = true
                        if (passwordField == "Password")
                            passwordField = ""
                    }
                },
            textStyle =
            if (passwordField == "Password") TextStyle(Color.LightGray, fontSize = 16.sp)
            else TextStyle(Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray
            )

        )

    }
}


@Preview(showBackground = true)
@Composable
fun LogInPreview() {
    LogIn()
}
