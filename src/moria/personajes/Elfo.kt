package moria.personajes

import moria.objetos.Carcaj
import moria.objetos.Objeto

class Elfo(nombre: String, vivo: Boolean, objeto: Objeto) : Personaje(nombre, vivo, objeto), SoyElfo {

    override fun lanzarFlecha() {
        (this.objeto as Carcaj).cantidad--
    }

    override fun recargarCarcaj(flechas: Int) {
        (this.objeto as Carcaj).cantidad += flechas
    }

    override fun accion(): Boolean {
        return true
    }

}