-- Creación de la base de datos
DROP DATABASE IF EXISTS scta;
CREATE DATABASE scta;
USE scta;

-- 1. Tabla: usuario (Incluye seguridad para Login)
CREATE TABLE usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    pregunta_seguridad VARCHAR(150) NULL,
    respuestapregunta VARCHAR(150) NULL,
    PRIMARY KEY (id_usuario)
);

-- 2. Tabla: categoria
CREATE TABLE categoria (
    id_categoria INT NOT NULL AUTO_INCREMENT,
    descripcion VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_categoria)
);

-- 3. Tabla: proveedor
CREATE TABLE proveedor (
    id_proveedor INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    empresa VARCHAR(100) NULL,
    telefono VARCHAR(20) NULL,
    PRIMARY KEY (id_proveedor)
);

-- 4. Tabla: producto
CREATE TABLE producto (
    id_producto INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT NULL,
    precio_proveedor DECIMAL(10,2) NOT NULL, -- Precio de compra
    precio_unitario DECIMAL(10,2) NOT NULL,  -- Precio de venta
    id_categoria INT,
    id_proveedor INT,
    stock_actual INT DEFAULT 0,
    stock_minimo INT DEFAULT 0,
    iva INT DEFAULT 0,
    puntos_otorgados INT DEFAULT 0,
    fecha_de_agregacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_producto),
    FOREIGN KEY (id_categoria) REFERENCES categoria (id_categoria),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor (id_proveedor)
);

-- 5. Tabla: cliente
CREATE TABLE cliente (
    id_cliente INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NULL,
    PRIMARY KEY (id_cliente)
);

-- 6. Tabla: regla_descuento (Relación 1:1 con Categoría)
CREATE TABLE regla_descuento (
    id_regla INT NOT NULL AUTO_INCREMENT,
    id_categoria INT UNIQUE NOT NULL,
    puntos_requeridos INT NOT NULL,
    porcentaje_descuento DECIMAL(5,2) NOT NULL,
    PRIMARY KEY (id_regla),
    FOREIGN KEY (id_categoria) REFERENCES categoria (id_categoria)
);

-- 7. Tabla puente: cliente_puntos (Historial de puntos por producto)
CREATE TABLE cliente_puntos (
    id_cliente INT,
    id_producto INT,
    puntos_acumulados INT DEFAULT 0,
    PRIMARY KEY (id_cliente, id_producto),
    FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente),
    FOREIGN KEY (id_producto) REFERENCES producto (id_producto)
);

-- 8. Tabla: cabecera_venta (Maestro de la transacción)
CREATE TABLE cabecera_venta (
    id_cabecera INT NOT NULL AUTO_INCREMENT,
    id_cliente INT,
    valor_a_pagar DECIMAL(10,2) NOT NULL,
    fecha_venta DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_cabecera),
    FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
);

-- 9. Tabla: detalle_venta (Detalle de cada artículo vendido)
CREATE TABLE detalle_venta (
    id_detalle INT NOT NULL AUTO_INCREMENT,
    id_cabecera INT,
    id_producto INT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    descuento DECIMAL(10,2) DEFAULT 0.00,
    iva DECIMAL(10,2) NOT NULL,
    total_a_pagar DECIMAL(10,2) NOT NULL,
    puntos_ganados INT DEFAULT 0,
    PRIMARY KEY (id_detalle),
    FOREIGN KEY (id_cabecera) REFERENCES cabecera_venta (id_cabecera),
    FOREIGN KEY (id_producto) REFERENCES producto (id_producto)
);