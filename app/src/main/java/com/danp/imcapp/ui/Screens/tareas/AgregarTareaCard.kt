package com.danp.imcapp.ui.Screens.tareas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.danp.imcapp.data.TareaCategoria

@Composable
fun AgregarTareaCard(
    titulo           : String,
    categoria        : TareaCategoria,
    primaryColor     : Color,
    onTituloChange   : (String) -> Unit,
    onCategoriaChange: (TareaCategoria) -> Unit,
    onAgregar        : () -> Unit
) {
    var expandido by remember { mutableStateOf(false) }

    Card(
        modifier  = Modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Header del card — toca para expandir
            Row(
                modifier      = Modifier
                    .fillMaxWidth()
                    .clickable { expandido = !expandido },
                verticalAlignment     = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "➕ Agregar nueva tarea",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Icon(
                    imageVector        = if (expandido) Icons.Default.KeyboardArrowUp
                    else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint               = Color.Gray
                )
            }

            // Formulario — solo visible si está expandido
            if (expandido) {
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value         = titulo,
                    onValueChange = onTituloChange,
                    label         = { Text("Título de la tarea") },
                    modifier      = Modifier.fillMaxWidth(),
                    shape         = RoundedCornerShape(12.dp),
                    singleLine    = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Selector de categoría
                Text(
                    "Categoría:",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(6.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(TareaCategoria.entries) { cat ->
                        val seleccionada = cat == categoria
                        Surface(
                            onClick = { onCategoriaChange(cat) },
                            shape   = RoundedCornerShape(20.dp),
                            color   = if (seleccionada) primaryColor else Color(0xFFF0EBF8),
                            border  = BorderStroke(
                                1.dp,
                                if (seleccionada) primaryColor else Color.Transparent
                            )
                        ) {
                            Text(
                                "${cat.etiqueta}",
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                style    = MaterialTheme.typography.labelSmall.copy(
                                    color = if (seleccionada) Color.White else Color.Gray
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick  = {
                        onAgregar()
                        expandido = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape    = RoundedCornerShape(12.dp),
                    colors   = ButtonDefaults.buttonColors(containerColor = primaryColor),
                    enabled  = titulo.isNotBlank()
                ) {
                    Text("Agregar", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}