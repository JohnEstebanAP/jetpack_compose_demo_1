package com.demo.jetpackcomposedemo1.screens

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.demo.jetpackcomposedemo1.R
import com.demo.jetpackcomposedemo1.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(key1 = true) {
        //simulacion de tareas que se ehecutaran en segundo plano mientra se esta acrgando la app
        delay(3000)
        //eliminamos la pantalla de splashScreen regresando atras y sacandola de la pila de pantallas.
        navController.popBackStack()
        //abrimos nuestro login
        navController.navigate(AppScreens.AuthScreen.route)
    }
    Splash()
}

@Preview(showSystemUi = true)
@Composable
fun Splash() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_sofka),
            contentDescription = "Logo sofka"
        )
    }
}