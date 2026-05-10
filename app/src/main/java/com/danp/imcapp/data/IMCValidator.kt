package com.danp.imcapp.data

object IMCValidator {

    fun validarPeso(peso: Int): Boolean {
        return peso in 1..300
    }

    fun validarAltura(altura: Int): Boolean {
        return altura in 50..250
    }

    fun validarEdad(edad: Int): Boolean {
        return edad in 1..120
    }
}