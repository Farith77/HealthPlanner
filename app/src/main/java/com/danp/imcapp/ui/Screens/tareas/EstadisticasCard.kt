package com.danp.imcapp.ui.Screens.tareas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.danp.imcapp.data.TareasEstadisticas
import androidx.compose.ui.graphics.Color

@Composable
fun EstadisticasCard(
    estadisticas: TareasEstadisticas,
    primaryColor: Color
) {
    Card(
        modifier  = Modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(containerColor = primaryColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                "Progreso General",
                style = MaterialTheme.typography.titleSmall.copy(
                    color      = Color.White.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Medium
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Barra de progreso
            LinearProgressIndicator(
                progress           = { estadisticas.porcentajeCumplimiento / 100f },
                modifier           = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color              = Color.White,
                trackColor         = Color.White.copy(alpha = 0.3f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Contadores
            Row(
                modifier              = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                EstadisticaItem(
                    valor    = estadisticas.total.toString(),
                    etiqueta = "Total"
                )
                EstadisticaItem(
                    valor    = estadisticas.completadas.toString(),
                    etiqueta = "Hechas"
                )
                EstadisticaItem(
                    valor    = estadisticas.pendientes.toString(),
                    etiqueta = "Pendientes"
                )
                EstadisticaItem(
                    valor    = "${estadisticas.porcentajeCumplimiento.toInt()}%",
                    etiqueta = "Completado"
                )
            }
        }
    }
}

@Composable
private fun EstadisticaItem(valor: String, etiqueta: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            valor,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color      = Color.White
            )
        )
        Text(
            etiqueta,
            style = MaterialTheme.typography.labelSmall.copy(
                color = Color.White.copy(alpha = 0.8f)
            )
        )
    }
}