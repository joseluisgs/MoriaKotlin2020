// Fichero principal de las Minas de Moria

// importamos la clase principal
import moria.Moria

fun main() {
    println("¡BIENVENIDOS A MORIA!")
    // Declaramos la clase principal, usando singleton
    // https://refactoring.guru/es/design-patterns/singleton
    //Moria.test()
    Moria.run()
    println("FIN")
}