package moria.personajes

import moria.objetos.Anillo
import moria.objetos.Objeto
import moria.utils.Utils

class Hobbit(nombre: String, vivo: Boolean, objeto: Objeto) : Personaje(nombre, vivo, objeto), SoyHobbit {

    override fun ponerseAnillo() {
        (this.objeto as Anillo).puesto = true
    }

    override fun quitarseAnillo() {
        (this.objeto as Anillo).puesto = false
    }

    fun decidir(): Boolean {
        return Utils.probabilidad(50, 100)
    }

    fun superarPeligro(limite: Int): Boolean {
        return Utils.probabilidad(limite, 100)
    }

}