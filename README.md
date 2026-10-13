# Clientes API

API REST para la gestión de clientes con autenticación JWT y control de acceso por roles, hecha con Spring Boot y MySQL.

> Frontend de este proyecto: [clientes-frontend](https://github.com/DanielIncio/clientes-frontend)

## Tecnologías

- Java 17 *(verifica el valor `java.version` en tu `pom.xml`)*
- Spring Boot, Spring Web, Spring Data JPA, Spring Security
- MySQL
- JWT (JJWT 0.11.5)
- Maven
- Postman (pruebas)

## Funcionalidades

- CRUD completo de clientes
- Registro y login de usuarios con contraseñas encriptadas (BCrypt)
- Autenticación stateless con token JWT (expira en 1 hora)
- Roles y permisos: Utilizados para la autorizacion.
- Manejo centralizado de errores con respuestas JSON (`@RestControllerAdvice`)

## Cómo ejecutarlo

### Requisitos

- Java 17 o superior
- Maven
- MySQL

### Pasos

1. Clona el repositorio:
   ```bash
   git clone https://github.com/DanielIncio/clientes-api.git
   cd clientes-api
   ```
2. Crea una base de datos vacía en MySQL (las tablas las crea Hibernate al iniciar):
   ```sql
   CREATE DATABASE clientes_db;
   ```
3. Copia el archivo de configuración de ejemplo y completa tus datos:
   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```
   Edita `application.properties` con tu usuario y contraseña de MySQL y un `jwt.secret` propio.
4. Ejecuta el proyecto:
   ```bash
   mvn spring-boot:run
   ```
   La API queda disponible en `http://localhost:8080`.

## Endpoints

| Método | Ruta | Descripción | Requiere token |
|---|---|---|---|
| POST | `/usuarios/registro` | Registrar usuario | No |
| POST | `/usuarios/login` | Iniciar sesión (devuelve JWT) | No |
| GET | `/clientes` | Listar clientes | Sí |
| GET | `/clientes/{id}` | Buscar cliente por id | Sí |
| POST | `/clientes` | Crear cliente | Sí |
| PUT | `/clientes/{id}` | Actualizar cliente | Sí |
| DELETE | `/clientes/{id}` | Eliminar cliente | Sí (`<rol>`) |

El token se envía en el header de cada petición protegida:

```
Authorization: Bearer <token>
```

### Ejemplo: login

```json
POST /usuarios/login
{
  "username": "admin",
  "password": "123456"
}
```

Respuesta: un JWT que se usa en las demás peticiones.

## Estructura del proyecto

```
config/       → SecurityConfig
controller/   → AuthController, ClienteController
service/      → AuthService, ClienteService, JwtService, UsuarioDetailsService
repository/   → ClienteRepository, UsuarioRepository
model/        → Cliente, Usuario
filter/       → JwtAuthFilter
exception/    → ManejadorErrores
```

## Qué aprendí

- Arquitectura por capas (controller, service, repository)
- Autenticación stateless con JWT y filtros de Spring Security
- Encriptación de contraseñas con BCrypt
- Manejo de errores centralizado y códigos HTTP correctos

## Autor

**Daniel Incio** · [GitHub](https://github.com/DanielIncio)
