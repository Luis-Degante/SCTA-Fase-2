package Modelo;

/**
 * Clase que representa la entidad Usuario en el sistema. Se utiliza para
 * transportar los datos del usuario entre las capas de la aplicación (MVC),
 * incluyendo credenciales y parámetros de seguridad.
 *
 * * @author lsuar
 */
public class Usuario {

    // Atributos de la clase que mapean con la tabla 'usuario' de la base de datos
    private int id_usuario;
    private String nombre;
    private String password;
    private String pregunta;
    private String respuestapregunta;

    /**
     * Constructor por defecto. Inicializa los atributos numéricos en 0 y las
     * cadenas de texto como vacías para evitar errores de puntero nulo
     * (NullPointerException).
     */
    public Usuario() {
        this.id_usuario = 0;
        this.nombre = "";
        this.password = "";
        this.pregunta = "";
        this.respuestapregunta = "";
    }

    /**
     * @return El identificador único del usuario.
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario El nuevo ID a asignar al usuario.
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return El nombre o nickname del usuario administrador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre El nombre que se desea establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return La contraseña almacenada del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password La nueva contraseña a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return El texto de la pregunta de seguridad para recuperación.
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * @param pregunta El enunciado de la pregunta de seguridad.
     */
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * @return La respuesta configurada para la pregunta de seguridad.
     */
    public String getRespuestapregunta() {
        return respuestapregunta;
    }

    /**
     * @param respuestapregunta La respuesta que validará el acceso en caso de
     * olvido.
     */
    public void setRespuestapregunta(String respuestapregunta) {
        this.respuestapregunta = respuestapregunta;
    }

}
