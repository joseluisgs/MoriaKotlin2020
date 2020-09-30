package moria.personajes

import moria.objetos.Anillo
import moria.objetos.Objeto

class Hobbit(nombre: String, vivo: Boolean, objeto: Objeto) : Personaje(nombre, vivo, objeto), SoyHobbit {

    override fun ponerseAnillo() {
        (this.objeto as Anillo).puesto = true
    }

    override fun quitarseAnillo() {
        (this.objeto as Anillo).puesto = false
    }

}