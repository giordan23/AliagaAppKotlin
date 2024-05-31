package com.example.aliagaapp.components.productos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.aliagaapp.model.Producto
import com.example.aliagaapp.model.ProductoEnCompra

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarProductoModal(
    productoEnCompra: ProductoEnCompra?,
    onDismiss: () -> Unit,
    onSave: (ProductoEnCompra) -> Unit,
    productosDisponibles: List<Producto> // Lista de productos disponibles
) {
    var producto by remember { mutableStateOf(productoEnCompra?.producto ?: productosDisponibles.first()) }
    var cantidad by remember { mutableStateOf(productoEnCompra?.cantidad ?: 0.0) }
    var unidadMedida by remember { mutableStateOf(productoEnCompra?.unidadMedida ?: ProductoEnCompra.UnidadMedida.KILOGRAMO) }
    var secado by remember { mutableStateOf(productoEnCompra?.secado ?: ProductoEnCompra.Secado.ESTANDAR) }
    var descuentoKg by remember { mutableStateOf(productoEnCompra?.descuentoKg ?: 0.0) }

    var expandedProducto by remember { mutableStateOf(false) }
    var expandedUnidadMedida by remember { mutableStateOf(false) }
    var expandedSecado by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                val nuevoProductoEnCompra = ProductoEnCompra(
                    producto = producto,
                    cantidad = cantidad,
                    descuentoKg = descuentoKg,
                    unidadMedida = unidadMedida,
                    secado = secado
                )
                onSave(nuevoProductoEnCompra)
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancelar")
            }
        },
        title = { Text("Agregar Producto") },
        text = {
            Column {
                // ComboBox para seleccionar el producto
                ExposedDropdownMenuBox(
                    expanded = expandedProducto,
                    onExpandedChange = { expandedProducto = !expandedProducto }
                ) {
                    TextField(
                        readOnly = true,
                        value = "${producto.nombreProducto.name} - S/. ${producto.precio}",
                        onValueChange = { },
                        label = { Text("Producto") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expandedProducto
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedProducto,
                        onDismissRequest = { expandedProducto = false }
                    ) {
                        productosDisponibles.forEach { prod ->
                            DropdownMenuItem(
                                text = {
                                    Text("${prod.nombreProducto.name} - S/. ${prod.precio}")
                                },
                                onClick = {
                                    producto = prod
                                    expandedProducto = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Input numérico para la cantidad
                TextField(
                    value = cantidad.toString(),
                    onValueChange = { cantidad = it.toDoubleOrNull() ?: 0.0 },
                    label = { Text("Cantidad") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // ComboBox para la unidad de medida
                ExposedDropdownMenuBox(
                    expanded = expandedUnidadMedida,
                    onExpandedChange = { expandedUnidadMedida = !expandedUnidadMedida }
                ) {
                    TextField(
                        readOnly = true,
                        value = unidadMedida.name,
                        onValueChange = { },
                        label = { Text("Unidad de Medida") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expandedUnidadMedida
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedUnidadMedida,
                        onDismissRequest = { expandedUnidadMedida = false }
                    ) {
                        ProductoEnCompra.UnidadMedida.values().forEach { unidad ->
                            DropdownMenuItem(
                                text = {
                                    Text(unidad.name)
                                },
                                onClick = {
                                    unidadMedida = unidad
                                    expandedUnidadMedida = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ComboBox para el secado
                ExposedDropdownMenuBox(
                    expanded = expandedSecado,
                    onExpandedChange = { expandedSecado = !expandedSecado }
                ) {
                    TextField(
                        readOnly = true,
                        value = secado.name,
                        onValueChange = { },
                        label = { Text("Secado") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expandedSecado
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedSecado,
                        onDismissRequest = { expandedSecado = false }
                    ) {
                        ProductoEnCompra.Secado.values().forEach { sec ->
                            DropdownMenuItem(
                                text = {
                                    Text(sec.name)
                                },
                                onClick = {
                                    secado = sec
                                    expandedSecado = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Input numérico para el descuentoKg
                TextField(
                    value = descuentoKg.toString(),
                    onValueChange = { descuentoKg = it.toDoubleOrNull() ?: 0.0 },
                    label = { Text("Descuento (Kg)") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
            }
        }
    )
}
