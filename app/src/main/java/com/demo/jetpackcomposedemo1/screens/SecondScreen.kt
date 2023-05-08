package com.demo.jetpackcomposedemo1.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.demo.jetpackcomposedemo1.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondsScreen(navNavigation: NavController, text: String?) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        "Centered TopAppBar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navNavigation.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "onBack"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            BodyContent2(navNavigation, innerPadding, text)
        }
    )
}

@Composable
fun BodyContent2(navController: NavController, innerPadding: PaddingValues, text: String?) {
    Column(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())) {
        Text("Hi navigation")
        text?.let {
            Text(it)
        }
        Button(onClick = {
            navController.navigate(route = AppScreens.FirstScreen.route)
        }) {
            Text(text = "Navega 1")
        }
    }
}
