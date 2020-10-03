// Fichero principal de las Minas de Moria

// importamos la clase principal
import moria.Moria

/**
 * Punto de entrada a nuestro programa. Además es nuestra fachada
 *  https://refactoring.guru/es/design-patterns/facade
 */
fun main() {
    println("*** ¡BIENVENIDOS A MORIA! ***")
    println()
    // Declaramos la clase principal, usando singleton y ademas es nuestro patron fachada
    // https://refactoring.guru/es/design-patterns/singleton
    // https://refactoring.guru/es/design-patterns/facade
    //Moria.test()
    Moria.run()
    println()
    println("*** FIN ***")
}
