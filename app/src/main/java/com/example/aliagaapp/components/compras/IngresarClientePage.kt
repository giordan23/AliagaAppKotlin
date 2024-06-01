package com.example.aliagaapp.components.compras

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aliagaapp.viewmodel.ClienteViewModel
import com.example.aliagaapp.viewmodel.ProductoViewModel

@Composable
fun IngresarClientePage(
    navController: NavController,
    productViewModel: ProductoViewModel = viewModel(),
    clienteViewModel: ClienteViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    var dni by remember { mutableStateOf("") }
    val productos = productViewModel.productos
    val token = "69f3bb15b48da807ba6d3b8597366fab94d8487ea8f3a558b283aac977cc1622" // Reemplaza esto con tu token real

    val clienteResponse = clienteViewModel.clienteResponse
    val nombreCompleto = clienteViewModel.nombreCompleto

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Lista de productos y precios
        Text(text = "Precios de Compra", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        productos.forEach { producto ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        val encodedProducto = producto.nombreProducto.name
                        navController.navigate("actualizarPrecio/$encodedProducto")
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = producto.nombreProducto.name, fontSize = 18.sp)
                Text(text = "${producto.precio}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Divider(color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Input para DNI Cliente
        OutlinedTextField(
            value = dni,
            onValueChange = {
                if (it.length <= 8 && it.all { char -> char.isDigit() }) {
                    dni = it
                }
            },
            label = { Text("DNI Cliente") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón que se activa cuando el DNI tiene 8 dígitos
        Button(
            onClick = { clienteViewModel.fetchClienteInfo(dni, token) },
            enabled = dni.length == 8,
            colors = ButtonDefaults.buttonColors(containerColor = if (dni.length == 8) MaterialTheme.colorScheme.primary else Color.Gray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Validar DNI", color = Color.White, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input para Nombre Completo del Cliente
        OutlinedTextField(
            value = nombreCompleto,
            onValueChange = { /* No editable */ },
            label = { Text("Nombre Completo") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón "Siguiente"
        Button(
            onClick = { navController.navigate("productosCompra") },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Siguiente", color = Color.White, fontSize = 18.sp)
        }
    }
}
