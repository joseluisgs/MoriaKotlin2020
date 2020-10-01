package moria.personajes

import moria.objetos.Anillo
import moria.objetos.Objeto
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

    fun decidir(): Boolean {
        println("--> $nombre toma una decisión")
        return Utils.probabilidad(50, 100)
    }

    fun superarPeligroHabilidad(limite: Int): Boolean {
        val superar = Utils.probabilidad(limite, 100)
        if (superar) {
            this.quitarseAnillo()
            return this.peligroSuperado()
        } else {
            println("--> $nombre no ha superado el peligro :(")
            return this.huir()
        }
    }

}