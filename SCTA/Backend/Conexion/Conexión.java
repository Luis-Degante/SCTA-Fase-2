package Conexion;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Clase encargada de gestionar la comunicación entre la aplicación Java y la
 * base de datos MySQL mediante el driver JDBC.
 *
 * * @author lsuar
 */
public class Conexión {

    /**
     * Metodo que realiza la conexion a la base de datos local "scta". Utiliza
     * el driver de MySQL para establecer un puente de comunicación.
     *
     * * @return El objeto Connection activo o null si ocurre un error.
     */
    public static Connection conectar() {

        try {
            // Se define la ruta de la base de datos, el usuario (root) y la contraseña (vacía)
            // La URL apunta a localhost y al esquema "scta"
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/scta", "root", "");

            // Si la conexión es exitosa, se retorna el objeto Connection
            return cn;

        } catch (SQLException e) {
            // Se muestra una alerta visual al usuario en caso de falla (servidor apagado, db inexistente, etc.)
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);

        }

        // Retorna null si el bloque try falló
        return null;

    }

}
