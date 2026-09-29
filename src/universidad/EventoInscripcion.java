/**
 * UNIVERSIDAD DE LA CUENCA DEL PLATA
 * INGENIERIA EN SISTEMAS DE INFORMACION
 * MATERIA: PARADIGMAS Y LENGUAJES DE PROGRAMACION II
 * ACTIVIDAD DE EVALUACION A2 (INDIVIDUAL) - Extension de la solucion grupal AE1
 * ALUMNO: BENITEZ MAXIMO
 * CURSO: 2° "B"
 */
package universidad;

import java.time.LocalDateTime;

/**
 * Evento inmutable que describe algo que ocurrió en una Inscripcion.
 * Es el "mensaje" que el sujeto envía a cada Observador.
 */
public class EventoInscripcion {

    private final TipoEvento tipo;
    private final Inscripcion inscripcion;
    private final String mensaje;
    private final LocalDateTime fechaHora;

    public EventoInscripcion(TipoEvento tipo, Inscripcion inscripcion, String mensaje) {
        this.tipo = tipo;
        this.inscripcion = inscripcion;
        this.mensaje = mensaje;
        this.fechaHora = LocalDateTime.now();
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
}
