package com.danp.imcapp.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.danp.imcapp.data.Sexo
import com.danp.imcapp.ui.viewmodels.InicioVM
import androidx.compose.ui.graphics.Color

@Composable
fun InicioScreen(
    navController: NavController,
    viewModel: InicioVM = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val state = viewModel.state.value

    val backgroundColor = Color(0xFFF3EEF8)
    val primaryColor    = Color(0xFF7C3AED)
    val cardColor       = Color.White

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 48.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                // --- TÍTULO ---
                Column {
                    Text(
                        "Indice Masa Corporal",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        "Ingrese y seleccione la siguiente informacion",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }

                // --- SEXO ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SexoCard(
                        label    = "Hombre",
                        icono    = Icons.Default.Person,
                        selected = state.sexo == Sexo.HOMBRE,
                        color    = primaryColor,
                        modifier = Modifier.weight(1f),
                        onClick  = { viewModel.seleccionarSexo(Sexo.HOMBRE) }
                    )
                    SexoCard(
                        label    = "Mujer",
                        icono    = Icons.Default.Person,
                        selected = state.sexo == Sexo.MUJER,
                        color    = primaryColor,
                        modifier = Modifier.weight(1f),
                        onClick  = { viewModel.seleccionarSexo(Sexo.MUJER) }
                    )
                }

                // --- ALTURA ---
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape    = RoundedCornerShape(16.dp),
                    colors   = CardDefaults.cardColors(containerColor = cardColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Altura", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                "${state.altura}",
                                style = MaterialTheme.typography.displayMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color      = primaryColor
                                )
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                "cm",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Gray,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                        Slider(
                            value    = state.altura.toFloat(),
                            onValueChange = { viewModel.cambiarAltura(it.toInt()) },
                            valueRange    = 100f..220f,
                            colors   = SliderDefaults.colors(
                                thumbColor       = primaryColor,
                                activeTrackColor = primaryColor
                            )
                        )
                    }
                }

                // --- PESO Y EDAD ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ContadorCard(
                        label    = "Peso(kg)",
                        valor    = state.peso,
                        color    = primaryColor,
                        modifier = Modifier.weight(1f),
                        onMenos  = { viewModel.disminuirPeso() },
                        onMas    = { viewModel.aumentarPeso() }
                    )
                    ContadorCard(
                        label    = "Edad",
                        valor    = state.edad,
                        color    = primaryColor,
                        modifier = Modifier.weight(1f),
                        onMenos  = { viewModel.disminuirEdad() },
                        onMas    = { viewModel.aumentarEdad() }
                    )
                }
            }

            // --- CALCULAR ---
            Button(
                onClick = {
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("resultado", viewModel.calcularResultado())
                    navController.navigate("resultado")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape  = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
            ) {
                Text(
                    "CALCULAR",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                )
            }
        }
    }
}

// --- Composable auxiliar: tarjeta de sexo ---
@Composable
fun SexoCard(
    label: String,
    icono: ImageVector,
    selected: Boolean,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier  = modifier.clickable { onClick() },
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(
            containerColor = if (selected) color else Color(0xFFF0EBF8)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icono,
                contentDescription = label,
                tint   = if (selected) Color.White else color,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                label,
                color = if (selected) Color.White else Color.Gray,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        }
    }
}

// --- Composable auxiliar: tarjeta de contador ---
@Composable
fun ContadorCard(
    label: String,
    valor: Int,
    color: Color,
    modifier: Modifier = Modifier,
    onMenos: () -> Unit,
    onMas: () -> Unit
) {
    Card(
        modifier  = modifier,
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(label, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "$valor",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                FilledIconButton(
                    onClick = onMenos,
                    colors  = IconButtonDefaults.filledIconButtonColors(
                        containerColor = Color(0xFFF0EBF8)
                    ),
                    modifier = Modifier.size(36.dp)
                ) {
                    Text("−", color = color, style = MaterialTheme.typography.titleMedium)
                }
                FilledIconButton(
                    onClick = onMas,
                    colors  = IconButtonDefaults.filledIconButtonColors(
                        containerColor = Color(0xFFF0EBF8)
                    ),
                    modifier = Modifier.size(36.dp)
                ) {
                    Text("+", color = color, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}