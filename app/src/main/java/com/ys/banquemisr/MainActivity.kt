package com.ys.banquemisr

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.ys.banquemisr.ui.theme.Cherry
import com.ys.banquemisr.ui.theme.Gray
import com.ys.banquemisr.ui.theme.LightCherry

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LogIn()
        }
    }
}

@Composable
fun LogIn(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var usernameField by remember { mutableStateOf("") }
    var passwordField by remember { mutableStateOf("") }

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
            TextButton(onClick = {
                changeLocales(context)
            }) {
                Text(
                    text = stringResource(R.string.language),
                    fontWeight = FontWeight.Bold,
                    color = Cherry,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.change, FontWeight.Normal))
                )
            }
        }

        OutlinedTextField(
            value = usernameField,
            onValueChange = { usernameField = it },
            label = {
                Text(
                    text = stringResource(id = R.string.username),
                    color = Color.Gray
                )
            },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 36.dp, bottom = 16.dp)
                .fillMaxWidth()
                .height(64.dp),
            textStyle = TextStyle(Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray, unfocusedBorderColor = Color.LightGray
            )
        )
        var passwordVisible by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = passwordField,
            onValueChange = { passwordField = it },
            label = {
                Text(
                    text = stringResource(R.string.password),
                    color = Color.Gray
                )
            },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(64.dp),
            textStyle = TextStyle(Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray, unfocusedBorderColor = Color.LightGray
            ),
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }) {
                    Image(
                        painter = painterResource(id = if (passwordVisible) R.drawable.visible else R.drawable.not_visible),
                        contentDescription = "password_visibility",
                        modifier = Modifier.size(32.dp)
                    )
                }
            },
            visualTransformation =
            if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 13.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                text = stringResource(R.string.forgot_password_username),
                textDecoration = TextDecoration.Underline,
                fontSize = 12.sp,
                color = Gray
            )
        }
        Button(
            onClick = { },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp)
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = if (usernameField.isNotBlank() && passwordField.isNotBlank()) Cherry else LightCherry,
            )

        ) {
            Text(
                text = stringResource(R.string.log_in),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.change, FontWeight.Normal))
            )
        }
        SuggestionHyperlinks()
        HorizontalDivider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
        FeaturesAndIcons()
    }
}

@Composable
fun SuggestionHyperlinks() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = stringResource(R.string.schedule_reschedule), fontSize = 12.sp, color = Gray
        )
        Text(
            text = stringResource(R.string.branch_visit),
            textDecoration = TextDecoration.Underline,
            fontSize = 12.sp,
            color = Cherry,
            fontWeight = FontWeight.Bold
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp, bottom = 32.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        Text(
            text = stringResource(R.string.new_to_the_app), fontSize = 12.sp, color = Gray
        )
        Text(
            text = stringResource(R.string.register),
            textDecoration = TextDecoration.Underline,
            fontSize = 12.sp,
            color = Cherry,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun FeaturesAndIcons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AppFeature(feature = R.string.loan_calculator, icon = R.drawable.loan_calc)
        AppFeature(feature = R.string.our_products, R.drawable.our_products)
        AppFeature(feature = R.string.branch_atm, icon = R.drawable.nearest_branch_or_atm)
        AppFeature(feature = R.string.exchange_rates, icon = R.drawable.exchange_rate)
    }
}

@Composable
fun AppFeature(@StringRes feature: Int, @DrawableRes icon: Int) {
    Column{
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(56.dp)
        )
        Text(
            text = stringResource(feature),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 12.sp,
            lineHeight = 14.sp
        )
    }
}

fun changeLocales(context: Context) {
    val locale = context.resources.configuration.locales[0]
    val localeString = if (locale.language == "ar") "en" else "ar"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val localeManager = context.getSystemService(LocaleManager::class.java)
        localeManager.applicationLocales = LocaleList.forLanguageTags(localeString)
    } else {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(localeString))
    }
}


@Preview(showBackground = true)
@Composable
fun LogInPreview() {
    LogIn()
}




