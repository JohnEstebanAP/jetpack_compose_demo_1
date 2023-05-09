package com.demo.jetpackcomposedemo1

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.demo.jetpackcomposedemo1.navigation.AppNavigation
import com.demo.jetpackcomposedemo1.screens.AuthScreen
import com.demo.jetpackcomposedemo1.ui.theme.JetpackComposeDemo1Theme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemo1Theme() {
                AppNavigation(this, this)
            }
        }
    }
}