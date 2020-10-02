package moria.personajes

import moria.objetos.Anillo
import moria.objetos.Objeto
import moria.peligros.Peligro
import moria.utils.Utils

class Hobbit(nombre: String, vivo: Boolean, objeto: Objeto) : Personaje(nombre, vivo, objeto), SoyHobbit {

    override fun ponerseAnillo() {
        println("--> $nombre Me pongo el anillo")
        (this.objeto as Anillo).puesto = true
    }

    override fun quitarseAnillo() {
        println("--> $nombre Me quito el anillo")
        (this.objeto as Anillo).puesto = false
    }

    private fun decidir(): Boolean {
        println("--> $nombre toma una decisión")
        return Utils.probabilidad(50, 100)
    }

    private fun superarPeligroHabilidad(limite: Int): Boolean {
        val superar = Utils.probabilidad(limite, 100)
        if (superar) {
            this.quitarseAnillo()
            return this.peligroSuperado()
        } else {
            println("--> $nombre no ha superado el peligro :(")
            return this.huir()
        }
    }

    override fun accion(peligro: Peligro): Boolean {
        println("--> $nombre entra en acción")
        // Tomamos una decisión (50%)
        val decision = this.decidir()
        if (decision) {
            println("--> $nombre decide no ponerse el anillo")
            // Nos ponemos el anillo
            this.ponerseAnillo()
            // Lo superamos el 90% de los casos
            return this.superarPeligroHabilidad(90)
        } else {
            println("--> $nombre decide no ponerse el anillo")
            // Solo lo superamos en el 20% de los casos
            return this.superarPeligroHabilidad(20)
        }
    }

}