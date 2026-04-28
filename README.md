#SCTA

Sistema de Control de Tiendas de Abarrotes (Suarez Degante Luis Angel - Santoyo Aparicio Leonardo)

Archivos del proyecto SCTA

Este archivo contiene la explicación del codigo de las clases Backend correspondites al modulo de productos
de la semana 2.

Archivos de la carpeta Conexión 

Archivo: Conexión.java 

Explicación del fragmento: 

Aunque ya se mencionó en el módulo anterior, esta clase se sigue utilizando en esta y los demás módulos. Esta clase tiene como finalidad establecer 
la comunicación entre la aplicación desarrollada en Java y la base de datos MySQL utilizando el controlador JDBC. Su función principal es centralizar el 
proceso de conexión, permitiendo que otras partes del sistema puedan acceder a la base de datos de forma sencilla y reutilizable. Dentro de la clase se define 
el método estático conectar(), el cual intenta crear una conexión activa hacia una base de datos local denominada “scta”, utilizando como credenciales el usuario 
root y una contraseña vacía. Este método emplea la clase DriverManager para establecer la conexión mediante una URL que apunta a localhost, lo que indica que 
la base de datos se encuentra en el mismo equipo donde se ejecuta la aplicación. En caso de que la conexión se realice correctamente, el método retorna un objeto 
de tipo Connection, el cual puede ser utilizado por otras clases (como controladores o modelos) para ejecutar consultas SQL, inserciones, actualizaciones o 
eliminaciones de datos. Por otro lado, si ocurre algún error durante el proceso (por ejemplo, si el servidor MySQL no está activo, la base de datos no existe o 
hay problemas de acceso), el sistema muestra un mensaje de error mediante una ventana emergente (JOptionPane) y retorna un valor nulo. 

 

Archivos de la carpeta Controlador 

Archivo: Ctrl_Producto.java 

Explicación de fragmento: 

La presente clase tiene como propósito principal gestionar todas las operaciones relacionadas con la entidad Producto en la base de datos. Su función es actuar 
como intermediaria entre la lógica del sistema y el almacenamiento de datos, permitiendo realizar operaciones de tipo CRUD (Crear, Leer, Actualizar y Eliminar).
Para ello, utiliza la clase Conexión, encargada de establecer la comunicación con la base de datos MySQL mediante JDBC, así como la clase Producto, que encapsula 
los datos necesarios para cada operación. En cuanto a sus funcionalidades, la clase incluye un método llamado guardar, el cual permite registrar un nuevo producto
en la base de datos. Este método recibe un objeto de tipo Producto y utiliza sus atributos, como nombre, descripción, precio, categoría, proveedor e IVA, para
insertarlos en la tabla correspondiente. Para garantizar mayor seguridad, emplea una consulta preparada (PreparedStatement), evitando posibles ataques de inyección
SQL. Si la operación se realiza correctamente, retorna un valor verdadero; en caso contrario, muestra un mensaje de error. Asimismo, cuenta con el método
existeProducto, cuya finalidad es verificar si ya existe un producto con el mismo nombre dentro de la base de datos, con el objetivo de evitar duplicados. Este 
método ejecuta una consulta que busca coincidencias en el campo nombre de la tabla producto. Por otro lado, el método actualizarConfiguracion permite modificar la 
información general de un producto existente, como su nombre, descripción, categoría, proveedor y los puntos otorgados dentro del sistema de fidelización. 
De igual manera, el método actualizarAjusteInventario está enfocado en la gestión del inventario, permitiendo actualizar los valores de stock actual y stock mínimo 
de un producto. Finalmente, la clase incluye el método EliminarConfiguracion, el cual se encarga de eliminar un producto de la base de datos utilizando su
identificador. Este proceso se realiza mediante una consulta preparada para garantizar seguridad y eficiencia. 

 

Archivos de la carpeta Modelo  

Archivo: Producto.java 

Explicación del fragmento: 

Esta clase funciona como un objeto de transferencia de datos (DTO), permitiendo encapsular toda la información relacionada con los productos y facilitar su
intercambio entre las distintas capas del sistema, como el controlador y la base de datos.La clase define un conjunto de atributos que corresponden directamente
con los campos de la tabla producto en la base de datos. Entre estos atributos se incluyen el identificador único del producto (idProducto), su nombre, descripción,
precio unitario y precio del proveedor, así como las referencias a su categoría y proveedor mediante llaves foráneas (idCategoria e idProveedor). Además, incorpora
datos importantes para la gestión del inventario, como el stock actual, el stock mínimo, el impuesto aplicado (IVA), los puntos otorgados dentro del sistema de
fidelización y un campo de fecha que puede ser utilizado para registrar información temporal relacionada con el producto. La clase cuenta con dos constructores.
El primero es un constructor por defecto, el cual inicializa todos los atributos con valores base (como 0 o cadenas vacías), evitando errores relacionados con
valores nulos durante la ejecución del sistema. El segundo es un constructor parametrizado, que permite crear instancias de la clase Producto con valores
específicos desde el momento de su creación, facilitando su uso en operaciones como registros o actualizaciones. Asimismo, la clase implementa métodos de acceso y
modificación (getters y setters) para cada uno de sus atributos. Estos métodos permiten aplicar el principio de encapsulamiento, ya que controlan la forma en que
los datos son consultados o modificados desde otras partes del sistema. Gracias a esto, se mantiene una estructura organizada, segura y fácil de mantener. 
