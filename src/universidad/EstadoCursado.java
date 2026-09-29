/**
 * UNIVERSIDAD DE LA CUENCA DEL PLATA
 * INGENIERIA EN SISTEMAS DE INFORMACION
 * MATERIA: PARADIGMAS Y LENGUAJES DE PROGRAMACION II
 * TRABAJO PRACTICO AE1 EVALUATIVO
 * INTEGRANTES: MORINIGO SERGIO, BENITEZ MAXIMO
 * CURSO: 2° "B"
 */
package universidad;

/**
 * Representa el estado del cursado de un Alumno en una Materia.
 * CURSANDO: el alumno está inscripto y aún no finalizó el cursado.
 * REGULAR, LIBRE, PROMOCIONADO: situaciones finales posibles.
 */
public enum EstadoCursado {
    CURSANDO,
    REGULAR,
    LIBRE,
    PROMOCIONADO
}
