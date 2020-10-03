package moria.personajes

import moria.objetos.Carcaj
import moria.objetos.Objeto
import moria.peligros.Accion
import moria.peligros.Peligro

class Elfo(nombre: String, vivo: Boolean, objeto: Objeto) : Personaje(nombre, vivo, objeto), SoyElfo {

    override fun lanzarFlecha() {
        (this.objeto as Carcaj).cantidad--
    }

    override fun recargarCarcaj(flechas: Int) {
        (this.objeto as Carcaj).cantidad += flechas
    }

    override fun accion(peligro: Peligro): Boolean {
        println("--> $nombre entra en acción")
        // Tenemos flechas (le restamos a los enemigos
        val estado = ((this.objeto as Carcaj).cantidad) - ((peligro as Accion).enemigos)
        println("Mis flechas: ${(this.objeto as Carcaj).cantidad} - Mis enemigos ${peligro.enemigos}")
        if (estado >= 0) {
            println("--> Tenemos más flechas que enemigos :)")
            // Matamos
            println("--> $nombre dispara a los enemigos")
            this.matarEnemigos(peligro.enemigos)
            // recogemos flechas
            this.recogerFlechas(peligro.flechas)
            // println ("Mis flechas: ${(this.objeto as Carcaj).cantidad}")
            return this.peligroSuperado()

        } else {
            // No tenemos suficientes flechas
            println("--> No tenemos suficiente flechas. Los enemigos nos superan :(")
            // matamos a los enemigos con todas
            matarEnemigos((this.objeto as Carcaj).cantidad)
            return if (this.huir()) {
                // recogemos las flechas
                this.recogerFlechas(peligro.flechas)
                // println ("Mis flechas: ${(this.objeto as Carcaj).cantidad}")
                true
            } else false
        }
    }

    private fun matarEnemigos(disparos: Int) {
        for (i in 1..disparos) {
            this.lanzarFlecha()
        }
    }


    private fun recogerFlechas(flechas: Int) {
        println("--> $nombre recoge $flechas flechas de la sala")
        this.recargarCarcaj(flechas)
    }

}