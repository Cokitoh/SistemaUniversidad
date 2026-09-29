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
 * Eslabón: el alumno no debe estar ya inscripto en la Materia.
 */
public class ValidadorInscripcionDuplicada extends ValidadorInscripcion {

    @Override
    protected ResultadoValidacion verificar(Alumno alumno, Materia materia) {
        if (materia.buscarInscripcion(alumno) != null) {
            return ResultadoValidacion.rechazado("El alumno ya está inscripto en esta materia.");
        }
        return ResultadoValidacion.aprobado();
    }
}
