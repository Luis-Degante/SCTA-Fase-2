package Controlador;

import Conexion.Conexión;
import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Ctrl_Cliente {

    public boolean guardar(Cliente objeto) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("INSERT INTO cliente (nombre, telefono) VALUES (?, ?)");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getTelefono());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el cliente: " + e, "Error.", JOptionPane.ERROR_MESSAGE);
        }

        return respuesta;
    }

    public boolean existeCliente(String telefono) {
        boolean respuesta = false;
        String sql = "select telefono from cliente where telefono = '" + telefono + "'";
        Statement st;

        try {

            Connection cn = Conexión.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, el cliente ya existe: " + e, "Error.", JOptionPane.ERROR_MESSAGE);
        }

        return respuesta;
    }

    public boolean actualizarConfiguracion(Cliente objeto, int idCliente) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("Update cliente set nombre = ?, telefono = ? "
                    + "where id_cliente = '" + idCliente + "'");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getTelefono());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar los datos del cliente: " + e, "Error.", JOptionPane.ERROR_MESSAGE);
        }

        return respuesta;
    }

    public boolean EliminarConfiguracion(int idCLiente) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();
        try {
            // 1. Usamos el parámetro ? para el ID
            PreparedStatement consulta = cn.prepareStatement("delete from cliente where id_cliente = ?");
            consulta.setInt(1, idCLiente);

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
