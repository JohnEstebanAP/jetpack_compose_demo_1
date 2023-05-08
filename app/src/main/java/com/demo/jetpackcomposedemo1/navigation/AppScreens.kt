package com.demo.jetpackcomposedemo1.navigation

/**La clase sellada es una forma limitada y controlada de
 *  definir una jerarquía de tipos, y puede ser útil para
 *  modelar tipos de datos con un conjunto fijo de valores conocidos.
 *  En este caso, AppScreens se utiliza para representar
 *  diferentes pantallas de una aplicación.
 */
sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens("splash_screen")
    object AuthScreen : AppScreens("auth_screen")
    object FirstScreen : AppScreens("first_screen")
    object SecondScreen : AppScreens("second_screen")
}
