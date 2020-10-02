package moria.salas

import moria.peligros.Peligro
// una vez asiignados el numero y la sala no cambian, son de lectura
data class Sala(val numero: Int = 1, val peligro: Peligro) {

    // Función de test
    fun test() {
        println("Sala: $numero y mi objeto es: ${peligro.tipo}")
    }

}