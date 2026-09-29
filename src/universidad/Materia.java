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
 * Materia perteneciente a un curso/año de una Carrera, dictada por un Profesor.
 * Define además sus correlativas, su cupo máximo y el límite de inasistencias
 * permitido antes de quedar LIBRE.
 */
public class Materia {

    public static final int LIMITE_INASISTENCIAS_POR_DEFECTO = 5;
    public static final int CUPO_POR_DEFECTO = 30;

    private String nombre;
    private int curso;
    private String cuatrimestre;
    private Profesor profesor;
    private Carrera carrera;
    private List<Inscripcion> inscripciones;
    private List<Materia> correlativas;
    private int limiteInasistencias;
    private int cupoMaximo;

    public Materia(String nombre, int curso, String cuatrimestre, Profesor profesor, Carrera carrera) {
        this.nombre = nombre;
        this.curso = curso;
        this.cuatrimestre = cuatrimestre;
        this.profesor = profesor;
        this.carrera = carrera;
        this.inscripciones = new ArrayList<>();
        this.correlativas = new ArrayList<>();
        this.limiteInasistencias = LIMITE_INASISTENCIAS_POR_DEFECTO;
        this.cupoMaximo = CUPO_POR_DEFECTO;
        profesor.agregarMateria(this);
    }

    public String getNombre() {
        return nombre;
    }

    public int getCurso() {
        return curso;
    }

    public String getCuatrimestre() {
        return cuatrimestre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public List<Inscripcion> getInscripciones() {
        return Collections.unmodifiableList(inscripciones);
    }

    public List<Materia> getCorrelativas() {
        return Collections.unmodifiableList(correlativas);
    }

    public int getLimiteInasistencias() {
        return limiteInasistencias;
    }

    public void setLimiteInasistencias(int limiteInasistencias) {
        if (limiteInasistencias < 0) {
            throw new IllegalArgumentException("El límite de inasistencias no puede ser negativo.");
        }
        this.limiteInasistencias = limiteInasistencias;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        if (cupoMaximo < 1) {
            throw new IllegalArgumentException("El cupo máximo debe ser al menos 1.");
        }
        this.cupoMaximo = cupoMaximo;
    }

    public void agregarCorrelativa(Materia materia) {
        if (materia != this && !correlativas.contains(materia)) {
            correlativas.add(materia);
        }
    }

    /**
     * Indica si hay lugar para un alumno más entre quienes están cursando.
     */
    public boolean hayCupo() {
        return getAlumnosCursando().size() < cupoMaximo;
    }

    /**
     * Crea y registra la inscripción de un alumno. Las reglas de negocio
     * (matrícula, duplicados, correlativas y cupo) se validan antes, en la
     * cadena de ValidadorInscripcion que administra el Sistema.
     */
    public Inscripcion inscribirAlumno(Alumno alumno) {
        if (buscarInscripcion(alumno) != null) {
            throw new IllegalStateException("El alumno ya está inscripto en esta materia.");
        }
        Inscripcion inscripcion = new Inscripcion(alumno, this);
        inscripciones.add(inscripcion);
        alumno.agregarInscripcion(inscripcion);
        return inscripcion;
    }

    public Inscripcion buscarInscripcion(Alumno alumno) {
        for (Inscripcion i : inscripciones) {
            if (i.getAlumno().equals(alumno)) {
                return i;
            }
        }
        return null;
    }

    public boolean finalizarCursadoDeAlumno(Alumno alumno, EstadoCursado estadoFinal) {
        Inscripcion inscripcion = buscarInscripcion(alumno);
        if (inscripcion == null) {
            System.out.println("El alumno no está inscripto en esta materia.");
            return false;
        }
        return inscripcion.finalizarCursado(estadoFinal);
    }

    public boolean registrarInasistenciaDeAlumno(Alumno alumno) {
        Inscripcion inscripcion = buscarInscripcion(alumno);
        if (inscripcion == null) {
            System.out.println("El alumno no está inscripto en esta materia.");
            return false;
        }
        return inscripcion.sumarInasistencia();
    }

    public List<Inscripcion> getAlumnosCursando() {
        List<Inscripcion> resultado = new ArrayList<>();
        for (Inscripcion i : inscripciones) {
            if (i.estaCursando()) {
                resultado.add(i);
            }
        }
        return resultado;
    }

    public List<Inscripcion> getAlumnosFinalizados() {
        List<Inscripcion> resultado = new ArrayList<>();
        for (Inscripcion i : inscripciones) {
            if (!i.estaCursando()) {
                resultado.add(i);
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        return nombre + " (Curso: " + curso + " - Cuatrimestre: " + cuatrimestre
                + " - Profesor: " + profesor.getNombreCompleto() + ")";
    }
}
