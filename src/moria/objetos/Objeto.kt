package moria.objetos

// Las variables a heredar tambíén deben ser open
abstract class Objeto(var tipo: String) {

    open fun test() {
        println("Soy el objeto tipo $tipo")
    }
}