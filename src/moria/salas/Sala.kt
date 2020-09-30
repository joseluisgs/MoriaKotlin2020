package moria.salas

import moria.peligros.Peligro

class Sala(var numero: Int = 1, var peligro: Peligro) {
    // Función de test
    fun test() {
        println("Sala: $numero y mi objeto es: ${peligro.tipo}")
    }
}