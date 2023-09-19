import java.util.ArrayList;

/**
 * Clase que representa una sede de una universidad.
 */
class Sede {
    private int idSede;
    private ArrayList<Salon> salones;

    /**
     * Constructor de la clase Sede.
     *
     * @param idSede Identificador único de la sede.
     * @param nombre Nombre de la sede.
     */
    public Sede(int idSede) {
        this.idSede = idSede;
        this.salones = new ArrayList<Salon>();
    }

    /**
     * Obtiene el identificador único de la sede.
     *
     * @return El identificador de la sede.
     */
    public int getIdSede() {
        return idSede;
    }

    /**
     * Obtiene el nombre de la sede.
     *
     * @return El nombre de la sede.
     */


    /**
     * Agrega un salón a la sede.
     *
     * @param salon El salón a agregar.
     */
    public void agregarSalon(Salon salon) {
        salones.add(salon);
    }

    /**
     * Obtiene la lista de salones de la sede.
     *
     * @return La lista de salones.
     */
    public ArrayList<Salon> getSalones() {
        return salones;
    }

    /**
     * Retorna el salon segun el id
     *
     * @return el salon.
     */
    public Salon getSalon(int idSalon) {
        for(Salon salon: salones){
            if(salon.getIdSalon() == idSalon){
                return salon;
            }
        }
        return  null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Sede: " + idSede;
    }
}
