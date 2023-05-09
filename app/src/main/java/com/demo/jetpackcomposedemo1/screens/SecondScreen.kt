package com.demo.jetpackcomposedemo1.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.demo.jetpackcomposedemo1.R
import com.demo.jetpackcomposedemo1.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun SecondsScreen(navNavigation: NavController = rememberNavController(), text: String? = "Hola") {
    Scaffold(topBar = {
        SmallTopAppBar(title = {
            Text(
                "Centered TopAppBar", maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }, navigationIcon = {
            IconButton(onClick = {
                navNavigation.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = "onBack"
                )
            }
        }, actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Localized description"
                )
            }
        })
    }, content = { innerPadding ->
        BodyContent2(navNavigation, innerPadding, text)
    })
}

@Composable
fun BodyContent2(navController: NavController, innerPadding: PaddingValues, text: String?) {

    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }

    val borderWidth = 4.dp
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(top = innerPadding.calculateTopPadding())
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(
                        borderWidth, rainbowColorsBrush
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo_sofka),
                contentDescription = "icon",
                modifier = Modifier
                    .rotate(25.0f)
                    .alpha(0.1f)
                    .align(Alignment.Center),
                tint = Color.DarkGray
            )
            Image(
                painter = painterResource(id = R.drawable.logo_sofka),
                contentDescription = "logo sofka",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(200.dp),
            )
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Icono de play",
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.BottomCenter),
                tint = Color.Black
            )
        }

        val (getText, setText) = rememberSaveable { mutableStateOf("") }
        StateSample(text = getText, onValueChangle = setText)

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


@Composable
fun StateSample(text: String, onValueChangle: (String) -> Unit) {
    //nos permite recordar el estado incluso conda cambiamos la orientacion del dispositivo.
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(0.dp, 10.dp)
            .padding(64.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        TextField(
            value = text,
            onValueChange = { onValueChangle(it) },
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = text, modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .padding(8.dp)
        )
        Button(
            onClick = { onValueChangle("") },
            modifier = Modifier.fillMaxWidth(),
            enabled = text.isNotEmpty()
        ) {
            Text(text = "Clear")
        }
    }
}
