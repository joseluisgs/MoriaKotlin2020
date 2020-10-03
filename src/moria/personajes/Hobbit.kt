package moria.personajes

import moria.objetos.Anillo
import moria.objetos.Objeto
import moria.peligros.Peligro
import moria.utils.Utils

/**
 * Clase Hobbit
 * Hereda de Personaje
 * Implementa SoyHobbit
 * @constructor
 */
class Hobbit(nombre: String, vivo: Boolean, objeto: Objeto) : Personaje(nombre, vivo, objeto), SoyHobbit {

    /**
     * Se pone el anillo
     */
    override fun ponerseAnillo() {
        println("--> $nombre Me pongo el anillo")
        (this.objeto as Anillo).puesto = true
    }

    /**
     * Se quita el anillo
     */
    override fun quitarseAnillo() {
        println("--> $nombre Me quito el anillo")
        (this.objeto as Anillo).puesto = false
    }

    /**
     * Decide que hacer
     * @return Boolean tipo de decision
     */
    private fun decidir(): Boolean {
        println("--> $nombre toma una decisión")
        return Utils.probabilidad(50, 100)
    }

    /**
     * Indica si ha superado el peligro de habilidad
     * @param limite Int límite para la superación
     * @return Boolean Verdadero o falso
     */
    private fun superarPeligroHabilidad(limite: Int): Boolean {
        val superar = Utils.probabilidad(limite, 100)
        return if (superar) {
            this.quitarseAnillo()
            this.peligroSuperado()
        } else {
            println("--> $nombre no ha superado el peligro :(")
            this.huir()
        }
    }

    /**
     * Acción principal de Hobbit
     * @param peligro Peligro tipo de peligro
     * @return Boolean True->> Lo consigue. Fase ->> Fracaso
     */
    override fun accion(peligro: Peligro): Boolean {
        println("--> $nombre entra en acción")
        // Tomamos una decisión (50%)
        val decision = this.decidir()
        return if (decision) {
            println("--> $nombre decide no ponerse el anillo")
            // Nos ponemos el anillo
            this.ponerseAnillo()
            // Lo superamos el 90% de los casos
            this.superarPeligroHabilidad(90)
        } else {
            println("--> $nombre decide no ponerse el anillo")
            // Solo lo superamos en el 20% de los casos
            this.superarPeligroHabilidad(20)
        }
    }

}