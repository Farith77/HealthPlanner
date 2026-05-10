package com.danp.imcapp.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.danp.imcapp.data.IMCState
import com.danp.imcapp.data.Sexo
import com.danp.imcapp.data.IMCCalculator
import com.danp.imcapp.data.IMCResult
import com.danp.imcapp.data.IMCValidator

class InicioVM : ViewModel() {

    var state = mutableStateOf(IMCState())
        private set

    // --- SEXO ---
    fun seleccionarSexo(sexo: Sexo) {
        state.value = state.value.copy(sexo = sexo)
    }

    // --- PESO ---
    fun aumentarPeso() {
        val nuevoPeso = state.value.peso + 1
        if (IMCValidator.validarPeso(nuevoPeso)) {
            state.value = state.value.copy(peso = nuevoPeso)
        }
    }

    fun disminuirPeso() {
        val nuevoPeso = state.value.peso - 1
        if (IMCValidator.validarPeso(nuevoPeso)) {
            state.value = state.value.copy(peso = nuevoPeso)
        }
    }

    // --- ALTURA (con un slider) ---
    fun cambiarAltura(nuevaAltura: Int) {
        if (IMCValidator.validarAltura(nuevaAltura)) {
            state.value = state.value.copy(altura = nuevaAltura)
        }
    }

    // --- EDAD ---
    fun aumentarEdad() {
        val nuevaEdad = state.value.edad + 1
        if (IMCValidator.validarEdad(nuevaEdad)) {
            state.value = state.value.copy(edad = nuevaEdad)
        }
    }

    fun disminuirEdad() {
        val nuevaEdad = state.value.edad - 1
        if (IMCValidator.validarEdad(nuevaEdad)) {
            state.value = state.value.copy(edad = nuevaEdad)
        }
    }

    // --- CALCULAR RESULTADO ---
    fun calcularResultado(): IMCResult {
        return IMCCalculator.calcularResultado(
            peso = state.value.peso,
            altura = state.value.altura
        )
    }
}