package moria.personajes

import moria.objetos.Objeto
import moria.peligros.Peligro
import moria.utils.Utils

// Definimos la clase de personaje. Es open porque podemos instanciarla y heredar
abstract class Personaje(val nombre: String, var vivo: Boolean = true, var objeto: Objeto) {
    private val PROB_HUIR = 80

    abstract fun accion(peligro: Peligro): Boolean

    fun huir(): Boolean {
        println("--> Intentando huir :(")
        val sorteo = Utils.probabilidad(PROB_HUIR, 100)
        if (sorteo) {
            println("--> Hemos tenido suerte y podemos huir :)")
            return true
        } else {
            println("--> No hemos tenido suerte y no podemos huir :_(")
            vivo = false
            return false
        }
    }

    fun peligroSuperado(): Boolean {
        println("--> $nombre ha superado el peligro. ¡Continuamos! :)")
        return true
    }

    // Función de test
    fun test() {
        println("Soy: $nombre ¿Estoy vivo?: $vivo y mi objeto es: ${objeto.tipo}")
    }

}