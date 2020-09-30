package moria.objetos

class Vara(override var tipo: String = "Vara", var energia: Int = 1) : Objeto(tipo) {
    // funcion de incialización
//    init {
//        println(energia)
//    }
    override fun test() {
        println("Soy el objeto $tipo y mis caracteristica es energía: $energia")
    }
}