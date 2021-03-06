package moria.personajes

import moria.objetos.Objeto
import moria.objetos.Vara
import moria.peligros.Magico
import moria.peligros.Peligro
import moria.utils.Utils
import kotlin.random.Random

/**
 * Clase Mago
 * Hereda de Personaje
 * Implementa SoyMago
 * @constructor
 */
class Mago(nombre: String, vivo: Boolean, objeto: Objeto) : Personaje(nombre, vivo, objeto), SoyMago {

    /**
     * Reccarga la vara
     * @param energia Int energía de recarga
     */
    override fun recargarVara(energia: Int) {
        (this.objeto as Vara).energia -= energia
    }

    /**
     * Muestra el poder de la vara
     * @return Int Valor del poder de la vara
     */
    override fun poderVara(): Int {
        return (this.objeto as Vara).energia
    }

    /**
     * Indica si ha ganado su reto
     * @param limite Int limite de prbabilidad para superar
     * @return Boolean true lo ha conseguido, false, no lo ha conseguido
     */
    private fun ganar(limite: Int): Boolean {
        println("--> $nombre intenta ganar al peligro :(")
        val ganar = Utils.probabilidad(limite, 100)
        // Si ganamos peligro superado
        return if (ganar) this.peligroSuperado()
        // Si no huimos
        else {
            println("--> $nombre no ha superado el peligro :(")
            this.huir()
        }
    }

    /**
     * Acción principal de Mago
     * @param peligro Peligro tipo de peligro
     * @return Boolean True->> Lo consigue. Fase ->> Fracaso
     */
    override fun accion(peligro: Peligro): Boolean {
        println("--> $nombre entra en acción")
        // Recargamos la vara entre 1 y 10
        println("--> $nombre recarga la vara")
        this.recargarVara(Random.nextInt(1, 11))
        // Voy a usar un when en vez de in if else
        when {
            // Si el poder es mayor
            this.poderVara() > (peligro as Magico).poder -> {
                println("--> la vara es más poderosa que el peligro :)")
                return this.peligroSuperado()
            }
            // Si es igual
            this.poderVara() == peligro.poder -> {
                // Si es igual ganamos en el 60%
                println("--> la vara es tan poderosa como el peligro :|")
                return this.ganar(60)

            }
            else -> {
                // Si es menor
                println("--> la vara es menos poderosa que el peligro :(")
                return this.ganar(30)
            }
        }
    }

}