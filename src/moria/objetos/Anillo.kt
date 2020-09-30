package moria.objetos

class Anillo(override var tipo: String = "Anillo", var puesto: Boolean = false) : Objeto(tipo) {

    override fun test() {
        println("Soy el objeto $tipo y mis caracteristica es puesto: $puesto")
    }
}