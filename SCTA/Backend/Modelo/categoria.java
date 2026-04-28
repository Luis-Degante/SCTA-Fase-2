package Modelo;

/**
 * Clase que representa la entidad Categoria en el sistema. Define las
 * clasificaciones de los productos (ej. Refrescos, Botanas, Abarrotes)
 * permitiendo una organización lógica dentro del inventario del SCTA.
 *
 * * @author lsuar
 */
public class categoria {

    // Atributos que mapean con la tabla 'categoria' de la base de datos
    private int idCategoria;
    private String descripcion;

    /**
     * Constructor por defecto. Inicializa el ID en 0 y la descripción como una
     * cadena vacía.
     */
    public categoria() {
        idCategoria = 0;
        descripcion = "";
    }

    /**
     * Constructor sobrecargado para inicializar la categoría con valores
     * específicos.
     *
     * * @param idCategoria El identificador único proveniente de la base de
     * datos.
     * @param descripcion El nombre o detalle de la categoría.
     */
    public categoria(int idCategoria, String descripcion) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
    }

    /**
     * @return El identificador numérico de la categoría.
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria El nuevo ID que se asignará a la categoría.
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * @return El nombre o descripción de la clasificación.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion El texto que describe a la categoría.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
