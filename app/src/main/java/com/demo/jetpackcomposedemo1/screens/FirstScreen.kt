@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.demo.jetpackcomposedemo1.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.demo.jetpackcomposedemo1.navigation.AppScreens
import com.demo.jetpackcomposedemo1.ui.theme.JetpackComposeDemo1Theme

private val messages: List<MyMessage> = listOf(
    MyMessage("1 Hola Mundo", "Estas Listo?"),
    MyMessage("2 Holo John", ":)"),
    MyMessage("3 todo estara bien", "Claro que si"),
    MyMessage("4 Esta es una prueba increible", "siempre lo sera"),
    MyMessage(
        "5 probando texto largo eoeoeoeoeoeoeoeoeoeoeoeoeo",
        "eoeodthdthdthdthdt hdthdhtdthd thdthdthdt dfsdfsdfseo eoeoe eoeoe eoe oeoeoe eoeoeeieuia IEOUAO .YDEYFT APIOEUEUOI  iuoeuieuo uidoeuieuo iueioeui oe eoeoe hsdfdsf sdfdsfsdfs dfsdfsdfsdf sdfdsfdsf sdfsd fsdfsdfsdfds fdsgh dfhdthdt hdthdthd thdthdthdthdthdd"
    ),
    MyMessage(
        "6 probando texto largo eoeoeo eoeoeoeo",
        "eoeod thddasdasdasdthdthdt hdthdthd htdt hdthdth ouieuoieuo ioeuieoui euoioeu oeuheuid oa eao aeudui ieuduiedeui euid euiduo euid uied dthdthdt hdt heoeoe eoeoeo eoeoeoeoe eoeoeoeoe dthdthd thdthdthdthdthdd"
    ),
    MyMessage(
        "7 probando texto largo ",
        "eoeodt hdth dsds dsds gfdgdf dasdasd gdfgdfg asdeuideu euid euideui euideuid euidieu edui euide euoioeuioe oeuioeui asdasd dtdsd hdsd dthdthdthdht dthdthdthdthdthdth dthdthdthdthdthdthdthdthdd"
    ),
    MyMessage("8 Hola Mundo", "Estas Listo?"),
    MyMessage("9 Holo John", ":)"),
    MyMessage("10 todo estara bien", "Claro que si"),
    MyMessage("11 Esta es una prueba increible", "siempre lo sera"),
)

@Composable
fun FirstScreen(navController: NavController) {
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
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
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
            BodyContent(navController, innerPadding)
        }
    )
}

//con la anotacion previem podemos visualizar el elmento grafico en la preview

@Composable
fun BodyContent(navController: NavController, innerpadding: PaddingValues) {
    JetpackComposeDemo1Theme {
        //val scrollState = rememberScrollState()
        //Column(modifier = Modifier.verticalScroll(scrollState)) {MyMessages() }
        Column(modifier = Modifier.padding(top = innerpadding.calculateTopPadding())) {

            Text("Hi navigation")
            Button(onClick = {
                Log.d("boton", "haciendo click en el moton 1")
                navController.navigate(route = AppScreens.SecondScreen.route + "/ ")
            }) {
                Text(text = "Navegar a la pagina 2")
            }
            MyMessages()

        }

    }

}

@Composable
fun MyMessage(messages: List<MyMessage>) {
    LazyColumn {
        items(messages) { message ->
            MyComponent(message)
        }
    }

}


@Composable
fun MyComponent(message: MyMessage) {
    //para cambiar de tamanio color etc se utilizan modificadores.
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        MyImage()
        MyTexts(message)
    }
}

@Composable
fun MyImage() {
    Icon(
        Icons.Sharp.Menu,
        contentDescription = "Mi imagen de prueba",
        modifier = Modifier
            .clip(shape = CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            //.size(height = 40.dp, width = 40.dp)
            .size(64.dp)
    )
}


data class MyMessage(val title: String, val body: String)

//con la anotacion composable se indica que esto es un elmento grafico
@Composable
//crear un elemento cmoposeable
fun MyTexts(message: MyMessage) {

    //sintacis para almacenar y modifucar valores en tiempo de ejecucion dentro de interfaces bisuales de jetpack compose
    //nos permite desencadenar un repintodo en la interfas grafica.
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .padding(start = 8.dp)
        .clickable {
            expanded = !expanded
        }) {
        MyText(
            text = message.title,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium
        )
        MyText(
            text = message.body,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelSmall,
            lines = if (expanded) Int.MAX_VALUE else 1

        )
    }
}

@Composable
fun MyText(text: String, color: Color, style: TextStyle, lines: Int = Int.MAX_VALUE) {
    Text(text, color = color, style = style, maxLines = lines)
}


@Composable
fun MyMessages() {
    MyMessage(messages = messages)
}