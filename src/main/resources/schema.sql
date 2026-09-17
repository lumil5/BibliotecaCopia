-- ==========================================================
-- SCRIPT DDL: BASE DE DATOS SISTEMA BIBLIOTECA
-- Motor: PostgreSQL
-- ==========================================================

-- 1. CREACIÓN DE ESQUEMAS
CREATE SCHEMA IF NOT EXISTS administracion;
CREATE SCHEMA IF NOT EXISTS biblioteca;
CREATE SCHEMA IF NOT EXISTS inventario;
CREATE SCHEMA IF NOT EXISTS prestamo;
CREATE SCHEMA IF NOT EXISTS seguridad;

-- ==========================================================
-- ESQUEMA: administracion
-- ==========================================================

-- Tabla: persona
CREATE TABLE IF NOT EXISTS administracion.persona (
    idpersona SERIAL PRIMARY KEY,
    dni VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    "apellidoPaterno" VARCHAR(100) NOT NULL,
    "apellidoMaterno" VARCHAR(100),
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    correo VARCHAR(100),
    "fechaNacimiento" DATE,
    sexo VARCHAR(10),
    estado VARCHAR(20) DEFAULT 'ACTIVO'
);

-- Tabla: cliente
CREATE TABLE IF NOT EXISTS administracion.cliente (
    "idCliente" SERIAL PRIMARY KEY,
    idpersona INT NOT NULL,
    codigo VARCHAR(50) UNIQUE,
    "tipoCliente" VARCHAR(50) NOT NULL, -- ESTUDIANTE, DOCENTE, ADMINISTRATIVO, EXTERNO
    "fechaRegistro" DATE DEFAULT CURRENT_DATE,
    estado BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_cliente_persona FOREIGN KEY (idpersona)
        REFERENCES administracion.persona (idpersona)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Tabla: empleado
CREATE TABLE IF NOT EXISTS administracion.empleado (
    "idEmpleado" SERIAL PRIMARY KEY,
    idpersona INT NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    "fechaIngreso" DATE DEFAULT CURRENT_DATE,
    sueldo DOUBLE PRECISION,
    CONSTRAINT fk_empleado_persona FOREIGN KEY (idpersona)
        REFERENCES administracion.persona (idpersona)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ==========================================================
-- ESQUEMA: biblioteca
-- ==========================================================

-- Tabla: autor
CREATE TABLE IF NOT EXISTS biblioteca.autor (
    "idAutor" SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    pais VARCHAR(100),
    "fechaNacimiento" DATE,
    estado BOOLEAN DEFAULT TRUE
);

-- Tabla: categoria
CREATE TABLE IF NOT EXISTS biblioteca.categoria (
    "idCategoria" SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

-- Tabla: editorial
CREATE TABLE IF NOT EXISTS biblioteca.editorial (
    "idEditorial" SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    pais VARCHAR(100),
    telefono VARCHAR(20),
    correo VARCHAR(100),
    estado BOOLEAN DEFAULT TRUE
);

-- Tabla: libro
CREATE TABLE IF NOT EXISTS biblioteca.libro (
    "idLibro" SERIAL PRIMARY KEY,
    "idEditorial" INT,
    "idCategoria" INT,
    isbn VARCHAR(20) UNIQUE,
    titulo VARCHAR(255) NOT NULL,
    subtitulo VARCHAR(255),
    anio INT,
    edicion VARCHAR(50),
    paginas INT,
    idioma VARCHAR(50),
    descripcion TEXT,
    estado BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_libro_editorial FOREIGN KEY ("idEditorial")
        REFERENCES biblioteca.editorial ("idEditorial")
        ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT fk_libro_categoria FOREIGN KEY ("idCategoria")
        REFERENCES biblioteca.categoria ("idCategoria")
        ON UPDATE CASCADE ON DELETE SET NULL
);

-- Tabla intermedia: libro_autor (Relación M:N entre libro y autor)
CREATE TABLE IF NOT EXISTS biblioteca.libro_autor (
    "idLibro" INT NOT NULL,
    "idAutor" INT NOT NULL,
    PRIMARY KEY ("idLibro", "idAutor"),
    CONSTRAINT fk_libroautor_libro FOREIGN KEY ("idLibro")
        REFERENCES biblioteca.libro ("idLibro")
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_libroautor_autor FOREIGN KEY ("idAutor")
        REFERENCES biblioteca.autor ("idAutor")
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- Tabla: ejemplar
CREATE TABLE IF NOT EXISTS biblioteca.ejemplar (
    "idEjemplar" SERIAL PRIMARY KEY,
    "idLibro" INT NOT NULL,
    "codigoBarra" VARCHAR(100) UNIQUE,
    ubicacion VARCHAR(100),
    estado BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_ejemplar_libro FOREIGN KEY ("idLibro")
        REFERENCES biblioteca.libro ("idLibro")
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ==========================================================
-- ESQUEMA: inventario
-- ==========================================================

-- Tabla: proveedor
CREATE TABLE IF NOT EXISTS inventario.proveedor (
    "idProveedor" SERIAL PRIMARY KEY,
    ruc VARCHAR(20) UNIQUE,
    "razonSocial" VARCHAR(200) NOT NULL,
    telefono VARCHAR(20),
    correo VARCHAR(100)
);

-- Tabla: compra
CREATE TABLE IF NOT EXISTS inventario.compra (
    "idCompra" SERIAL PRIMARY KEY,
    "idProveedor" INT NOT NULL,
    fecha DATE DEFAULT CURRENT_DATE,
    total DOUBLE PRECISION,
    CONSTRAINT fk_compra_proveedor FOREIGN KEY ("idProveedor")
        REFERENCES inventario.proveedor ("idProveedor")
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Tabla: detalle_compra
CREATE TABLE IF NOT EXISTS inventario.detalle_compra (
    "idDetalleCompra" SERIAL PRIMARY KEY,
    "idCompra" INT NOT NULL,
    "idLibro" INT NOT NULL,
    cantidad INT NOT NULL,
    precio DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_detallecompra_compra FOREIGN KEY ("idCompra")
        REFERENCES inventario.compra ("idCompra")
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_detallecompra_libro FOREIGN KEY ("idLibro")
        REFERENCES biblioteca.libro ("idLibro")
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ==========================================================
-- ESQUEMA: prestamo
-- ==========================================================

-- Tabla: prestamo
CREATE TABLE IF NOT EXISTS prestamo.prestamo (
    "idPrestamo" SERIAL PRIMARY KEY,
    "idCliente" INT NOT NULL,
    "idEmpleado" INT NOT NULL,
    "fechaPrestamo" DATE DEFAULT CURRENT_DATE,
    "fechaDevolucion" DATE NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_prestamo_cliente FOREIGN KEY ("idCliente")
        REFERENCES administracion.cliente ("idCliente")
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_prestamo_empleado FOREIGN KEY ("idEmpleado")
        REFERENCES administracion.empleado ("idEmpleado")
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Tabla: detalle_prestamo
CREATE TABLE IF NOT EXISTS prestamo.detalle_prestamo (
    "idDetalle" SERIAL PRIMARY KEY,
    "idPrestamo" INT NOT NULL,
    "idEjemplar" INT NOT NULL,
    "fechaDevolucionReal" DATE,
    multa DOUBLE PRECISION DEFAULT 0.0,
    estado BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_detalleprestamo_prestamo FOREIGN KEY ("idPrestamo")
        REFERENCES prestamo.prestamo ("idPrestamo")
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_detalleprestamo_ejemplar FOREIGN KEY ("idEjemplar")
        REFERENCES biblioteca.ejemplar ("idEjemplar")
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Tabla: multa
CREATE TABLE IF NOT EXISTS prestamo.multa (
    "idMulta" SERIAL PRIMARY KEY,
    "idDetallePrestamo" INT NOT NULL,
    monto DOUBLE PRECISION NOT NULL,
    motivo VARCHAR(255),
    estado BOOLEAN DEFAULT TRUE,
    fecha DATE DEFAULT CURRENT_DATE,
    CONSTRAINT fk_multa_detalleprestamo FOREIGN KEY ("idDetallePrestamo")
        REFERENCES prestamo.detalle_prestamo ("idDetalle")
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- Tabla: reserva
CREATE TABLE IF NOT EXISTS prestamo.reserva (
    "idReserva" SERIAL PRIMARY KEY,
    "idCliente" INT NOT NULL,
    "idLibro" INT NOT NULL,
    fecha DATE DEFAULT CURRENT_DATE,
    estado BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_reserva_cliente FOREIGN KEY ("idCliente")
        REFERENCES administracion.cliente ("idCliente")
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_reserva_libro FOREIGN KEY ("idLibro")
        REFERENCES biblioteca.libro ("idLibro")
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- ==========================================================
-- ESQUEMA: seguridad
-- ==========================================================

-- Tabla: rol
CREATE TABLE IF NOT EXISTS seguridad.rol (
    id_rol SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

-- Tabla: usuario
CREATE TABLE IF NOT EXISTS seguridad.usuario (
    id_usuario SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    estado VARCHAR(20) DEFAULT 'ACTIVO',
    id_rol INT NOT NULL,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol)
        REFERENCES seguridad.rol (id_rol)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Tabla: modulo
CREATE TABLE IF NOT EXISTS seguridad.modulo (
    id_modulo SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    icono VARCHAR(50),
    ruta VARCHAR(100) NOT NULL,
    orden INT DEFAULT 0,
    estado BOOLEAN DEFAULT TRUE
);

-- Tabla: usuario_permiso
CREATE TABLE IF NOT EXISTS seguridad.usuario_permiso (
    id_usuario_permiso SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_modulo INT NOT NULL,
    puede_leer BOOLEAN DEFAULT FALSE,
    puede_crear BOOLEAN DEFAULT FALSE,
    puede_editar BOOLEAN DEFAULT FALSE,
    puede_eliminar BOOLEAN DEFAULT FALSE,
    puede_imprimir BOOLEAN DEFAULT FALSE,
    CONSTRAINT uq_usuario_modulo UNIQUE (id_usuario, id_modulo),
    CONSTRAINT fk_permiso_usuario FOREIGN KEY (id_usuario)
        REFERENCES seguridad.usuario (id_usuario)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_permiso_modulo FOREIGN KEY (id_modulo)
        REFERENCES seguridad.modulo (id_modulo)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- ==========================================================
-- MIGRACIÓN Y SEED DATA INICIAL PARA SEGURIDAD
-- ==========================================================

-- Asegurar tipo VARCHAR(20) para columna estado si las tablas ya existían previamente con tipo BOOLEAN
ALTER TABLE IF EXISTS seguridad.usuario ALTER COLUMN estado TYPE VARCHAR(20) USING (CASE WHEN estado::text IN ('true', 't', 'ACTIVO') THEN 'ACTIVO' ELSE 'INACTIVO' END);
ALTER TABLE IF EXISTS administracion.persona ALTER COLUMN estado TYPE VARCHAR(20) USING (CASE WHEN estado::text IN ('true', 't', 'ACTIVO') THEN 'ACTIVO' ELSE 'INACTIVO' END);

-- Roles
INSERT INTO seguridad.rol (id_rol, nombre, descripcion)
VALUES 
    (1, 'ROLE_ADMIN', 'Administrador del sistema con acceso total'),
    (2, 'ROLE_BIBLIOTECARIO', 'Gestor de libros, personas y préstamos'),
    (3, 'ROLE_LECTOR', 'Usuario lector con permisos de consulta')
ON CONFLICT (id_rol) DO NOTHING;

-- Modulos del sistema Biblioteca
INSERT INTO seguridad.modulo (id_modulo, nombre, codigo, icono, ruta, orden, estado)
VALUES 
    (1, 'Dashboard', 'MOD_DASHBOARD', 'bi bi-speedometer2', '/dashboard', 1, true),
    (2, 'Gestión de Personas', 'MOD_PERSONAS', 'bi bi-people', '/Personas', 2, true),
    (3, 'Catálogo de Libros', 'MOD_LIBROS', 'bi bi-book', '/Libros', 3, true),
    (4, 'Préstamos y Reservas', 'MOD_PRESTAMOS', 'bi bi-journal-bookmark', '/Prestamos', 4, true)
ON CONFLICT (id_modulo) DO NOTHING;

-- Usuario inicial Admin (password: admin123 -> BCrypt verificado $2a$10$iVK9kD0Q8pP0nJn.y70Jv.3KKHkKSPn9c8j7Bh0erQrmme8R1MLw2)
INSERT INTO seguridad.usuario (id_usuario, username, password, email, estado, id_rol)
VALUES 
    (1, 'admin', '$2a$10$iVK9kD0Q8pP0nJn.y70Jv.3KKHkKSPn9c8j7Bh0erQrmme8R1MLw2', 'admin@biblioteca.org', 'ACTIVO', 1)
ON CONFLICT (id_usuario) DO UPDATE SET password = EXCLUDED.password, estado = EXCLUDED.estado;

-- Permisos granulares para admin
INSERT INTO seguridad.usuario_permiso (id_usuario, id_modulo, puede_leer, puede_crear, puede_editar, puede_eliminar, puede_imprimir)
VALUES 
    (1, 1, true, true, true, true, true),
    (1, 2, true, true, true, true, true),
    (1, 3, true, true, true, true, true),
    (1, 4, true, true, true, true, true)
ON CONFLICT (id_usuario, id_modulo) DO NOTHING;

