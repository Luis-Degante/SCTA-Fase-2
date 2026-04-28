/**
 * Clase Modelo: ReglaDescuento
 * Propósito: Representar la entidad "Regla de Descuento" como un objeto en Java.
 * Permite transportar los datos de fidelización entre la base de datos, 
 * el controlador y la interfaz de usuario.
 */
package Modelo;

public class ReglaDescuento {
    
    // --- Atributos (Mapeo de columnas de la BD) ---
    private int idRegla;               // Identificador primario de la regla
    private int idCategoria;           // Relación (FK) con la categoría a la que aplica
    private int puntosRequeridos;      // Cantidad de puntos que el cliente debe canjear
    private double porcentajeDescuento; // Valor del descuento (ej: 0.15 para 15%)

    /**
     * Constructor vacío
     * Necesario para frameworks de persistencia y para instanciar el objeto
     * antes de setear sus valores manualmente.
     */
    public ReglaDescuento() {
    }

    /**
     * Constructor sobrecargado
     * Permite crear una instancia con todos sus datos cargados en una sola línea.
     * Útil cuando recuperas datos desde un ResultSet de SQL.
     */
    public ReglaDescuento(int idRegla, int idCategoria, int puntosRequeridos, double porcentajeDescuento) {
        this.idRegla = idRegla;
        this.idCategoria = idCategoria;
        this.puntosRequeridos = puntosRequeridos;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    // --- Métodos Getter y Setter ---
    // Proporcionan el principio de ENCAPSULAMIENTO, permitiendo leer o 
    // modificar los atributos privados de forma controlada.

    public int getIdRegla() {
        return idRegla;
    }

    public void setIdRegla(int idRegla) {
        this.idRegla = idRegla;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(int puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
}