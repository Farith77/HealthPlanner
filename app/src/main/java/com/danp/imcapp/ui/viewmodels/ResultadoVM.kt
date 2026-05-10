package com.danp.imcapp.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.danp.imcapp.data.IMCResult
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class ResultadosVM : ViewModel() {

    var resultado by mutableStateOf<IMCResult?>(null)

    fun getIMC(): String {
        return resultado?.imc?.let {
            String.format("%.2f", it)
        } ?: "--"
    }

    fun getClasificacion(): String {
        return resultado?.clasificacion ?: ""
    }

    fun getDescripcion(): String {
        return resultado?.descripcion ?: ""
    }

    // para UI dinámica
    fun getColorEstado(): String {
        val imc = resultado?.imc ?: return "GRAY"

        return when {
            imc < 18.5 -> "BLUE"
            imc < 25 -> "GREEN"
            imc < 30 -> "YELLOW"
            else -> "RED"
        }
    }
}