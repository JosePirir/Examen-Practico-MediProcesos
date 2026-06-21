CREATE TABLE rol (
  id_rol INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  descripcion VARCHAR(100)
);

CREATE TABLE usuario (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  correo VARCHAR(100),
  usuario VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(100) NOT NULL,
  id_rol INT,
  FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

INSERT INTO rol (nombre, descripcion) VALUES
('Administrador', 'Acceso total al sistema'),
('Usuario', 'Acceso limitado');

-- Nota: el usuario administrador inicial ("admin" / "admin123") se crea
-- automáticamente al arrancar el backend, mediante un componente
-- DataSeeder (Jakarta EE @Singleton @Startup) que verifica si ya existe
-- y, de no ser así, lo inserta con su contraseña ya hasheada (BCrypt).
-- Esto evita guardar contraseñas en texto plano en cualquier script SQL.