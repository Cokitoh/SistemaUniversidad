/**
 * UNIVERSIDAD DE LA CUENCA DEL PLATA
 * INGENIERIA EN SISTEMAS DE INFORMACION
 * MATERIA: PARADIGMAS Y LENGUAJES DE PROGRAMACION II
 * ACTIVIDAD DE EVALUACION A2 (INDIVIDUAL) - Extension de la solucion grupal AE1
 * ALUMNO: BENITEZ MAXIMO
 * CURSO: 2° "B"
 */
package universidad;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Observador concreto: guarda un historial de todos los eventos
 * de todas las inscripciones a las que está suscripto.
 */
public class RegistroAuditoria implements Observador {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final List<String> registros = new ArrayList<>();

    @Override
    public void actualizar(EventoInscripcion evento) {
        Inscripcion i = evento.getInscripcion();
        registros.add("[" + evento.getFechaHora().format(FORMATO) + "] " + evento.getTipo()
                + " | " + i.getAlumno().getNombreCompleto()
                + " | " + i.getMateria().getNombre()
                + " (" + i.getMateria().getCarrera().getNombre() + ")"
                + " | " + evento.getMensaje());
    }

    public List<String> getRegistros() {
        return Collections.unmodifiableList(registros);
    }

    public void mostrar() {
        System.out.println("Registro de auditoría:");
        if (registros.isEmpty()) {
            System.out.println("  Todavía no hay eventos registrados.");
        }
        for (String r : registros) {
            System.out.println("  " + r);
        }
    }
}
