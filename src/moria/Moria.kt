package moria

import moria.objetos.Anillo
import moria.objetos.Carcaj
import moria.objetos.Vara
import moria.peligros.Accion
import moria.personajes.Personaje
import moria.salas.Sala
import kotlin.random.Random

// Definimos Moria como Object y de esta manera implementamos el singleton
// https://blog.mindorks.com/how-to-create-a-singleton-class-in-kotlin
object Moria {
    // Constantes del sistema
    private val MAX_ENERGIA: Int = 50
    private val MAX_FLECHAS: Int = 20

    // Variables de Moria, con lateint indicamos que las inicializaremos fuera del int o de la declaracion
    // Personajes
    private lateinit var gandalf: Personaje
    private lateinit var legolas: Personaje
    private lateinit var frodo: Personaje
    // Salas

    // Me gusta definir las cosas en el init para evitar ensuciar el código
    // a diferencia con constructor es que este esta pensado para tareas mas "cargadas" y una vez creado el objeto
    // Le asigna los valores que queramos
    init {
        initPersonajes()
    }

    // Inicia los personajes
    private fun initPersonajes() {
        // Como vemos estamos realizando una inyección de dependencias usando agragaciones con objetos asbtractos
        gandalf = Personaje("Gandalf", true, Vara(energia = Random.nextInt(1, MAX_ENERGIA)))
        legolas = Personaje("Legolas", true, Carcaj(cantidad = Random.nextInt(1, MAX_FLECHAS)))
        frodo = Personaje(nombre = "Frodo", objeto = Anillo())
    }

    // función de ejecución
    public fun run() {
        println("Moria--> Ejecutandose")
    }

    // funcion de test
    fun test() {
        println("Soy Moria")
        gandalf.test()
        gandalf.objeto.test()
        legolas.test()
        legolas.objeto.test()
        frodo.test()
        frodo.objeto.test()

        var sala: Sala = Sala(1, Accion())
        sala.test()
        sala.peligro.test()
    }
}