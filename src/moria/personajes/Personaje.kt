package moria.personajes

import moria.objetos.Objeto

// Definimos la clase de personaje
open class Personaje(val nombre: String, var estado: Boolean, var objeto: Objeto) {

    // Función de test
    fun test() {
        println("Soy $nombre estoy $estado y mi objeto es: ${objeto.tipo}")
    }
}