package moria.personajes

import moria.objetos.Objeto
import kotlin.random.Random

// Definimos la clase de personaje
open class Personaje(val nombre: String, var vivo: Boolean = true, var objeto: Objeto) {
    private val PROB_HUIR = 50

    fun huir(): Boolean {
        return Random.nextInt(1, 81) <= PROB_HUIR
    }

    // Función de test
    fun test() {
        println("Soy: $nombre ¿Estoy vivo?: $vivo y mi objeto es: ${objeto.tipo}")
    }

}