package moria.peligros

// Asigno el valor por defecto si no me llega en el costructor
class Magico(tipo: String = "Mágico", var poder: Int = 1) : Peligro(tipo) {

    override fun test() {
        println("Peligro $tipo y poder: $poder")
    }

}