

/**
 * Clase que representa un salón de clases en una universidad.
 */
class Salon {
    private int idSede;
    private char edificio;
    private int nivel;
    private int idSalon;
    private int capacidad;
    private Curso[][] horarioAsignado; // Matriz para registrar la asignación de cursos al horario.

    /**
     * Constructor de la clase Salon.
     *
     * @param idSede    Identificador de la sede del salón.
     * @param edificio  Letra que representa el edificio.
     * @param nivel     Nivel del edificio donde se encuentra el salón.
     * @param idSalon   Identificador único del salón.
     * @param capacidad Capacidad máxima de estudiantes que puede albergar el salón.
     */
    public Salon(int idSede, char edificio, int nivel, int idSalon, int capacidad) {
        this.idSede = idSede;
        this.edificio = edificio;
        this.nivel = nivel;
        this.idSalon = idSalon;
        this.capacidad = capacidad;
        this.horarioAsignado = new Curso[6][14]; // Matriz de 6 días x 14 horas (de 7:00 AM a 8:00 PM)
    
    }

    /**
     * Obtiene el identificador de la sede del salón.
     *
     * @return El identificador de la sede.
     */
    public int getIdSede() {
        return idSede;
    }

    /**
     * Obtiene el horario del salon
     *
     * @return La matriz bidemensional del horario.
     */
    public Curso[][] getHorario() {
        return horarioAsignado;
    }

    /**
     * Obtiene la letra que representa el edificio.
     *
     * @return La letra que representa el edificio.
     */
    public char getEdificio() {
        return edificio;
    }

    /**
     * Obtiene el nivel del edificio donde se encuentra el salón.
     *
     * @return El nivel del edificio.
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Obtiene el identificador único del salón.
     *
     * @return El identificador del salón.
     */
    public int getIdSalon() {
        return idSalon;
    }

    /**
     * Obtiene la capacidad máxima de estudiantes que puede albergar el salón.
     *
     * @return La capacidad del salón.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Consulta la asignación de un curso en un día y hora específicos.
     *
     * @param dia  El día de la semana (0 a 6, donde 0 es lunes y 6 es domingo).
     * @param hora La hora del día (de 0 a 13, donde 0 es 7:00 AM y 13 es 8:00 PM).
     * @return El curso asignado en ese día y hora, o null si no hay asignación.
     */
    public Curso consultarAsignacion(int dia, int hora) {
        return horarioAsignado[dia][hora];
    }

    /**
     * Asigna un curso a un día y hora específicos en el salón.
     *
     * @param curso El curso a asignar.
     * @param dia   El día de la semana (0 a 6, donde 0 es lunes y 6 es domingo).
     * @param hora  La hora del día (de 0 a 13, donde 0 es 7:00 AM y 13 es 8:00 PM).
     */
    public void asignarCurso(Curso curso, int dia, int hora) {
        horarioAsignado[dia][hora] = curso;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Sede: " + idSede + "\nEdificio: " + edificio + "\nNivel: " + nivel +
               "\nId Salón: " + idSalon + "\nCapacidad: " + capacidad;
    }
}
