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
 * Observador concreto: alerta al Coordinador de la Carrera únicamente
 * cuando un alumno supera el límite de inasistencias y queda LIBRE.
 */
public class NotificadorCoordinador implements Observador {

    private final Coordinador coordinador;

    public NotificadorCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

    @Override
    public void actualizar(EventoInscripcion evento) {
        if (evento.getTipo() == TipoEvento.LIMITE_INASISTENCIAS_SUPERADO) {
            System.out.println("   [Alerta al Coordinador " + coordinador.getNombreCompleto() + "] "
                    + evento.getMensaje());
        }
    }
}
