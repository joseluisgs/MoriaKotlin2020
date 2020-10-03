package moria.personajes

import moria.objetos.Objeto
import moria.peligros.Peligro
import moria.utils.Utils

/**
 * Clase personaje, punto de partida de la herencia. Como no instanciamos es asbtract y no open
 * @property nombre String nombre del personaje
 * @property vivo Boolean si está vivo (true) o muerto (false)
 * @property objeto Objeto Objeto que porta
 * @property PROB_HUIR Int Probabilidad de huir parametrizable
 * @constructor
 */
abstract class Personaje(val nombre: String, var vivo: Boolean = true, var objeto: Objeto) {
    private val PROB_HUIR = 80

    /**
     * Acción principal de Personaje
     * @param peligro Peligro tipo de peligro
     * @return Boolean True->> Lo consigue. Fase ->> Fracaso
     */
    abstract fun accion(peligro: Peligro): Boolean

    /**
     * Indica si huye o no el personaje
     * @return Boolean True->> Lo consigue. Fase ->> Fracaso
     */
    fun huir(): Boolean {
        println("--> Intentando huir :(")
        val sorteo = Utils.probabilidad(PROB_HUIR, 100)
        return if (sorteo) {
            println("--> Hemos tenido suerte y podemos huir :)")
            true
        } else {
            println("--> No hemos tenido suerte y no podemos huir :_(")
            vivo = false
            false
        }
    }

    /**
     * Indica si el peligro se ha superado
     * @return Boolean True->> Lo consigue. Fase ->> Fracaso
     */
    fun peligroSuperado(): Boolean {
        println("--> $nombre ha superado el peligro. ¡Continuamos! :)")
        return true
    }

    // Función de test
//    fun test() {
//        println("Soy: $nombre ¿Estoy vivo?: $vivo y mi objeto es: ${objeto.tipo}")
//    }

}