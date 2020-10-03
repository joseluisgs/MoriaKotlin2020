package moria.salas

import moria.peligros.Peligro

/**
 * Clase sala. La definimos como un POJO para tener sus métodos típicos usando data
 * @property numero Int numero de la sala
 * @property peligro Peligro tipo de peligro asignado
 * @constructor
 */
data class Sala(val numero: Int = 1, val peligro: Peligro) {

    // Función de test
//    fun test() {
//        println("Sala: $numero y mi objeto es: ${peligro.tipo}")
//    }

}