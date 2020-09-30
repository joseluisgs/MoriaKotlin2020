package moria.objetos

// Las variables a heredar tambíien deben ser open
abstract class Objeto(open var tipo: String) {

    open fun test() {
        println("Soy el objeto tipo $tipo")
    }
}