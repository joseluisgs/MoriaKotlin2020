package moria.peligros

abstract class Peligro(var tipo: String) {

    open fun test() {
        println("Soy el peligro tipo $tipo")
    }

}