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
    // Declaramos la clase principal, usando singleton (opcional) Builder y ademas es nuestro patron fachada
    // https://refactoring.guru/es/design-patterns/builder
    // https://refactoring.guru/es/design-patterns/singleton
    // https://refactoring.guru/es/design-patterns/facade
    //Moria.test()
    val moria = Moria.Builder()
        .totalSalas(36)
        .totalPoderVara(30)
        .flechasInicialesCarcaj(10)
        .totalPoderMalignoSala(10)
        .totalFlechasSala(5)
        .totalEnemigosSala(10)
        .build()

    moria.run()
    println()
    println("*** FIN ***")
}
