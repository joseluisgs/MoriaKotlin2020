package moria.peligros

class Habilidad(override var tipo: String = "Habilidad") : Peligro(tipo) {

    override fun test() {
        println("Peligro $tipo")
    }
}