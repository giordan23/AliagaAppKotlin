package com.example.aliagaapp.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aliagaapp.R

@Composable
fun MainPage(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Encabezado
        Text(
            text = "BIENVENIDO",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Imagen
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Imagen de Bienvenida",
            modifier = Modifier
                .size(350.dp)
                .padding(bottom = 16.dp)
        )

        // Botones
        Button(
            onClick = { navController.navigate("compras") },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Text(text = "COMPRAS", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }

        Button(
            onClick = { /* Acción del segundo botón */ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Text(text = "VENTAS", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }

        Button(
            onClick = { /* Acción del segundo botón */ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Text(text = "ADELANTOS", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }

        Button(
            onClick = { /* Acción del segundo botón */ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Text(text = "CAJA", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
    }
}
