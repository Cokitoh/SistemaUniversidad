/**
 * UNIVERSIDAD DE LA CUENCA DEL PLATA
 * INGENIERIA EN SISTEMAS DE INFORMACION
 * MATERIA: PARADIGMAS Y LENGUAJES DE PROGRAMACION II
 * ACTIVIDAD DE EVALUACION A2 (INDIVIDUAL) - Extension de la solucion grupal AE1
 * ALUMNO: BENITEZ MAXIMO
 * CURSO: 2° "B"
 */
package universidad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Profesor. Puede dictar más de una Materia, incluso en distintas Carreras.
 * Hereda de Persona.
 */
public class Profesor extends Persona {

    private List<Materia> materiasQueDicta;

    public Profesor(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
        this.materiasQueDicta = new ArrayList<>();
    }

    public List<Materia> getMateriasQueDicta() {
        return Collections.unmodifiableList(materiasQueDicta);
    }

    public void agregarMateria(Materia materia) {
        if (!materiasQueDicta.contains(materia)) {
            materiasQueDicta.add(materia);
        }
    }

    /**
     * El profesor carga la situación final de un alumno en una de sus materias.
     */
    public boolean cargarSituacionFinal(Materia materia, Alumno alumno, EstadoCursado estadoFinal) {
        if (!materiasQueDicta.contains(materia)) {
            System.out.println("Este profesor no dicta la materia indicada.");
            return false;
        }
        return materia.finalizarCursadoDeAlumno(alumno, estadoFinal);
    }

    /**
     * El profesor registra una falta (inasistencia) de un alumno en una de sus materias.
     */
    public boolean registrarInasistencia(Materia materia, Alumno alumno) {
        if (!materiasQueDicta.contains(materia)) {
            System.out.println("Este profesor no dicta la materia indicada.");
            return false;
        }
        return materia.registrarInasistenciaDeAlumno(alumno);
    }

    @Override
    public String toString() {
        return "Profesor: " + super.toString();
    }
}
