package Controlador;

import Modelo.Usuario;
import Conexion.Conexión;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Clase controlador encargada de gestionar las operaciones de autenticación,
 * seguridad y actualización de datos del usuario en el sistema.
 * @author lsuar
 */
public class Ctrl_Usuario {

    /**
     * Metodo que realiza el inicio de sesión validando las credenciales del usuario.
     * @param objeto Instancia de Usuario que contiene el nombre y password ingresados.
     * @return boolean true si el usuario y contraseña coinciden en la base de datos.
     */
    public boolean loginUser(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();

        // Validación preventiva de la conexión al servidor
        if (cn == null) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo establecer conexión con el servidor.\nVerifique que XAMPP/MySQL esté encendido.",
                    "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Consulta preparada para evitar ataques de inyección SQL
        String sql = "SELECT nombre_usuario FROM usuario WHERE nombre_usuario = ? AND password = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, objeto.getNombre());
            pst.setString(2, objeto.getPassword());

            ResultSet rs = pst.executeQuery();

            // Si existe un registro, las credenciales son correctas
            if (rs.next()) {
                respuesta = true;
            }

            cn.close(); // Liberación del recurso de conexión

        } catch (SQLException e) {
            System.out.println("Error en la consulta de login: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar la base de datos.");
        }

        return respuesta;
    }

    /**
     * Metodo que realiza la actualización del nombre de usuario y la contraseña.
     * @param objeto Instancia con los nuevos datos del usuario.
     * @param idUsuario Identificador único del usuario a modificar.
     * @return boolean true si la actualización se realizó con éxito.
     */
    public boolean actualizarUsuario(Usuario objeto, int idUsuario) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();
        try {
            // Se actualizan los campos básicos de perfil
            PreparedStatement consulta = cn.prepareStatement("Update usuario set nombre_usuario = ?, password = ? "
                    + "where id_usuario = '" + idUsuario + "'");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getPassword());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar los datos del cliente: " + e, "Error.", JOptionPane.ERROR_MESSAGE);
        }

        return respuesta;
    }

    /**
     * Metodo que realiza la validación de acceso mediante la respuesta a la pregunta de seguridad.
     * Utiliza LOWER() en SQL para permitir que la comparación no sea sensible a mayúsculas.
     * @param objeto Instancia con la respuesta proporcionada por el usuario.
     * @return boolean true si la respuesta coincide con la registrada para el usuario administrador.
     */
    public boolean loginPorPreguntaUnico(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();

        if (cn == null) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo establecer conexión con el servidor.",
                    "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Se asume ID 1 para el usuario administrador único del sistema
        String sql = "SELECT nombre_usuario FROM usuario WHERE id_usuario = 1 AND LOWER(respuestapregunta) = LOWER(?)";

        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, objeto.getRespuestapregunta());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error en la consulta de acceso por pregunta: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar la base de datos.");
        }

        return respuesta;
    }

    /**
     * Metodo que realiza la actualización de la configuración de seguridad (pregunta y respuesta).
     * @param objeto Instancia con la nueva pregunta y su respectiva respuesta.
     * @param idUsuario ID del usuario al que se le aplicarán los cambios.
     * @return boolean true si los parámetros de seguridad fueron actualizados.
     */
    public boolean actualizarPregunta(Usuario objeto, int idUsuario) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "UPDATE usuario SET pregunta_seguridad = ?, respuestapregunta = ? WHERE id_usuario = ?"
            );

            consulta.setString(1, objeto.getPregunta());
            consulta.setString(2, objeto.getRespuestapregunta());
            consulta.setInt(3, idUsuario);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al actualizar los parámetros de seguridad: " + e,
                    "Error.", JOptionPane.ERROR_MESSAGE);
        }

        return respuesta;
    }

    /**
     * Metodo que realiza la obtención de la pregunta de seguridad registrada en la BD.
     * Útil para mostrarla dinámicamente en la interfaz de recuperación de cuenta.
     * @return String con el texto de la pregunta de seguridad.
     */
    public String obtenerPreguntaSeguridad() {
        String pregunta = "";
        Connection cn = Conexión.conectar();
        String sql = "SELECT pregunta_seguridad FROM usuario WHERE id_usuario = 1";

        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                pregunta = rs.getString("pregunta_seguridad");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener la pregunta: " + e.getMessage());
        }
        return pregunta;
    }

}