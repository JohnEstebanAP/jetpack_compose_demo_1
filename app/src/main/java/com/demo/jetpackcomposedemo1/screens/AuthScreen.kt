package com.demo.jetpackcomposedemo1.screens

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.demo.jetpackcomposedemo1.navigation.AppScreens


@Composable
fun AuthScreen(
    navController: NavController,
    fragmentActivity: FragmentActivity,
    context: Context
) {

    setupAuth(context)

    var auth by remember {
        //asignamos el balor inicial de la bariable mutable
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .background(
                if (auth) Color.Green else Color.Cyan
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            if (auth) "Estas Autenticado :)" else "Necesitas autenticarte",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (auth) {
                auth = false
            } else {
                authenticate(canAuthenticate, promptInfo, fragmentActivity, context) { isauth ->
                    auth = isauth;
                    if (auth) navController.navigate(route = AppScreens.FirstScreen.route)
                }
            }
        }) {
            Text(if (auth) "Cerra" else "Autenticar")
        }
    }
}


// private methods
private var canAuthenticate = false
private lateinit var promptInfo: BiometricPrompt.PromptInfo

private fun setupAuth(context: Context) {
    if (
        BiometricManager.from(context).canAuthenticate(
            BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK or BiometricManager.Authenticators.DEVICE_CREDENTIAL
        ) == BiometricManager.BIOMETRIC_SUCCESS
    ) {
        canAuthenticate = true
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticacion biometrica")
            .setSubtitle("Autenticate utilizando el sensor biometrico")
            .setAllowedAuthenticators(
                BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK or BiometricManager.Authenticators.DEVICE_CREDENTIAL
            ).build()
    }
}

fun authenticate(
    canAuthenticate: Boolean,
    promptInfo: BiometricPrompt.PromptInfo,
    fragmentActivity: FragmentActivity,
    context: Context,
    auth: (auth: Boolean) -> Unit
) {
    if (canAuthenticate) {
        BiometricPrompt(fragmentActivity, ContextCompat.getMainExecutor(context),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    auth(true)
                }
            }).authenticate(promptInfo)
    } else {
        auth(false)
    }
}
