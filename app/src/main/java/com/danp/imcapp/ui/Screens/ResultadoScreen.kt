package com.danp.imcapp.ui.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.danp.imcapp.data.IMCResult
import com.danp.imcapp.ui.viewmodels.ResultadosVM
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun ResultadoScreen(
    navController: NavController,
    viewModel: ResultadosVM = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val resultado = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<IMCResult>("resultado")

    if (resultado != null && viewModel.resultado == null) {
        viewModel.resultado = resultado
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Tu Resultado", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        Text("IMC: ${viewModel.getIMC()}", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(10.dp))
        Text(viewModel.getClasificacion(), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(10.dp))
        Text(viewModel.getDescripcion(), style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
        Button(
            onClick = {
                navController.navigate("tareas/${viewModel.resultado?.clasificacion}")
            },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape    = RoundedCornerShape(28.dp),
        ) {
            Text("TAREAS", fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
        }
    }
}