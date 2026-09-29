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
 * Observador concreto: informa al Alumno de todo lo que sucede
 * en su Inscripcion (por consola, como en el resto del sistema).
 */
public class NotificadorAlumno implements Observador {

    @Override
    public void actualizar(EventoInscripcion evento) {
        Alumno alumno = evento.getInscripcion().getAlumno();
        System.out.println("   [Aviso a " + alumno.getNombreCompleto() + "] " + evento.getMensaje());
    }
}
