/**
 * UNIVERSIDAD DE LA CUENCA DEL PLATA
 * INGENIERIA EN SISTEMAS DE INFORMACION
 * MATERIA: PARADIGMAS Y LENGUAJES DE PROGRAMACION II
 * ACTIVIDAD DE EVALUACION A2 (INDIVIDUAL) - Extension de la solucion grupal AE1
 * ALUMNO: BENITEZ MAXIMO
 * CURSO: 2° "B"
 */
package universidad;

/**
 * Patrón Observer - rol Sujeto.
 * Define cómo se suscriben, se dan de baja y se notifican los Observadores.
 * Se usa una interfaz propia porque java.util.Observable está deprecado.
 */
public interface Sujeto {

    void agregarObservador(Observador observador);

    void quitarObservador(Observador observador);

    void notificarObservadores(EventoInscripcion evento);
}
