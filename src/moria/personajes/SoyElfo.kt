package moria.personajes

/**
 * Interfaz de acciones de Elefo
 */
interface SoyElfo {
    /**
     * Lanza una flecha
     */
    fun lanzarFlecha()

    /**
     * Recarga el carcaj
     * @param flechas Int número de flechas
     */
    fun recargarCarcaj(flechas: Int)
}