package moria.objetos

class Vara(override var tipo: String = "Vara", var energia: Int = 1) : Objeto(tipo) {

    override fun test() {
        println("Objeto $tipo y energía: $energia")
    }
}