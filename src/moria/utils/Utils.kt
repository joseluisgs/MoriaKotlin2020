package moria.utils

import kotlin.random.Random

/**
 * Funciones de utilidad. Son métodos de clase (estáticos) por eso se usa companion para definirlos dentro.
 * Otra cosa es usar object y que sea un singleton
 */
class Utils {
    // un objeto companion es lo normal para hacer metodos y propiedades de clase (estáticos), solo los que vayan del companion
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