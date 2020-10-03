package moria.peligros

/**
 * Peligro de acción
 * @property flechas Int Flechas existentes a recoger
 * @property enemigos Int Enemigos existentes
 * @constructor
 */
class Accion(tipo: String = "Acción", var flechas: Int = 1, var enemigos: Int = 1) : Peligro(tipo) {

//    override fun test() {
//        println("Peligro $tipo hay flechas: $flechas y enemigos: $enemigos")
//    }

}