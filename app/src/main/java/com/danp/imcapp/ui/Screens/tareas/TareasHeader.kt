package com.danp.imcapp.ui.Screens.tareas

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TareasHeader(
    clasificacion: String,
    onVolver: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onVolver) {
            Icon(
                imageVector        = Icons.Default.ArrowBack,
                contentDescription = "Volver"
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                "Mis Tareas",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                "Plan para: $clasificacion",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}