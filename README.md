# SPRING WEB CON MARIADB

## Descripción del Proyecto

Este proyecto es una aplicación web desarrollada con Spring Framework y MariaDB como base de datos. La aplicación permite gestionar libros y usuarios, proporcionando funcionalidades como inserción, consulta, y autenticación de usuarios.

## Endpoints de `BookController`

- **`GET /`**: Muestra un mensaje de bienvenida en la página principal.
- **`GET /index`**: Página principal después de iniciar sesión.
- **`GET /insert`**: Muestra un formulario para insertar un nuevo libro.
- **`POST /insert`**: Procesa la inserción de un nuevo libro en la base de datos.
- **`GET /list`**: Lista todos los libros disponibles en la base de datos.
- **`GET /find`**: Muestra un formulario para buscar un libro por su ID.
- **`POST /find`**: Busca un libro por su ID y muestra el resultado.

## Endpoints de `UserController`

- **`GET /user/login`**: Muestra el formulario de inicio de sesión.
- **`POST /user/login`**: Valida las credenciales del usuario y redirige a la página principal si son correctas.
- **`GET /user/logout`**: Cierra la sesión del usuario y redirige al formulario de inicio de sesión.
- **`GET /user/sing-in`**: Muestra el formulario de registro de usuario.
- **`POST /user/sign-in`**: Registra un nuevo usuario y redirige al formulario de inicio de sesión.

## Scripts para la base de datos:

Primero crear la base de datos

```mysql
CREATE DATABASE llibreria_smc;
USE llibreria_smc;
```

El programa usa el usuario spring, con contraseña system para conectarse a la base de datos. <br><br>

Los `schemas` DDL se encuentrarn en resources/schema.sql, pero también los muestro aqui:

```mysql

CREATE TABLE IF NOT EXISTS books (
  book_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255),
  author VARCHAR(255),
  publisher VARCHAR(255),
  publication_date DATE,
  genre VARCHAR(255),
  isbn VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
  user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(40) UNIQUE,
  password VARCHAR(64)
)

```

Si se desea, se pueden agregar inserts para los usuarios que pueden acceder a la libreria, o se puede usar la
página para registrar nuevos usuarios /user/sign-in

## Preguntas de reflexión y analisis

---
### Per què al servei estem utilitzant mètodes que no hem declarat explícitament al repositori? Com és possible?

La respuesta a esto es que el repositorio extiende otra interfaz que contiene estos otros métodos.
Si no les hacemos `@Override` los métodos seguiran ahi con la misma estructura y el mismo return type.

---
### El repositori pot triar fer l’extends de les interfícies PagingAndSortingRepository o de JpaRepository. En què es diferencien aquestes dues amb la interfície CrudRepository?

Efectivamente el repositorio puede extender otras interficies definidas por Spring. La de JPA contiene unos metodos más orientados
a la métodologia/ideologia del JPA, sus estados de transient, y usa otros Objetos que ahora mismo no se que son, como lo es
el Example. El PagingAndSorting supongo que es solo para hacer selects, porque solo veo dos métodos find, pero uno de esos,
retorna un Objeto Page, que podria servir para paginar los resultados (cosa que yo hago mucho con mi objeto paginator :d)

---
### Què significa Optional<Class> i per a què serveix?

Sirve para envolver un valor en otro objeto que se encarga de informar si el valor existe o no.
Facilita mucho en el código para leer y manejar los valores y evitar errores de null pointer. Tiene el método `isPresent()` que
devuelve boolean cuando el valor de dentro no está o debia ser null. En código tuve que implementar uno y en vez de
envolver un null o algo asi, se retorna un Optional.empty(). Y en caso de que exista se hace un `Optional.of()` creo.

---
### Per què el controlador utilitza el servei i no la seva implementació? 

El controlador utiliza el servicio porque así se separa la lógica de negocio de los detalles concretos de la implementación. Esto hace que el código sea más fácil de mantener y probar, ya que se puede cambiar cómo funciona el servicio sin necesidad de modificar el controlador. Además, este enfoque sigue buenas prácticas de diseño, como programar contra interfaces en lugar de implementaciones, lo que ayuda a que la aplicación sea más flexible y escalable.





