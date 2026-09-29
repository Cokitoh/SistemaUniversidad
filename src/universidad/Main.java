/**
 * UNIVERSIDAD DE LA CUENCA DEL PLATA
 * INGENIERIA EN SISTEMAS DE INFORMACION
 * MATERIA: PARADIGMAS Y LENGUAJES DE PROGRAMACION II
 * ACTIVIDAD DE EVALUACION A2 (INDIVIDUAL) - Extension de la solucion grupal AE1
 * ALUMNO: BENITEZ MAXIMO
 * CURSO: 2° "B"
 */
package universidad;

import java.util.List;
import java.util.Scanner;

/**
 * Clase principal. Contiene el Menú de opciones solicitado por la consigna:
 * matricular alumnos, inscribirlos a materias, cargar la situación final,
 * registrar asistencia y mostrar alumnos por carrera y materia. La A2 agrega
 * la consulta de inscripciones de un alumno y del registro de auditoría.
 * Toda operación pasa por Sistema; Main sólo se ocupa de la entrada/salida.
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Sistema sistema = new Sistema();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1:
                    matricularAlumno();
                    break;
                case 2:
                    inscribirAlumnoAMateria();
                    break;
                case 3:
                    cargarSituacionFinal();
                    break;
                case 4:
                    registrarAsistencia();
                    break;
                case 5:
                    mostrarAlumnosPorCarreraYMateria();
                    break;
                case 6:
                    mostrarInscripcionesDeAlumno();
                    break;
                case 7:
                    sistema.getRegistroAuditoria().mostrar();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
            System.out.println();
        } while (opcion != 0);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("===== SISTEMA DE GESTIÓN UNIVERSITARIA =====");
        System.out.println("1. Matricular Alumno a una Carrera");
        System.out.println("2. Inscribir Alumno a una Materia");
        System.out.println("3. Cargar situación final de un Alumno en una Materia");
        System.out.println("4. Registrar inasistencia de un Alumno en una Materia");
        System.out.println("5. Mostrar Alumnos de una Carrera y Materia");
        System.out.println("6. Ver inscripciones de un Alumno");
        System.out.println("7. Ver registro de auditoría");
        System.out.println("0. Salir");
    }

    // ---------------------------------------------------------------
    // Opción 1
    // ---------------------------------------------------------------
    private static void matricularAlumno() {
        Carrera carrera = elegirCarrera();
        if (carrera == null) {
            return;
        }

        System.out.print("Ingrese DNI del Alumno: ");
        String dni = sc.nextLine();
        Alumno existente = sistema.buscarAlumnoPorDni(dni);

        String nombre;
        String apellido;
        if (existente != null) {
            nombre = existente.getNombre();
            apellido = existente.getApellido();
            System.out.println("Alumno ya registrado: " + existente);
        } else {
            System.out.print("Ingrese Nombre: ");
            nombre = sc.nextLine();
            System.out.print("Ingrese Apellido: ");
            apellido = sc.nextLine();
        }

        Alumno alumno = sistema.matricularAlumno(nombre, apellido, dni, carrera);
        System.out.println("Alumno " + alumno + " matriculado en \"" + carrera.getNombre() + "\".");
    }

    // ---------------------------------------------------------------
    // Opción 2
    // ---------------------------------------------------------------
    private static void inscribirAlumnoAMateria() {
        Alumno alumno = elegirAlumnoPorDni();
        if (alumno == null) {
            return;
        }

        Carrera carrera = elegirCarrera();
        if (carrera == null) {
            return;
        }

        Materia materia = elegirMateria(carrera);
        if (materia == null) {
            return;
        }

        boolean ok = sistema.inscribirAlumnoAMateria(alumno, materia);
        if (ok) {
            System.out.println("Alumno inscripto correctamente en \"" + materia.getNombre() + "\".");
        }
    }

    // ---------------------------------------------------------------
    // Opción 3
    // ---------------------------------------------------------------
    private static void cargarSituacionFinal() {
        Carrera carrera = elegirCarrera();
        if (carrera == null) {
            return;
        }
        Materia materia = elegirMateria(carrera);
        if (materia == null) {
            return;
        }
        Alumno alumno = elegirAlumnoPorDni();
        if (alumno == null) {
            return;
        }

        System.out.println("Situación final: 1) Regular  2) Libre  3) Promocionado");
        int op = leerEntero("Elija la situación final: ");
        EstadoCursado estado;
        switch (op) {
            case 1:
                estado = EstadoCursado.REGULAR;
                break;
            case 2:
                estado = EstadoCursado.LIBRE;
                break;
            case 3:
                estado = EstadoCursado.PROMOCIONADO;
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }

        boolean ok = sistema.cargarSituacionFinal(materia, alumno, estado);
        if (ok) {
            System.out.println("Situación final cargada correctamente.");
        }
    }

    // ---------------------------------------------------------------
    // Opción 4
    // ---------------------------------------------------------------
    private static void registrarAsistencia() {
        Carrera carrera = elegirCarrera();
        if (carrera == null) {
            return;
        }
        Materia materia = elegirMateria(carrera);
        if (materia == null) {
            return;
        }
        Alumno alumno = elegirAlumnoPorDni();
        if (alumno == null) {
            return;
        }

        boolean ok = sistema.registrarInasistencia(materia, alumno);
        if (ok) {
            System.out.println("Inasistencia registrada correctamente.");
        }
    }

    // ---------------------------------------------------------------
    // Opción 5
    // ---------------------------------------------------------------
    private static void mostrarAlumnosPorCarreraYMateria() {
        Carrera carrera = elegirCarrera();
        if (carrera == null) {
            return;
        }
        Materia materia = elegirMateria(carrera);
        if (materia == null) {
            return;
        }

        System.out.println("¿Qué desea visualizar? 1) Cursando  2) Finalizada");
        int op = leerEntero("Elija una opción: ");

        if (op == 1) {
            List<Inscripcion> cursando = materia.getAlumnosCursando();
            System.out.println("Alumnos cursando \"" + materia.getNombre() + "\":");
            if (cursando.isEmpty()) {
                System.out.println("  No hay alumnos cursando esta materia.");
            }
            for (Inscripcion i : cursando) {
                System.out.println("  - " + i.getAlumno());
            }
        } else if (op == 2) {
            List<Inscripcion> finalizados = materia.getAlumnosFinalizados();
            System.out.println("Alumnos que finalizaron \"" + materia.getNombre() + "\":");
            if (finalizados.isEmpty()) {
                System.out.println("  No hay alumnos con cursado finalizado en esta materia.");
            }
            for (Inscripcion i : finalizados) {
                System.out.println("  - " + i.getAlumno() + " | Situación: " + i.getEstado()
                        + " | Inasistencias: " + i.getCantidadInasistencias());
            }
        } else {
            System.out.println("Opción inválida.");
        }
    }

    // ---------------------------------------------------------------
    // Opción 6
    // ---------------------------------------------------------------
    private static void mostrarInscripcionesDeAlumno() {
        Alumno alumno = elegirAlumnoPorDni();
        if (alumno == null) {
            return;
        }
        System.out.println("Inscripciones de " + alumno + ":");
        if (alumno.getInscripciones().isEmpty()) {
            System.out.println("  El alumno no tiene inscripciones.");
        }
        for (Inscripcion i : alumno.getInscripciones()) {
            System.out.println("  - " + i.getMateria().getNombre() + " (" + i.getMateria().getCarrera().getNombre()
                    + ") | Estado: " + i.getEstado() + " | Inasistencias: " + i.getCantidadInasistencias()
                    + "/" + i.getMateria().getLimiteInasistencias());
        }
    }

    // ---------------------------------------------------------------
    // Métodos auxiliares de selección / entrada de datos
    // ---------------------------------------------------------------

    private static Carrera elegirCarrera() {
        List<Carrera> carreras = sistema.getCarreras();
        System.out.println("Carreras disponibles:");
        for (int i = 0; i < carreras.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + carreras.get(i));
        }
        int op = leerEntero("Indique la Carrera (número): ");
        if (op < 1 || op > carreras.size()) {
            System.out.println("Carrera inválida.");
            return null;
        }
        return carreras.get(op - 1);
    }

    private static Materia elegirMateria(Carrera carrera) {
        List<Materia> materias = carrera.getMaterias();
        System.out.println("Materias de \"" + carrera.getNombre() + "\":");
        for (int i = 0; i < materias.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + materias.get(i));
        }
        int op = leerEntero("Indique la Materia (número): ");
        if (op < 1 || op > materias.size()) {
            System.out.println("Materia inválida.");
            return null;
        }
        return materias.get(op - 1);
    }

    private static Alumno elegirAlumnoPorDni() {
        System.out.print("Ingrese DNI del Alumno: ");
        String dni = sc.nextLine();
        Alumno alumno = sistema.buscarAlumnoPorDni(dni);
        if (alumno == null) {
            System.out.println("No existe un alumno registrado con ese DNI.");
        }
        return alumno;
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Ingrese un número válido: ");
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }
}
