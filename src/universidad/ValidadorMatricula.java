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
 * Eslabón: el alumno debe estar matriculado en la Carrera de la Materia.
 */
public class ValidadorMatricula extends ValidadorInscripcion {

    @Override
    protected ResultadoValidacion verificar(Alumno alumno, Materia materia) {
        if (!alumno.estaMatriculadoEn(materia.getCarrera())) {
            return ResultadoValidacion.rechazado("El alumno no está matriculado en la Carrera \""
                    + materia.getCarrera().getNombre() + "\", no puede inscribirse a esta materia.");
        }
        return ResultadoValidacion.aprobado();
    }
}
