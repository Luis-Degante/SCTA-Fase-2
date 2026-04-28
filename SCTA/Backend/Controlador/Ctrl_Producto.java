package Controlador;

import Conexion.Conexión;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Ctrl_Producto {

    public boolean guardar(Producto objeto) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("INSERT INTO producto (nombre, descripcion, precio_unitario, id_categoria, id_proveedor, iva) VALUES (?, ?, ?, ?, ?, ?)");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getDescripcion());
            consulta.setDouble(3, objeto.getPrecio_unitario());
            consulta.setInt(4, objeto.getIdCategoria());
            consulta.setInt(5, objeto.getIdProveedor());
            consulta.setInt(6, objeto.getIVA());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el producto: " + e, "Error.", JOptionPane.ERROR_MESSAGE);
        }

        return respuesta;
    }

    public boolean existeProducto(String Producto) {
        boolean respuesta = false;
        String sql = "select nombre from producto where nombre = '" + Producto + "'";
        Statement st;

        try {

            Connection cn = Conexión.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar el producto.");
        }

        return respuesta;
    }

    public boolean actualizarConfiguracion(Producto objeto, int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("Update producto set nombre = ?, descripcion = ?,"
                    + "id_categoria = ?, id_proveedor = ?, puntos_otorgados = ? where id_producto = '" + idProducto + "'");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getDescripcion());
            consulta.setInt(3, objeto.getIdCategoria());
            consulta.setInt(4, objeto.getIdProveedor());
            consulta.setInt(5, objeto.getPuntos_otorgados());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar categoria.");
        }

        return respuesta;
    }

    public boolean actualizarAjusteInventario(Producto objeto, int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("Update producto set stock_actual = ?, stock_minimo = ? "
                    + "where id_producto = '" + idProducto + "'");

            consulta.setInt(1, objeto.getStock_actual());
            consulta.setInt(2, objeto.getStock_minimo());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        return respuesta;
    }

    public boolean EliminarConfiguracion(int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();
        try {
            // 1. Usamos el parámetro ? para el ID
            PreparedStatement consulta = cn.prepareStatement("delete from producto where id_producto = ?");
            consulta.setInt(1, idProducto);

            // 2. Ejecutamos una sola vez y guardamos el resultado
            int resultado = consulta.executeUpdate();

            if (resultado > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.getMessage());
        }

        return respuesta;
    }

}
