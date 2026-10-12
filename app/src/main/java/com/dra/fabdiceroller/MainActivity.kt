package com.dra.fabdiceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dra.fabdiceroller.ui.theme.FabdicerollerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FabdicerollerTheme {
                DiceRollerApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiceRollerApp() {

    var currentStep by remember { mutableStateOf(1) }

    val imageResource = when (currentStep) {
        1 -> R.drawable.ayambertelur
        2 -> R.drawable.kocoktelur
        3 -> R.drawable.gorengtelur
        else -> R.drawable.telursiapmakan
    }

    val descriptionText = when (currentStep) {
        1 -> "Ayam Bertelur"
        2 -> "Pecahkan telur ke mangkuk dan aduk"
        3 -> "Goreng telur"
        else -> "Telur siap dimakan"
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "cara masak telur",
                        fontWeight = FontWeight.Bold
                    )
                },

                )

        }
    ) { innerPadding ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.tertiaryContainer
        ) {
            DiceTextAndImage(
                textLabel = descriptionText,
                drawableResourceId = imageResource,
                contentDescription = descriptionText,
                onImageClick = {
                    currentStep =
                        if (currentStep < 4) currentStep + 1 else 1
                }
            )
        }
    }
}

@Composable
fun DiceTextAndImage(
    textLabel: String,
    drawableResourceId: Int,
    contentDescription: String,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC3ECD2)
                )
            ) {
                Image(
                    painter = painterResource(
                        id = drawableResourceId
                    ),
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = textLabel,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiceRollerPreview() {
    FabdicerollerTheme {
        DiceRollerApp()
    }
}