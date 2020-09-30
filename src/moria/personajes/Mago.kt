package moria.personajes

import moria.objetos.Objeto
import moria.objetos.Vara

class Mago(nombre: String, vivo: Boolean, objeto: Objeto) : Personaje(nombre, vivo, objeto), SoyMago {

    override fun recargarVara(energia: Int) {
        (this.objeto as Vara).energia = energia
    }

    override fun poderVara(): Int {
        return (this.objeto as Vara).energia
    }

}