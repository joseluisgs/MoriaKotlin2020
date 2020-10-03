package moria.personajes

/**
 * Interfaz de acciones de Mago
 */
interface SoyMago {

    /**
     * Recarga la vara
     * @param energia Int valor de recargar la vara
     */
    fun recargarVara(energia: Int)

    /**
     * indica el poder de la vara
     * @return Int Valor del poder de la vara
     */
    fun poderVara(): Int

}