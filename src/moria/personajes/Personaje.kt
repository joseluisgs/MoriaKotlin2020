package moria.personajes

import moria.objetos.Objeto

// Definimos la clase de personaje
open class Personaje(val nombre: String, var vivo: Boolean = true, var objeto: Objeto) {

    // Función de test
    fun test() {
        println("Soy: $nombre ¿Estoy vivo?: $vivo y mi objeto es: ${objeto.tipo}")
    }

}