package com.danp.imcapp.ui.Screens.tareas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.danp.imcapp.data.TareaCategoria

@Composable
fun FiltrosCategorias(
    filtroActivo : TareaCategoria?,
    primaryColor : Color,
    onFiltroClick: (TareaCategoria?) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

        // Chip "Todas"
        item {
            FiltroChip(
                texto    = "Todas",
                selected = filtroActivo == null,
                color    = primaryColor,
                onClick  = { onFiltroClick(null) }
            )
        }

        // Chip por cada categoría
        items(TareaCategoria.entries) { categoria ->
            FiltroChip(
                texto    = "${categoria.etiqueta}",
                selected = filtroActivo == categoria,
                color    = primaryColor,
                onClick  = { onFiltroClick(categoria) }
            )
        }
    }
}

@Composable
private fun FiltroChip(
    texto   : String,
    selected: Boolean,
    color   : Color,
    onClick : () -> Unit
) {
    Surface(
        onClick = onClick,
        shape   = RoundedCornerShape(20.dp),
        color   = if (selected) color else Color.White,
        border  = BorderStroke(1.dp, if (selected) color else Color.LightGray)
    ) {
        Text(
            texto,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            style    = MaterialTheme.typography.labelMedium.copy(
                color      = if (selected) Color.White else Color.Gray,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
        )
    }
}