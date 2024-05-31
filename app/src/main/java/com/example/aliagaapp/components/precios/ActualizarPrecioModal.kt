package com.example.aliagaapp.components.precios

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aliagaapp.viewmodel.ProductoViewModel

@Composable
fun ActualizarPrecioPage(navController: NavController, producto: String, productViewModel: ProductoViewModel = viewModel(), modifier: Modifier = Modifier) {
    val decodedProducto = java.net.URLDecoder.decode(producto, "UTF-8")
    val productoObj = productViewModel.productos.find { it.nombreProducto.name == decodedProducto }
    var inputPrice by remember { mutableStateOf(TextFieldValue("%.2f".format(productoObj?.precio ?: 0.0))) }
    var showDialog by remember { mutableStateOf(true) } // Mostrar el diálogo inicialmente

    fun handleInput(input: String): String {
        val sanitizedInput = input.replace(Regex("[^\\d]"), "")
        if (sanitizedInput.isEmpty()) return "0.00"

        val newInput = sanitizedInput.padStart(3, '0')
        val integerPart = newInput.dropLast(2).trimStart('0').ifEmpty { "0" }
        val decimalPart = newInput.takeLast(2)
        return "$integerPart.$decimalPart"
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        productoObj?.precio = inputPrice.text.toDoubleOrNull() ?: productoObj?.precio ?: 0.0
                        showDialog = false
                        navController.popBackStack() // Volver a la página anterior después de guardar el precio
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                        navController.popBackStack() // Volver a la página anterior al cancelar
                    }
                ) {
                    Text("Cancelar")
                }
            },
            title = { Text(text = "Ingresar nuevo precio para $decodedProducto") },
            text = {
                OutlinedTextField(
                    value = inputPrice,
                    onValueChange = { newValue ->
                        val formattedInput = handleInput(newValue.text)
                        inputPrice = TextFieldValue(
                            text = formattedInput,
                            selection = TextRange(formattedInput.length)
                        )
                    },
                    label = { Text("Nuevo Precio") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }
}
