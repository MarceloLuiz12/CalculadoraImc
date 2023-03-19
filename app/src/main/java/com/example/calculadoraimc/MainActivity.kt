package com.example.calculadoraimc

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.calculation.CalculateIMC
import com.example.calculadoraimc.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraImcTheme {
                CalculatorImcScreen()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CalculatorImcScreen() {
    val context = LocalContext.current
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var textResult by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = LIGHT_BLUE,
                title = {
                    Text(
                        text = stringResource(id = R.string.calculator),
                        color = WHITE
                    )
                },
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_refresh),
                        contentDescription = "refresh",
                        modifier = Modifier.clickable {
                            weight = ""
                            height = ""
                            textResult = ""
                        }
                    )
                }
            )
        }
    ) {
        val gradient = Brush.linearGradient(0.3f to WHITE,1.0f to LIGHT_BLUE)
        val bringIntoViewRequester =  remember { BringIntoViewRequester()}
        Column(
            modifier = Modifier
                .background(gradient)
                .padding(30.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.calculator),
                color = LIGHT_BLUE,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Image(painter = painterResource(id = R.drawable.imc), contentDescription = null, modifier = Modifier.size(100.dp))

            Text(
                text = textResult,
                fontSize = 18.sp,
                color = DARK_BLUE,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = weight,
                onValueChange = { weight = it },
                label = {
                    Text("Peso (Kg)")
                },
                maxLines = 1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = LIGHT_BLUE,
                    focusedBorderColor = LIGHT_BLUE,
                    textColor = DARK_BLUE,
                    focusedLabelColor = DARK_BLUE
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .bringIntoViewRequester(
                        bringIntoViewRequester = bringIntoViewRequester
                    ),
                value = height,
                onValueChange = { height = it },
                label = {
                    Text("Altura")
                },
                maxLines = 1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = LIGHT_BLUE,
                    focusedBorderColor = LIGHT_BLUE,
                    textColor = DARK_BLUE,
                    focusedLabelColor = DARK_BLUE
                ),
                textStyle = TextStyle(
                    color = DARK_BLUE,
                    fontSize = 18.sp
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            Button(
                onClick = {
                    if (height.isBlank() || weight.isBlank()) {
                        Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        textResult = CalculateIMC().calculateIMC(height = height, weight = weight)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LIGHT_BLUE,
                    contentColor = WHITE
                )
            ) {
                Text(
                    text = "Calcular IMC",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}

@Composable
@Preview
fun CalculatorImcPreview() {
    CalculatorImcScreen()
}