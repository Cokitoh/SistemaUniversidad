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
 * Eslabón: la Materia debe tener cupo disponible.
 */
public class ValidadorCupo extends ValidadorInscripcion {

    @Override
    protected ResultadoValidacion verificar(Alumno alumno, Materia materia) {
        if (!materia.hayCupo()) {
            return ResultadoValidacion.rechazado("No hay cupo disponible en \"" + materia.getNombre()
                    + "\" (cupo máximo: " + materia.getCupoMaximo() + ").");
        }
        return ResultadoValidacion.aprobado();
    }
}
