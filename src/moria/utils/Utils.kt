package moria.utils

import kotlin.random.Random

class Utils {
    // un objeto companion es lo normal para hacer metodos estáticos, solo los que vayan del companion
    companion object {
        fun probabilidad(limite: Int, max: Int): Boolean {
            val sorteo = Random.nextInt(max)
            // println("probabilid $sorteo y mi limite es $limite")
            return sorteo <= limite
        }
    }
}

// Otra forma. Todos los métodos y objetos serán estáticos
//object Utils {
//    fun probabilidad(limite: Int, max: Int): Boolean {
//        val sorteo = Random.nextInt(max)
//        println("probabilid $sorteo y mi limite es $limite")
//        return sorteo <= limite
//    }
//}