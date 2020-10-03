package moria.objetos

/**
 * Anillo que se puede portar
 * @property puesto Boolean, indica si esta puesto (true) o no (false)
 * @constructor
 */
class Anillo(tipo: String = "Anillo", var puesto: Boolean = false) : Objeto(tipo) {

//    override fun test() {
//        println("Soy el objeto $tipo y mis caracteristica es puesto: $puesto")
//    }
}