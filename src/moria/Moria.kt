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
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

// Definimos Moria como Object y de esta manera implementamos el singleton
// https://blog.mindorks.com/how-to-create-a-singleton-class-in-kotlin
// https://refactoring.guru/es/design-patterns/singleton
object Moria {
    // Constantes del sistema.
    // Parametrización
    private const val MAX_ENERGIA = 30
    private const val MAX_FLECHAS = 10
    private const val MAX_SALAS = 36
    private const val MAX_SALA_MALIGNO = 10
    private const val MAX_SALA_FLECHAS = 5
    private const val MAX_SALA_ENEMIGOS = 10

    // Condiciones del run
    private const val VIVOS = true
    private const val MUERTOS = false

    // Variables de Moria, con lateint indicamos que las inicializaremos fuera del int o de la declaracion
    // Personajes
    private lateinit var gandalf: Personaje
    private lateinit var legolas: Personaje
    private lateinit var frodo: Personaje

    // Lista de salas
    private var salas = ArrayDeque<Sala>() // mutableListOf

    // Voy a programar la cola FIFO usando extensiín, si nlo quieres hacer por eherencia mira la rama TDA
    // Encolar, siempre añadimos al final de la cola
    private fun ArrayDeque<Sala>.encolar(sala: Sala) {
        this.add(sala)
    }

    // Desencolar añadimos al princpio de la cola
    private fun ArrayDeque<Sala>.desencolar(): Sala {
        return this.removeAt(0)
    }

    // Variables de ejecución
    private lateinit var salaActual: Sala
    private var estado = VIVOS

    // Me gusta definir las cosas en el init para evitar ensuciar el código
    // a diferencia con constructor es que este esta pensado para tareas mas "cargadas" y una vez creado el objeto
    // Le asigna los valores que queramos
    init {
        initPersonajes()
        initSalas()
    }

    // Inicia los personajes
    private fun initPersonajes() {
        // Como vemos estamos realizando una inyección de dependencias usando agragaciones con objetos asbtractos para objeto
        gandalf = Mago("Gandalf", true, Vara(energia = MAX_ENERGIA))
        legolas = Elfo("Legolas", true, Carcaj(cantidad = MAX_FLECHAS))
        frodo = Hobbit(nombre = "Frodo", vivo = true, objeto = Anillo())
    }

    // Inicia la salas
    private fun initSalas() {
        // Como es Fifo añadimos siempre al final
        for (i in 1..MAX_SALAS) {
            when (Random.nextInt(1, 4)) {
                1 -> salas.encolar(Sala(i, Magico(poder = Random.nextInt(1, MAX_SALA_MALIGNO))))
                2 -> salas.encolar(
                    Sala(
                        i,
                        Accion(
                            flechas = Random.nextInt(0, MAX_SALA_FLECHAS),
                            enemigos = Random.nextInt(1, MAX_SALA_ENEMIGOS)
                        )
                    )
                )
                3 -> salas.encolar(Sala(i, Habilidad()))
            }
        }
    }

    // función de ejecución
    fun run() {
        presentacion()
        // mientras haya salas o no hayamos terminado
        while (salas.size >= 1 && estado == VIVOS) {
            // entramos en la sala
            entrarSala()
            // Analizamos el tipo de peligro y con ella actuamos,
            estado = analizarActuar()
        }
        // Imprimimos el informe
        informe()
    }

    private fun analizarActuar(): Boolean {
        // Podemos usar el casting con la variable tipo pero mejor hacemos casting
        return when (this.salaActual.peligro) {
            is Magico -> gandalf.accion(this.salaActual.peligro)
            is Accion -> legolas.accion(this.salaActual.peligro)
            is Habilidad -> frodo.accion(this.salaActual.peligro)
            else -> MUERTOS
        }
    }

    private fun entrarSala() {
        // Eliminamos como la primera porque es una estructura FIFO, 
        this.salaActual = salas.desencolar()
        println("*** Entrando en la sala nº: ${this.salaActual.numero}. Es del tipo: ${this.salaActual.peligro.tipo}")
    }

    private fun presentacion() {
        val dateTime = LocalDateTime.now()
        val momento = dateTime.format(DateTimeFormatter.ofPattern("dd/M/y H:m:ss"))
        val mensaje = "*** ABRIENDO LAS PUERTAS DE MORIA ***"
        println(mensaje)
        println()
        File("moria.txt").appendText("$mensaje el $momento\n")
    }

    private fun informe() {
        val dateTime = LocalDateTime.now()
        val momento = dateTime.format(DateTimeFormatter.ofPattern("dd/M/y H:m:ss"))
        println("--------------")
        var mensaje: String
        if (estado == this.VIVOS) {
            mensaje = "--> NUESTROS HEROES HAS SUPERADO LOS PELIGROS DE MORIA :)\n"
            mensaje += "--> NUEVOS PELIGROS LES AGUARDAN :)\n"
        } else {
            mensaje = "--> NUESTROS HEROES HAN CAIDO EN MORIA :__(\n"
            mensaje += "--> NO HAN PODIDO PASAR DE LA SALA: ${salaActual.numero}\n"
        }
        mensaje += "*** CERRANDO LAS PUERTAS DE MORIA ***"
        println(mensaje)
        File("moria.txt").appendText("$mensaje el $momento\n\n", Charsets.UTF_8)
    }

    // funcion de test
//    fun test() {
//        println("Soy Moria")
//        gandalf.test()
//        gandalf.objeto.test()
//        legolas.test()
//        legolas.objeto.test()
//        frodo.test()
//        frodo.objeto.test()
//
//        val mago = Mago("El Mago", vivo = true, Vara())
//        mago.test()
//        mago.recargarVara(20)
//        mago.objeto.test()
//        val elfo = Elfo("El Elfo", vivo = true, Carcaj())
//        elfo.test()
//        elfo.recargarCarcaj(10)
//        elfo.objeto.test()
//        val hobbit = Hobbit("El Hobbit", vivo = true, Anillo())
//        hobbit.test()
//        hobbit.ponerseAnillo()
//        hobbit.objeto.test()
//
//
//        salas.forEach {
//            it.test()
//            it.peligro.test()
//            println(hobbit.huir())
//        }
//    }
}