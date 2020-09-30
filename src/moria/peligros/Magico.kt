package moria.peligros

class Magico(tipo: String = "Mágico", var poder: Int = 1) : Peligro(tipo) {

    override fun test() {
        println("Peligro $tipo y poder: $poder")
    }

}