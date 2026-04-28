package Modelo;

public class Producto {

    //Atributos
    private int idProducto;
    private String Nombre;
    private String descripcion;
    private double precio_unitario;
    private double precio_proveedor;
    private int idCategoria;
    private int idProveedor;
    private int stock_actual;
    private int stock_minimo;
    private int IVA;
    private int puntos_otorgados;
    private int fecha;

    public Producto() {
        this.idProducto = 0;
        this.Nombre = "";
        this.descripcion = "";
        this.precio_unitario = 0;
        this.precio_proveedor = 0;
        this.idCategoria = 0;
        this.idProveedor = 0;
        this.stock_actual = 0;
        this.stock_minimo = 0;
        this.IVA = 0;
        this.puntos_otorgados = 0;
        this.fecha = 0;
    }

    public Producto(int idProducto, String Nombre, String descripcion, double precio_unitario, double precio_proveedor, int idCategoria, int idProveedor, int stock_actual, int stock_minimo, int IVA, int puntos_otorgados, int fecha) {
        this.idProducto = idProducto;
        this.Nombre = Nombre;
        this.descripcion = descripcion;
        this.precio_unitario = precio_unitario;
        this.precio_proveedor = precio_proveedor;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
        this.stock_actual = stock_actual;
        this.stock_minimo = stock_minimo;
        this.IVA = IVA;
        this.puntos_otorgados = puntos_otorgados;
        this.fecha = fecha;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public double getPrecio_proveedor() {
        return precio_proveedor;
    }

    public void setPrecio_proveedor(double precio_proveedor) {
        this.precio_proveedor = precio_proveedor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getStock_actual() {
        return stock_actual;
    }

    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    public int getStock_minimo() {
        return stock_minimo;
    }

    public void setStock_minimo(int stock_minimo) {
        this.stock_minimo = stock_minimo;
    }

    public int getIVA() {
        return IVA;
    }

    public void setIVA(int IVA) {
        this.IVA = IVA;
    }

    public int getPuntos_otorgados() {
        return puntos_otorgados;
    }

    public void setPuntos_otorgados(int puntos_otorgados) {
        this.puntos_otorgados = puntos_otorgados;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    
    
}
