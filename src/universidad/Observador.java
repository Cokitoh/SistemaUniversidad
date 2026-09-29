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
 * Patrón Observer - rol Observador.
 * Toda clase interesada en los cambios de una Inscripcion implementa
 * esta interfaz y recibe los eventos mediante {@link #actualizar}.
 */
public interface Observador {

    void actualizar(EventoInscripcion evento);
}
