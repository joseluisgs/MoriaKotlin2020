package moria.peligros

open class Peligro(open var tipo: String) {

    open fun test() {
        println("Soy el peligro tipo $tipo")
    }
}