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

/**
 * Moria es nuestra clase principal. Actúa como controlador
 * Se crea como singleton para asegurar que solo hay una instancia (opcional)
 * Es la fachada de nuestro problema
 * https://refactoring.guru/es/design-patterns/singleton
 * https://blog.mindorks.com/how-to-create-a-singleton-class-in-kotlin
 * https://refactoring.guru/es/design-patterns/facade
 */
object Moria {
    /**
     * Constantes del sistema para tener parametrizado su ejecución y no mezclada en el código
     */
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
    // Personajes, son abstract y luego los iniciamos con el tipo concreto (polimorfismo)
    // es una forma simplificada y reducida de apliacar una factoria
    // https://refactoring.guru/es/design-patterns/factory-method
    private lateinit var gandalf: Personaje
    private lateinit var legolas: Personaje
    private lateinit var frodo: Personaje

    // Lista de salas. Usamos esta clase y no mutableListOf porque es una extensión más óptima para este problema
    // Programamos la cola FIFO usando funciones de extensión para optmizar las llamadas
    // https://kotlinlang.org/docs/reference/extensions.html
    // Si quieres verlo con herencia consulta la rama TDA
    private var salas = ArrayDeque<Sala>()

    /**
     * FIFO. Encolamos al final
     * @receiver ArrayDeque<Sala> Lista de salas
     * @param sala Sala a encolar
     */
    private fun ArrayDeque<Sala>.encolar(sala: Sala) {
        this.add(sala)
    }

    /**
     * FIFO. Desencolamos al principio
     * @receiver ArrayDeque<Sala> lista de salas
     * @return Sala sala a desencolar
     */
    private fun ArrayDeque<Sala>.desencolar(): Sala {
        return this.removeAt(0)
    }

    // Variables de ejecución
    private lateinit var salaActual: Sala
    private var estado = VIVOS

    /**
     *  Me gusta definir las cosas en el init para evitar ensuciar el código
     *  diferencia con constructor es que este esta pensado para tareas mas "cargadas" y una vez creado el objet
     *  Le asigna los valores que queramos
     */
    init {
        initPersonajes()
        initSalas()
    }

    /**
     * Iniciamos los personajes
     */
    private fun initPersonajes() {
        // Como vemos estamos realizando una inyección de dependencias usando agragaciones con objetos asbtractos para objeto
        // de nuevo aplicamos polimorfismo en nuestro intento de hacer una factoria de manera reducida
        // Ademas aplicamos inyección de dependencias para no acoplar el tipo de objetos
        // https://www.arquitecturajava.com/el-patron-de-inyeccion-de-dependencia/
        gandalf = Mago("Gandalf", true, Vara(energia = MAX_ENERGIA))
        legolas = Elfo("Legolas", true, Carcaj(cantidad = MAX_FLECHAS))
        frodo = Hobbit(nombre = "Frodo", vivo = true, objeto = Anillo())
    }

    /**
     * Iniciamos las salas
     */
    private fun initSalas() {
        // Como es Fifo añadimos siempre al final. de nuevo inyectamos la dependencia del peligro
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

    /**
     * Método principal de ejecución
     */
    fun run() {
        // Presentamos
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

    /**
     * Analiza la sala e indica quién debe actuar
     * @return Boolean True si continuar, false si hemos caído en la sala
     */
    private fun analizarActuar(): Boolean {
        // Podemos usar el casting con la variable tipo pero mejor hacemos casting
        return when (this.salaActual.peligro) {
            is Magico -> gandalf.accion(this.salaActual.peligro)
            is Accion -> legolas.accion(this.salaActual.peligro)
            is Habilidad -> frodo.accion(this.salaActual.peligro)
            else -> MUERTOS
        }
    }

    /**
     * Desencolamos una sala de la lista
     */
    private fun entrarSala() {
        // Eliminamos como la primera porque es una estructura FIFO, 
        this.salaActual = salas.desencolar()
        println("*** Entrando en la sala nº: ${this.salaActual.numero}. Es del tipo: ${this.salaActual.peligro.tipo}")
    }

    /**
     * Mensajes de presentación y entradas en el fichero de historias de ejecución
     */
    private fun presentacion() {
        val dateTime = LocalDateTime.now()
        val momento = dateTime.format(DateTimeFormatter.ofPattern("dd/M/y H:m:ss"))
        val mensaje = "*** ABRIENDO LAS PUERTAS DE MORIA ***"
        println(mensaje)
        println()
        File("moria.txt").appendText("$mensaje el $momento\n")
    }

    /**
     * Informe final en pantalla y en el fichero de historias de ejecución
     */
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