package moria.peligros

abstract class Peligro(val tipo: String) {

    open fun test() {
        println("Soy el peligro tipo $tipo")
    }

}