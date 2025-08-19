@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.hellojetpackcompose

import ads_mobile_sdk.h2
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.More
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellojetpackcompose.ui.theme.HelloJetpackComposeTheme

private val sampleName = listOf(
    "Andre",
    "Desta",
    "Parto",
    "Wendy",
    "Komeng",
    "Raffi Ahmad",
    "Andhika Pratama",
    "Vincent Ryan Rompies"
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloJetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        MyTopBar()
                        MainApp(sampleName)
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }

    val animatedSizeDp by animateDpAsState(
        targetValue = if (isExpanded) 120.dp else 80.dp, animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow
        )
    )


    Card(
        shape = MaterialTheme.shapes.medium, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ), modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.img_jetpack_compose),
                contentDescription = "Logo Jetpack Compose",
                modifier = Modifier.size(animatedSizeDp),
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Hello $name!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Welcome to Dicoding", style = MaterialTheme.typography.bodyLarge.copy(
                        fontStyle = FontStyle.Italic
                    )
                )
            }

            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Outlined.ExpandMore,
                    contentDescription = if (isExpanded) "Show Less" else "Show More"
                )
            }
        }
    }
}
//
//@Composable
//fun CountingButton(modifier: Modifier = Modifier) {
//    Column {
//        var number by remember {
//            mutableStateOf(0)
//        }
//
//        MyButton { number += 1 }
//        Text(
//            text = number.toString(),
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//            style = MaterialTheme.typography.h2,
//            fontStyle = FontStyle.Italic,
//        )
//
//        LazyRow {
//            item { Text("No:") }
//            item(number) {
//                Text("${it + 1}")
//            }
//        }
//    }
//
//}

@Composable
fun ContactCard() {
    Row(
        modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Avater",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )

            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Online Status",
                tint = Color.Green,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = "Dico Wisesa", fontWeight = FontWeight.Bold
            )
            Text("Online")
        }
    }

}


@Composable
fun MyButton(onButtonClicked: () -> Unit) { //higher-order function
    Button(onClick = onButtonClicked) { //lambda expression
        Text("Click Me")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        },

        title = {
            Text("App Bar ")
        },

        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite")
            }

            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Default.More, contentDescription = "Favorite")
            }

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
            ) {
                DropdownMenuItem(text = { Text("Call me") }, onClick = {})
            }

//            DropdownMenu(
//                expanded = showMenu, onDismissRequest = { showMenu = false }) {
//                DropdownMenuItem(onClick = {}) {
//                    Text(text = "Call me")
//                }
//            }

        })

}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    HelloJetpackComposeTheme {
//        Scaffold {
//            MyTopBar()
//            GreetingList(sampleName)
//        }
//    }
//}


@Preview(showBackground = true)
@Composable
fun ContactCardPreview() {
    HelloJetpackComposeTheme {
        ContactCard()
    }
}

@Composable
fun MainApp(sampleName: List<String>) {
    GreetingList(sampleName)
}

@Preview(showBackground = true, device = "id:pixel_5", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MainAppPreview() {
    HelloJetpackComposeTheme {
        Scaffold { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                MyTopBar()
                MainApp(sampleName)
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MainTopBarPreview() {
    HelloJetpackComposeTheme {
        MyTopBar()
    }
}


@Composable
fun GreetingList(names: List<String>) {
    if (names.isNotEmpty()) {
//        Column {
//            for (name in names) {
//                Greeting(name)
//            }
//        }

        LazyColumn {
            items(names) { name ->
                Greeting(name)
            }
        }

    } else {
        Text(text = "No People to greet :(")
    }
}



