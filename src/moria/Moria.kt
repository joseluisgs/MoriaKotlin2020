package moria

import moria.objetos.Anillo
import moria.objetos.Carcaj
import moria.objetos.Vara
import moria.peligros.Accion
import moria.peligros.Habilidad
import moria.peligros.Magico
import moria.personajes.Elfo
import moria.personajes.Hobbit
import moria.personajes.Mago
import moria.personajes.Personaje
import moria.salas.Sala
import kotlin.random.Random

// Definimos Moria como Object y de esta manera implementamos el singleton
// https://blog.mindorks.com/how-to-create-a-singleton-class-in-kotlin
object Moria {
    // Constantes del sistema
    private val MAX_ENERGIA: Int = 50
    private val MAX_FLECHAS: Int = 20
    private val MAX_SALAS: Int = 36
    private val MAX_SALA_MALIGNO: Int = 10
    private val MAX_SALA_FLECHAS: Int = 10
    private val MAX_SALA_ENEMIGOS: Int = 10

    // Variables de Moria, con lateint indicamos que las inicializaremos fuera del int o de la declaracion
    // Personajes
    private lateinit var gandalf: Personaje
    private lateinit var legolas: Personaje
    private lateinit var frodo: Personaje

    // Lista de salas
    private var salas = mutableListOf<Sala>()

    // Me gusta definir las cosas en el init para evitar ensuciar el código
    // a diferencia con constructor es que este esta pensado para tareas mas "cargadas" y una vez creado el objeto
    // Le asigna los valores que queramos
    init {
        initPersonajes()
        initSalas()
    }

    // Inicia los personajes
    private fun initPersonajes() {
        // Como vemos estamos realizando una inyección de dependencias usando agragaciones con objetos asbtractos
        gandalf = Personaje("Gandalf", true, Vara(energia = Random.nextInt(1, MAX_ENERGIA)))
        legolas = Personaje("Legolas", true, Carcaj(cantidad = Random.nextInt(1, MAX_FLECHAS)))
        frodo = Personaje(nombre = "Frodo", objeto = Anillo())
    }

    // Inicia la salas
    private fun initSalas() {
        for (i in 1..MAX_SALAS) {
            when (Random.nextInt(1, 4)) {
                1 -> salas.add(Sala(i, Magico(poder = Random.nextInt(1, MAX_SALA_MALIGNO))))
                2 -> salas.add(
                    Sala(
                        i,
                        Accion(
                            flechas = Random.nextInt(1, MAX_SALA_FLECHAS),
                            enemigos = Random.nextInt(1, MAX_SALA_ENEMIGOS)
                        )
                    )
                )
                3 -> salas.add(Sala(i, Habilidad()))
//                else -> {
//                }
            }
        }
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

        val mago = Mago("El Mago", vivo = true, Vara())
        mago.test()
        mago.recargarVara(20)
        mago.objeto.test()
        val elfo = Elfo("El Elfo", vivo = true, Carcaj())
        elfo.test()
        elfo.recargarCarcaj(10)
        elfo.objeto.test()
        val hobbit = Hobbit("El Hobbit", vivo = true, Anillo())
        hobbit.test()
        hobbit.ponerseAnillo()
        hobbit.objeto.test()


        salas.forEach {
            it.test();
            it.peligro.test()
        }
    }
}