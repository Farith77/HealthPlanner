package com.danp.imcapp.ui.Screens.tareas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.danp.imcapp.data.Tarea

@Composable
fun ListaTareas(
    tareas      : List<Tarea>,
    primaryColor: Color,
    onToggle    : (Int) -> Unit,
    onEliminar  : (Int) -> Unit
) {
    if (tareas.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "No hay tareas en esta categoría",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
        return
    }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(
            items = tareas,
            key   = { it.id }        // key para animaciones y performance
        ) { tarea ->
            TareaItem(
                tarea        = tarea,
                primaryColor = primaryColor,
                onToggle     = { onToggle(tarea.id) },
                onEliminar   = { onEliminar(tarea.id) }
            )
        }
    }
}

@Composable
private fun TareaItem(
    tarea       : Tarea,
    primaryColor: Color,
    onToggle    : () -> Unit,
    onEliminar  : () -> Unit
) {
    Card(
        modifier  = Modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(14.dp),
        colors    = CardDefaults.cardColors(
            containerColor = if (tarea.completada) Color(0xFFF0FFF4) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier          = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Checkbox
            Checkbox(
                checked         = tarea.completada,
                onCheckedChange = { onToggle() },
                colors          = CheckboxDefaults.colors(
                    checkedColor = primaryColor
                )
            )

            Spacer(modifier = Modifier.width(10.dp))

            // Texto
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    tarea.titulo,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight    = FontWeight.SemiBold,
                        textDecoration = if (tarea.completada) TextDecoration.LineThrough
                        else TextDecoration.None,
                        color          = if (tarea.completada) Color.Gray else Color.Black
                    )
                )
                if (tarea.descripcion.isNotBlank()) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        tarea.descripcion,
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                // Badge de categoría
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = primaryColor.copy(alpha = 0.1f)
                ) {
                    Text(
                        "${tarea.categoria.etiqueta}",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        style    = MaterialTheme.typography.labelSmall.copy(color = primaryColor)
                    )
                }
            }

            // Eliminar
            IconButton(onClick = onEliminar) {
                Icon(
                    imageVector        = Icons.Default.Delete,
                    contentDescription = "Eliminar tarea",
                    tint               = Color.LightGray
                )
            }
        }
    }
}