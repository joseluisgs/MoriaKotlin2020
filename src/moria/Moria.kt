package moria
import moria.objetos.Vara
import moria.personajes.Personaje
class Moria {
    // Constantes del sistema
    final val MAX_ENERGIA: Int = 50

    // función de ejecución
    public fun run() {
        println("Moria--> Ejecutandose")
    }

    // funcion de test
    fun test() {
        println("Soy Moria")
        var p: Personaje = Personaje("Gandalf", true, objeto = Vara(energia = this.MAX_ENERGIA))
        p.test()
    }
}