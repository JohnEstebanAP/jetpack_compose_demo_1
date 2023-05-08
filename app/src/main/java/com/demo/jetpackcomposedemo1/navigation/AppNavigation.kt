package com.demo.jetpackcomposedemo1.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.demo.jetpackcomposedemo1.MainActivity
import com.demo.jetpackcomposedemo1.screens.AuthScreen
import com.demo.jetpackcomposedemo1.screens.FirstScreen
import com.demo.jetpackcomposedemo1.screens.SecondsScreen
import com.demo.jetpackcomposedemo1.screens.SplashScreen

@Composable
fun AppNavigation(
    fragmentActivity: FragmentActivity,
    context: Context
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(route = AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = AppScreens.AuthScreen.route) {
            AuthScreen(
                navController,
                fragmentActivity,
                context
            )
        }
        composable(route = AppScreens.FirstScreen.route) { FirstScreen(navController) }
        composable(
            route = AppScreens.SecondScreen.route + "/{text}",
            arguments = listOf(navArgument(name = "text") {
                type = NavType.StringType
            })
        ) {
            SecondsScreen(navController, it.arguments?.getString("text"))
        }
    }
}