# Bitácora individual - Actividad Evaluativa 2 (AE2)
**Paradigmas y Lenguajes de Programación II** · Benítez, Maximo · Ing. en Sistemas de Información

---

## Encuentro 1 - [23/09/26]
**Tareas**
- Lectura de la consigna A2 y de la rúbrica (articulación teoría-práctica, implementación, organización del código, presentación).
- Análisis del código de la solución grupal AE1 (Persona, Alumno, Profesor, Coordinador, Carrera, Materia, Inscripcion, EstadoCursado, Sistema, Main).

**Decisiones**
- Puntos del dominio a profundizar: (1) nadie era notificado de lo que pasaba en una inscripción, (2) las reglas de inscripción eran mínimas y estaban repartidas, sin correlativas ni cupo, (3) las inasistencias no tenían consecuencias.

**Obstáculos**
- Ninguno; sólo relevamiento.

## Encuentro 2 - [24/09/26]
**Tareas**
- Elección de patrones entre los vistos en clase (Builder, Singleton, Adapter, Facade, Chain of Responsibility, Observer).

**Decisiones**
- **Observer**, para avisar al alumno, al coordinador y guardar un historial sin acoplar `Inscripcion` a cada interesado.
- **Chain of Responsibility**, para las reglas de inscripción (matrícula, duplicada, correlativas, cupo).
- Descartados por artificiales en este dominio: Singleton, Builder y Adapter. Facade: `Sistema` ya cumple ese rol, se refuerza pero no se cuenta como patrón.

**Obstáculos**
- Decidir cuál patrón era realmente necesario y no elegir sólo para llegar a dos.

## Encuentro 3 - [25/09/26]
**Tareas**
- Implementación de Observer: `Observador`, `Sujeto`, `TipoEvento`, `EventoInscripcion`, `NotificadorAlumno`, `NotificadorCoordinador`, `RegistroAuditoria`.
- `Inscripcion` pasa a ser `Sujeto`. Se agrega la regla de inasistencias (límite por materia; al superarlo, LIBRE).

**Decisiones**
- Interfaz propia en lugar de `java.util.Observable` (deprecado).
- El evento de confirmación se publica con `confirmar()`, después de suscribir los observadores; si se publicara en el constructor se perdería.

**Obstáculos**
- Orden de creación de la inscripción y suscripción de observadores (resuelto con `confirmar()`).

## Encuentro 4 - [26/09/26]
**Tareas**
- Implementación de Chain of Responsibility: `ResultadoValidacion`, `ValidadorInscripcion` y los eslabones `ValidadorMatricula`, `ValidadorInscripcionDuplicada`, `ValidadorCorrelativas`, `ValidadorCupo`.
- `Materia` incorpora correlativas, cupo máximo y límite de inasistencias.
- `Sistema` arma la cadena y suscribe los observadores; se cargan correlativas en los datos iniciales.

**Decisiones**
- Orden de la cadena: matrícula, duplicada, correlativas, cupo.
- Una vez finalizado el cursado, el estado no se puede modificar.

**Obstáculos**
- Las validaciones estaban repartidas entre `Sistema` y `Materia`; hubo que moverlas a los eslabones sin cambiar los mensajes originales.

## Encuentro 5 - [27/09/26]
**Tareas**
- Encapsulamiento (getters de sólo lectura) y `Main` operando únicamente a través de `Sistema`; nuevas opciones de menú 6 y 7.
- Pruebas por consola: correlativas, faltas hasta quedar LIBRE, duplicados, matrícula y cupo.
- Informe en PDF y diagramas UML.

**Decisiones**
- Limitación reconocida: no se contempla la recursada (un alumno LIBRE no puede reinscribirse a la misma materia).

**Obstáculos**
- Pendiente: .

---
