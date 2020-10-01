package moria.personajes

import moria.objetos.Objeto
import moria.utils.Utils

// Definimos la clase de personaje. Es open porque podemos instanciarla y heredar
open class Personaje(val nombre: String, var vivo: Boolean = true, var objeto: Objeto) {
    private val PROB_HUIR = 80

    fun huir(): Boolean {
        return Utils.probabilidad(80, 100)
    }

    // Función de test
    fun test() {
        println("Soy: $nombre ¿Estoy vivo?: $vivo y mi objeto es: ${objeto.tipo}")
    }

}