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
 * Eslabón: el alumno debe tener REGULAR o PROMOCIONADO cada una de las
 * materias correlativas de la Materia a la que quiere inscribirse.
 */
public class ValidadorCorrelativas extends ValidadorInscripcion {

    @Override
    protected ResultadoValidacion verificar(Alumno alumno, Materia materia) {
        for (Materia correlativa : materia.getCorrelativas()) {
            Inscripcion previa = correlativa.buscarInscripcion(alumno);
            boolean cumplida = previa != null
                    && (previa.getEstado() == EstadoCursado.REGULAR
                    || previa.getEstado() == EstadoCursado.PROMOCIONADO);
            if (!cumplida) {
                return ResultadoValidacion.rechazado("No cumple la correlatividad: debe tener \""
                        + correlativa.getNombre() + "\" regular o promocionada para inscribirse a \""
                        + materia.getNombre() + "\".");
            }
        }
        return ResultadoValidacion.aprobado();
    }
}
