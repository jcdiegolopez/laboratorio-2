/**
 * Clase que representa un curso universitario.
 */
class Curso {
    private int id_curso;
    private int id_sede;
    private String nombre_curso;
    private int horario; // Hora de inicio del curso (7 a 20)
    private int duracion; // Duración en horas
    private String dias; // Días de la semana separados por coma
    private int cantidad_estudiantes;
    private boolean asignado;

    /**
     * Constructor de la clase Curso.
     *
     * @param id_curso            Identificador único del curso.
     * @param id_sede             Identificador de la sede donde se imparte el curso.
     * @param nombre_curso        Nombre del curso.
     * @param horario             Hora de inicio del curso (de 7 a 20).
     * @param duracion            Duración en horas del curso.
     * @param dias                Días de la semana en que se imparte el curso (separados por coma).
     * @param cantidad_estudiantes Cantidad de estudiantes inscritos en el curso.
     */
    public Curso(int id_curso, int id_sede, String nombre_curso, int horario, int duracion, String dias, int cantidad_estudiantes) {
        this.id_curso = id_curso;
        this.id_sede = id_sede;
        this.nombre_curso = nombre_curso;
        this.horario = horario;
        this.duracion = duracion;
        this.dias = dias;
        this.cantidad_estudiantes = cantidad_estudiantes;
        this.asignado = false;
    }

    /**
     * Obtiene el identificador único del curso.
     *
     * @return El identificador del curso.
     */
    public int getIdCurso() {
        return id_curso;
    }

    /**
     * Obtiene el identificador de la sede donde se imparte el curso.
     *
     * @return El identificador de la sede.
     */
    public int getIdSede() {
        return id_sede;
    }

    /**
     * Obtiene el nombre del curso.
     *
     * @return El nombre del curso.
     */
    public String getNombreCurso() {
        return nombre_curso;
    }

    /**
     * Obtiene la hora de inicio del curso.
     *
     * @return La hora de inicio del curso.
     */
    public int getHorario() {
        return horario;
    }

    /**
     * Obtiene la duración en horas del curso.
     *
     * @return La duración en horas del curso.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Obtiene los días de la semana en que se imparte el curso.
     *
     * @return Los días de la semana separados por coma.
     */
    public String getDias() {
        return dias;
    }

    /**
     * Obtiene la cantidad de estudiantes inscritos en el curso.
     *
     * @return La cantidad de estudiantes inscritos.
     */
    public int getCantidadEstudiantes() {
        return cantidad_estudiantes;
    }

      /**
     * Obtiene el estado del curso.
     *
     * @return Si esta o no asignado el curso.
     */
    public boolean getAsignado() {
        return this.asignado;
    }

     /**
     * Setear el estado del curso.
     *
     * 
     */
    public void setAsignado(boolean state) {
        this.asignado = state;
    }

    public String toString() {
        return 
                "id_curso=" + id_curso +
                ", id_sede=" + id_sede +
                ", nombre_curso='" + nombre_curso + '\'' +
                ", horario=" + horario +
                ", duracion=" + duracion +
                ", dias='" + dias + '\'' +
                ", cantidad_estudiantes=" + cantidad_estudiantes;
    }
}
