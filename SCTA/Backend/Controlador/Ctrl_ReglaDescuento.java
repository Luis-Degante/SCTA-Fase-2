/**
 * Clase Controlador: Ctrl_ReglaProducto
 * Propósito: Gestionar la lógica de negocio y las consultas SQL 
 * relacionadas con la tabla 'regla_descuento'.
 */
package Controlador;

import Conexion.Conexión;
import Modelo.ReglaDescuento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Ctrl_ReglaDescuento {

    /**
     * Método: actualizarReglas
     * Propósito: Guarda o modifica una regla de descuento.
     * Técnica: Utiliza "Upsert" (Insert or Update).
     * @param objeto Instancia de ReglaDescuento con los datos.
     * @param idCategoria ID de la categoría a la que se vincula.
     * @return boolean true si la operación fue exitosa.
     */
    public boolean actualizarReglas(ReglaDescuento objeto, int idCategoria) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();
        try {
            /**
             * SQL Inteligente: 
             * INSERT ... ON DUPLICATE KEY UPDATE es una función avanzada de MySQL.
             * Si el 'id_categoria' ya existe (porque es UNIQUE), actualiza los valores existentes.
             * Si no existe, crea un nuevo registro automáticamente.
             */
            String sql = "INSERT INTO regla_descuento (id_categoria, puntos_requeridos, porcentaje_descuento) "
                    + "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE "
                    + "puntos_requeridos = VALUES(puntos_requeridos), "
                    + "porcentaje_descuento = VALUES(porcentaje_descuento)";

            // Previene Inyección SQL mediante el uso de PreparedStatement
            PreparedStatement consulta = cn.prepareStatement(sql);

            // Mapeo de parámetros (?)
            consulta.setInt(1, idCategoria);
            consulta.setInt(2, objeto.getPuntosRequeridos());
            consulta.setDouble(3, objeto.getPorcentajeDescuento());

            // Ejecuta la instrucción y verifica si hubo filas afectadas
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close(); // Liberación de recursos de conexión

        } catch (SQLException e) {
            System.out.println("Error en el controlador de reglas: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al procesar los datos: " + e.getMessage());
        }

        return respuesta;
    }

    /**
     * Método: EliminarReglas
     * Propósito: Borrar físicamente una regla de la base de datos.
     * @param idCategoria
     * @return 
     */
    public boolean EliminarReglas(int idCategoria) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();
        try {
            // Preparación de la consulta de eliminación por ID
            PreparedStatement consulta = cn.prepareStatement("delete from regla_descuento where id_categoria = ?");
            consulta.setInt(1, idCategoria);

            // Ejecución y validación de éxito
            int resultado = consulta.executeUpdate();

            if (resultado > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
        }

        return respuesta;
    }
}