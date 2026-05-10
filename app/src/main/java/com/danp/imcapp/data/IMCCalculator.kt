package com.danp.imcapp.data

object IMCCalculator {

    fun calcularIMC(peso: Int, alturaCm: Int): Float {
        val alturaM = alturaCm / 100f
        return peso / (alturaM * alturaM)
    }

    fun clasificarIMC(imc: Float): String {
        return when {
            imc < 18.5 -> "Bajo peso"
            imc < 25 -> "Normal"
            imc < 30 -> "Sobrepeso"
            else -> "Obesidad"
        }
    }

    fun getDescripcion(imc: Float): String {
        return when {
            imc < 18.5 -> "Tienes un peso por debajo de lo recomendado."
            imc < 25 -> "Tu peso es saludable."
            imc < 30 -> "Tienes sobrepeso, considera mejorar hábitos."
            else -> "Nivel de obesidad, se recomienda atención médica."
        }
    }

    fun calcularResultado(peso: Int, altura: Int): IMCResult {
        val imc = calcularIMC(peso, altura)
        return IMCResult(
            imc = imc,
            clasificacion = clasificarIMC(imc),
            descripcion = getDescripcion(imc)
        )
    }
}