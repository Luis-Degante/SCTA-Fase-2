package Controlador;

import Conexion.Conexión;
import Modelo.categoria;
import java.sql.*;

/**
 * Clase controlador encargada de gestionar las operaciones CRUD para la entidad
 * Categoria en la base de datos.
 *
 * @author lsuar
 */
public class Ctrl_Categoria {

    /**
     * Metodo que realiza la inserción de una nueva categoria en la base de
     * datos.
     *
     * @param objeto Instancia de la clase categoria con los datos a registrar.
     * @return boolean true si se guardó correctamente, false en caso contrario.
     */
    public boolean guardar(categoria objeto) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();
        try {
            // Se utiliza PreparedStatement para evitar inyecciones SQL
            PreparedStatement consulta = cn.prepareStatement("insert into categoria values (?, ?)");
            consulta.setInt(1, 0); // El ID es autoincremental en la BD
            consulta.setString(2, objeto.getDescripcion());

            // Si se afecta al menos una fila, la inserción fue exitosa
            if (consulta.executeUpdate() >= 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar categoria: " + e);
        }

        return respuesta;
    }

    /**
     * Metodo que realiza la consulta para verificar si una categoria ya existe.
     *
     * @param categoria Nombre de la descripción a buscar.
     * @return boolean true si el nombre ya se encuentra registrado.
     */
    public boolean existeCategoria(String categoria) {
        boolean respuesta = false;
        Statement st;

        try {
            Connection cn = Conexión.conectar();
            st = cn.createStatement();
            // Consulta directa para buscar coincidencias exactas en la descripción
            ResultSet rs = st.executeQuery("select descripcion from categoria where descripcion = '" + categoria + "'");

            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar la categoria: " + e);
        }

        return respuesta;
    }

    /**
     * Metodo que realiza la actualización de una categoria existente.
     *
     * @param objeto Instancia con la nueva descripción.
     * @param idCategoria ID único de la categoria a modificar.
     * @return boolean true si la actualización fue exitosa.
     */
    public boolean actualizar(categoria objeto, int idCategoria) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();
        try {
            // Sentencia para modificar la descripción filtrando por el ID seleccionado
            PreparedStatement consulta = cn.prepareStatement("Update categoria set descripcion =? where id_categoria = '" + idCategoria + "'");
            consulta.setString(1, objeto.getDescripcion());

            if (consulta.executeUpdate() >= 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar categoria: " + e);
        }

        return respuesta;
    }

    /**
     * Metodo que realiza la eliminación de una categoria de la base de datos.
     *
     * @param idCategoria Identificador único de la categoria.
     * @return boolean true si el registro fue borrado.
     */
    public boolean Eliminar(int idCategoria) {
        boolean respuesta = false;
        Connection cn = Conexión.conectar();
        try {
            // Sentencia DELETE para remover el registro por ID
            PreparedStatement consulta = cn.prepareStatement("delete from categoria where id_categoria = '" + idCategoria + "'");

            // Verificamos si la operación fue exitosa
            if (consulta.executeUpdate() >= 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar categoria: " + e);
        }

        return respuesta;
    }

}
