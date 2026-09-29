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
 * Carrera universitaria. Compuesta por Materias (todo/parte) y con un
 * Coordinador asociado. Los Alumnos se matriculan en ella.
 */
public class Carrera {

    private String nombre;
    private int duracionAnios;
    private Coordinador coordinador;
    private double precioInscripcion;
    private double precioCuota;
    private List<Materia> materias;
    private List<Alumno> alumnosMatriculados;

    public Carrera(String nombre, int duracionAnios, Coordinador coordinador,
                    double precioInscripcion, double precioCuota) {
        this.nombre = nombre;
        this.duracionAnios = duracionAnios;
        this.coordinador = coordinador;
        this.precioInscripcion = precioInscripcion;
        this.precioCuota = precioCuota;
        this.materias = new ArrayList<>();
        this.alumnosMatriculados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracionAnios() {
        return duracionAnios;
    }

    public Coordinador getCoordinador() {
        return coordinador;
    }

    public double getPrecioInscripcion() {
        return precioInscripcion;
    }

    public double getPrecioCuota() {
        return precioCuota;
    }

    public List<Materia> getMaterias() {
        return Collections.unmodifiableList(materias);
    }

    public List<Alumno> getAlumnosMatriculados() {
        return Collections.unmodifiableList(alumnosMatriculados);
    }

    public void agregarMateria(Materia materia) {
        materias.add(materia);
    }

    /**
     * Matricula un alumno en la carrera si aún no estaba matriculado.
     */
    public boolean matricularAlumno(Alumno alumno) {
        if (alumnosMatriculados.contains(alumno)) {
            System.out.println("El alumno ya está matriculado en esta carrera.");
            return false;
        }
        alumnosMatriculados.add(alumno);
        alumno.agregarCarreraMatriculada(this);
        return true;
    }

    public boolean tieneMatriculado(Alumno alumno) {
        return alumnosMatriculados.contains(alumno);
    }

    public Materia buscarMateriaPorNombre(String nombreMateria) {
        for (Materia m : materias) {
            if (m.getNombre().equalsIgnoreCase(nombreMateria)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return nombre + " (Duración: " + duracionAnios + " años - Coordinador: "
                + coordinador.getNombreCompleto() + ")";
    }
}
