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
 * Alumno de la Universidad. Se matricula en Carreras y se inscribe a Materias.
 * Hereda de Persona. El número de legajo se asigna automáticamente.
 */
public class Alumno extends Persona {

    private static int contadorLegajo = 1000;

    private int legajo;
    private List<Carrera> carrerasMatriculadas;
    private List<Inscripcion> inscripciones;

    public Alumno(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
        this.legajo = contadorLegajo++;
        this.carrerasMatriculadas = new ArrayList<>();
        this.inscripciones = new ArrayList<>();
    }

    public int getLegajo() {
        return legajo;
    }

    public List<Carrera> getCarrerasMatriculadas() {
        return Collections.unmodifiableList(carrerasMatriculadas);
    }

    public List<Inscripcion> getInscripciones() {
        return Collections.unmodifiableList(inscripciones);
    }

    public void agregarCarreraMatriculada(Carrera carrera) {
        if (!carrerasMatriculadas.contains(carrera)) {
            carrerasMatriculadas.add(carrera);
        }
    }

    public void agregarInscripcion(Inscripcion inscripcion) {
        inscripciones.add(inscripcion);
    }

    public boolean estaMatriculadoEn(Carrera carrera) {
        return carrerasMatriculadas.contains(carrera);
    }

    @Override
    public String toString() {
        return getNombreCompleto() + " (Legajo: " + legajo + " - DNI: " + dni + ")";
    }
}
