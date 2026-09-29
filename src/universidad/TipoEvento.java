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
 * Tipos de eventos que una Inscripcion (sujeto) puede publicar
 * a sus Observadores.
 */
public enum TipoEvento {
    INSCRIPCION_CONFIRMADA,
    INASISTENCIA_REGISTRADA,
    LIMITE_INASISTENCIAS_SUPERADO,
    CURSADO_FINALIZADO
}
