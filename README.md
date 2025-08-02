# API ForoHub

![Estado del Proyecto](https://img.shields.io/badge/status-finalizado-green)
![Licencia](https://img.shields.io/badge/license-MIT-blue)

API REST para un sistema de foro, desarrollada como parte del Desafío del programa Oracle Next Education (ONE) en Alura Latam. La API permite a los usuarios autenticarse y gestionar tópicos de discusión (Crear, Leer, Actualizar y Eliminar).

## 🚀 Funcionalidades Principales

-   **Autenticación de Usuarios:** Sistema de login seguro utilizando JSON Web Tokens (JWT).
-   **Gestión de Tópicos (CRUD):**
    -   `POST`: Crear un nuevo tópico.
    -   `GET`: Listar todos los tópicos de forma paginada y ordenada.
    -   `GET`: Ver el detalle de un tópico específico por su ID.
    -   `PUT`: Actualizar la información de un tópico existente.
    -   `DELETE`: Eliminar un tópico de la base de datos (eliminación física).
-   **Validaciones:** Se aplican validaciones a nivel de DTOs para asegurar la integridad de los datos según las reglas de negocio.
-   **Base de Datos:** Persistencia de datos en MySQL, con gestión de migraciones a través de Flyway.

---

## 🛠️ Tecnologías Utilizadas

-   **Java 17**
-   **Spring Boot 3:**
    -   Spring Web: Para la construcción de endpoints REST.
    -   Spring Data JPA: Para la persistencia de datos.
    -   Spring Security: Para la implementación de la seguridad y autenticación con JWT.
    -   Bean Validation: Para las validaciones de negocio.
-   **Lombok:** Para reducir el código boilerplate en las clases.
-   **MySQL:** Como motor de base de datos relacional.
-   **Flyway:** Para el versionado y control de las migraciones de la base de datos.
-   **Maven:** Como gestor de dependencias del proyecto.

---

## ⚙️ Instalación y Puesta en Marcha

Sigue estos pasos para levantar el proyecto en tu entorno local.

**1. Clonar el Repositorio**
```bash
git clone [https://github.com/tu-usuario/foro-hub-api.git](https://github.com/tu-usuario/foro-hub-api.git)
cd foro-hub-api
```

**2. Configurar la Base de Datos**
Asegúrate de tener MySQL instalado. Crea una base de datos con el nombre que prefieras, por ejemplo `forohub_db`.
```sql
CREATE DATABASE forohub_db;
```

**3. Configurar Variables de Entorno**
Abre el archivo `src/main/resources/application.properties` y modifica las siguientes líneas con tus credenciales:

```properties
# Configuración de la Base de Datos
spring.datasource.url=jdbc:mysql://localhost/forohub_db
spring.datasource.username=tu_usuario_mysql
spring.datasource.password=tu_contraseña_mysql

# Configuración del Secreto de JWT
api.security.secret=${JWT_SECRET:mi-palabra-secreta-12345}
```
**Nota:** Es una buena práctica manejar el secreto de JWT como una variable de entorno del sistema.

**4. Ejecutar la Aplicación**
Puedes ejecutar la aplicación usando el wrapper de Maven:
```bash
./mvnw spring-boot:run
```
O directamente desde tu IDE (IntelliJ IDEA, Eclipse, etc.). La aplicación estará disponible en `http://localhost:8080`.

---

## 🔌 API Endpoints

La API cuenta con los siguientes endpoints. Para acceder a las rutas privadas, es necesario obtener un token JWT a través del endpoint `/login` y enviarlo en la cabecera de las peticiones como `Authorization: Bearer <token>`.

| Verbo   | Endpoint         | Descripción                           | Acceso  |
| :------ | :--------------- | :------------------------------------ | :------ |
| `POST`  | `/login`         | Autentica a un usuario y devuelve un token JWT. | Público |
| `POST`  | `/topicos`       | Crea un nuevo tópico.                 | Privado |
| `GET`   | `/topicos`       | Lista todos los tópicos (paginado).   | Privado |
| `GET`   | `/topicos/{id}`  | Muestra los detalles de un tópico.    | Privado |
| `PUT`   | `/topicos/{id}`  | Actualiza un tópico existente.        | Privado |
| `DELETE`| `/topicos/{id}`  | Elimina un tópico existente.          | Privado |

### Ejemplo: Login de Usuario

**Petición `POST /login`**
```json
{
  "correoElectronico": "test@forohub.com",
  "contrasena": "12345"
}
```
**Respuesta `200 OK`**
```json
{
  "jwtToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Ejemplo: Crear un Tópico

**Petición `POST /topicos`** (con token en la cabecera `Authorization`)
```json
{
  "titulo": "¿Cuál es la mejor forma de manejar excepciones en Spring Boot?",
  "mensaje": "Quisiera saber las mejores prácticas para un manejo de errores global en una API REST.",
  "idAutor": 1,
  "idCurso": 1
}
```
**Respuesta `201 Created`**
```json
{
    "id": 1,
    "titulo": "¿Cuál es la mejor forma de manejar excepciones en Spring Boot?",
    "mensaje": "Quisiera saber las mejores prácticas para un manejo de errores global en una API REST.",
    "fechaCreacion": "2025-08-02T18:30:00",
    "status": "NO_RESPONDIDO",
    "autor": "Usuario de Prueba",
    "curso": "Spring Boot"
}
```

---

## 👨‍💻 Autor

**Marco Hernández**

-   LinkedIn: [https://www.linkedin.com/in/marco-hernandez-arenas/](https://www.linkedin.com/in/marco-hernandez-arenas/)
-   GitHub: [https://github.com/Marcoherna](https://github.com/Marcoherna)

---

## 📜 Licencia

Este proyecto está bajo la Licencia MIT.