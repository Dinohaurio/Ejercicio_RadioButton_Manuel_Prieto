package com.example.ejercicio_radiobutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Grade
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicio_radiobutton.ui.theme.Ejercicio_RadioButtonTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ejercicio_RadioButtonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Red)){
                            MyIcon()
                        }

                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray)) {
                            Column (horizontalAlignment = Alignment.CenterHorizontally){
                                MyTextButton()
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Cyan)) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally){
                                MyCheckBox()
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Green)) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally){
                                MySwitch()
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(Color.Blue)) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally){
                                Imagen()
                            }
                        }
                    }


                }
            }
        }
    }

    //1. Botón texto Presionar
    @Composable
    fun MyTextButton() {
        var show by rememberSaveable {
            mutableStateOf(false)
        }
        var texto by rememberSaveable {
            mutableStateOf("Botón presionado")
        }

        if (show) {
            var isLoading by rememberSaveable { mutableStateOf(true) }

            LaunchedEffect(isLoading) {
                delay(5000)
                isLoading = false
            }
            if (isLoading) {
                CircularProgressIndicator(color = Color.Red)
                Text(text = texto)
            } else {
                show = !show
            }
        }
        Button(
            onClick = { show = !show },
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(text = "Pulsar")
        }

    }


    //2-3. Una casilla de verificación (checkbox) con el texto 'Activar' que, al marcarla, mostrará el Text anterior.
    @Composable
    fun MyCheckBox() {
        var showMessage by rememberSaveable { mutableStateOf(false) }
        var texto by rememberSaveable { mutableStateOf("Este es el mensaje") }
        Checkbox(
            checked = showMessage,
            onCheckedChange = { isChecked ->
                showMessage = isChecked
            },
        )
        if (showMessage) {
            Text(text = texto)
        }
    }


    //4. Icono
    @Composable
    fun MyIcon() {
        Icon(
            imageVector = Icons.Rounded.Grade,
            contentDescription = "Icono",
            tint = Color.Blue,
            modifier = Modifier.size(50.dp)
        )
    }

    //5-6 Switch y RadioButton
    @Composable
    fun MySwitch() {
        var switchState by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf("Opción 1") }
        Switch(
            checked = switchState,
            onCheckedChange = { checked ->
                switchState = checked
            }
        )
        if (switchState) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = selectedOption == "Opción 1", onClick = {
                    selectedOption = "Opción 1"
                })
                Text(text = "Opción 1")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = selectedOption == "Opción 2", onClick = {
                    selectedOption = "Opción 2"
                })
                Text(text = "Opción 2")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = selectedOption == "Opción 3", onClick = {
                    selectedOption = "Opción 3"
                })
                Text(text = "Opción 3")
            }

        }
        Text(text = "Seleccionaste: $selectedOption")
    }

    //7. Imgaen que se actualiza al hacer click en el botón
    @Composable
    fun Imagen(){
        var imagenActual by remember { mutableStateOf(0) }
        val imagenes = listOf(
            R.drawable.gato,
            R.drawable.alonso,
            R.drawable.nosferatu
        )
        Image(painter = painterResource(id = imagenes[imagenActual]), contentDescription = "Imagen",
                modifier = Modifier
                .size(280.dp)
                .padding(10.dp)
        )
        Button(
            onClick = {imagenActual = (imagenActual + 1) % imagenes.size},
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(text = "Cambiar Imagen")
        }
    }
}

