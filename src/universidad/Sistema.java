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
 * Clase que centraliza el sistema: contiene las Carreras y los Alumnos,
 * inicializa los datos requeridos por la consigna original (2 Carreras con su
 * Coordinador, 2 Materias por curso para cada Carrera con su Profesor) y es el
 * único punto de entrada que usa el menú (Main) para operar.
 *
 * Integra los dos patrones de la A2:
 *  - Chain of Responsibility: cadena de ValidadorInscripcion para inscribirse.
 *  - Observer: suscribe observadores a cada Inscripcion nueva.
 */
public class Sistema {

    private List<Carrera> carreras;
    private List<Alumno> alumnos;
    private ValidadorInscripcion cadenaInscripcion;
    private RegistroAuditoria registroAuditoria;

    public Sistema() {
        this.carreras = new ArrayList<>();
        this.alumnos = new ArrayList<>();
        this.registroAuditoria = new RegistroAuditoria();
        this.cadenaInscripcion = construirCadenaDeInscripcion();
        inicializarDatos();
    }

    /**
     * Arma la cadena de reglas de inscripción. El orden va de la regla más
     * barata/general a la más específica: matrícula, duplicada, correlativas, cupo.
     */
    private ValidadorInscripcion construirCadenaDeInscripcion() {
        ValidadorInscripcion primero = new ValidadorMatricula();
        primero.setSiguiente(new ValidadorInscripcionDuplicada())
               .setSiguiente(new ValidadorCorrelativas())
               .setSiguiente(new ValidadorCupo());
        return primero;
    }

    /**
     * Carga los datos iniciales del sistema: 2 Carreras con su Coordinador,
     * y 2 Materias por curso (1er y 2do año) para cada Carrera, con su
     * Profesor correspondiente. Un mismo Profesor puede dictar en más de
     * una Carrera (ej. Matemática).
     */
    private void inicializarDatos() {
        // Coordinadores
        Coordinador coordSistemas = new Coordinador("Laura", "Gómez", "20111222");
        Coordinador coordContador = new Coordinador("Marcos", "Ibáñez", "21333444");

        // Carreras
        Carrera ingSistemas = new Carrera("Ingeniería en Sistemas de Información", 5,
                coordSistemas, 50000.0, 80000.0);
        Carrera contadorPublico = new Carrera("Contador Público", 5,
                coordContador, 45000.0, 70000.0);

        carreras.add(ingSistemas);
        carreras.add(contadorPublico);

        // Profesores
        Profesor profMatematica = new Profesor("Carlos", "Fernández", "25555666");
        Profesor profProgramacion = new Profesor("Ana", "Torres", "26777888");
        Profesor profContabilidad = new Profesor("Silvia", "Rojas", "27999000");
        Profesor profEconomia = new Profesor("Diego", "Suárez", "28111222");

        cargarMaterias(ingSistemas, contadorPublico, profMatematica,
                profProgramacion, profContabilidad, profEconomia);
    }

    /**
     * Deja armadas las Materias de cada Carrera (2 por curso, curso 1 y curso 2)
     * y sus correlatividades: cada materia de 2do año que continúa una de 1ro
     * la tiene como correlativa.
     */
    private void cargarMaterias(Carrera ingSistemas, Carrera contadorPublico,
                                 Profesor profMatematica, Profesor profProgramacion,
                                 Profesor profContabilidad, Profesor profEconomia) {
        // Ingeniería en Sistemas - Curso 1
        Materia matematica1Sis = agregarMateriaACarrera("Matemática I", 1, "1C", profMatematica, ingSistemas);
        Materia programacion1 = agregarMateriaACarrera("Programación I", 1, "1C", profProgramacion, ingSistemas);
        // Ingeniería en Sistemas - Curso 2
        Materia matematica2Sis = agregarMateriaACarrera("Matemática II", 2, "1C", profMatematica, ingSistemas);
        Materia programacion2 = agregarMateriaACarrera("Programación II", 2, "2C", profProgramacion, ingSistemas);
        matematica2Sis.agregarCorrelativa(matematica1Sis);
        programacion2.agregarCorrelativa(programacion1);

        // Contador Público - Curso 1
        agregarMateriaACarrera("Matemática I", 1, "1C", profMatematica, contadorPublico);
        Materia contabilidad1 = agregarMateriaACarrera("Contabilidad I", 1, "1C", profContabilidad, contadorPublico);
        // Contador Público - Curso 2
        agregarMateriaACarrera("Economía I", 2, "1C", profEconomia, contadorPublico);
        Materia contabilidad2 = agregarMateriaACarrera("Contabilidad II", 2, "2C", profContabilidad, contadorPublico);
        contabilidad2.agregarCorrelativa(contabilidad1);
    }

    private Materia agregarMateriaACarrera(String nombre, int curso, String cuatrimestre,
                                            Profesor profesor, Carrera carrera) {
        Materia materia = new Materia(nombre, curso, cuatrimestre, profesor, carrera);
        carrera.agregarMateria(materia);
        return materia;
    }

    // ---------------------------------------------------------------
    // Consultas
    // ---------------------------------------------------------------

    public List<Carrera> getCarreras() {
        return Collections.unmodifiableList(carreras);
    }

    public List<Alumno> getAlumnos() {
        return Collections.unmodifiableList(alumnos);
    }

    public RegistroAuditoria getRegistroAuditoria() {
        return registroAuditoria;
    }

    public Carrera buscarCarreraPorNombre(String nombre) {
        for (Carrera c : carreras) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    public Alumno buscarAlumnoPorDni(String dni) {
        for (Alumno a : alumnos) {
            if (a.getDni().equals(dni)) {
                return a;
            }
        }
        return null;
    }

    // ---------------------------------------------------------------
    // Operaciones del menú
    // ---------------------------------------------------------------

    /**
     * Da de alta un alumno nuevo (si el DNI no existe todavía) y lo matricula
     * en la carrera indicada.
     */
    public Alumno matricularAlumno(String nombre, String apellido, String dni, Carrera carrera) {
        Alumno alumno = buscarAlumnoPorDni(dni);
        if (alumno == null) {
            alumno = new Alumno(nombre, apellido, dni);
            alumnos.add(alumno);
        }
        carrera.matricularAlumno(alumno);
        return alumno;
    }

    /**
     * Inscribe a un alumno a una materia. Primero recorre la cadena de
     * validaciones; si todas se cumplen crea la inscripción, suscribe los
     * observadores y publica la confirmación.
     */
    public boolean inscribirAlumnoAMateria(Alumno alumno, Materia materia) {
        ResultadoValidacion resultado = cadenaInscripcion.validar(alumno, materia);
        if (!resultado.esValido()) {
            System.out.println(resultado.getMotivo());
            return false;
        }

        Inscripcion inscripcion = materia.inscribirAlumno(alumno);
        inscripcion.agregarObservador(new NotificadorAlumno());
        inscripcion.agregarObservador(new NotificadorCoordinador(materia.getCarrera().getCoordinador()));
        inscripcion.agregarObservador(registroAuditoria);
        inscripcion.confirmar();
        return true;
    }

    /**
     * El Profesor de la materia carga la situación final de un alumno.
     */
    public boolean cargarSituacionFinal(Materia materia, Alumno alumno, EstadoCursado estadoFinal) {
        return materia.getProfesor().cargarSituacionFinal(materia, alumno, estadoFinal);
    }

    /**
     * El Profesor de la materia registra una inasistencia de un alumno.
     */
    public boolean registrarInasistencia(Materia materia, Alumno alumno) {
        return materia.getProfesor().registrarInasistencia(materia, alumno);
    }
}
