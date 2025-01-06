# Travel Manager Backend

## Getting Started

### Prerequisitos

- Java 17
- Maven
- Docker
- Docker Compose

### Instalación

1. Clonar el repositorio
2. Si se utiliza IntelliJ, el IDE instalará las dependencias automáticamente, caso contrario ejecutar el comando `mvn clean install` para instalar las dependencias.

### Base de datos

La base de datos se encuentra en el archivo `compose.yml`. Para iniciar la base de datos:

- Ejecutar el comando `docker compose up -d`, desde la carpeta raíz del proyecto.

Notas:
Aunque se recomienda utilizar Docker para iniciar la base de datos, si no utiliza Docker, crear una instancia de PostgreSQL en su máquina local. Con las credenciales de la base de datos que se encuentran en el archivo `src/main/resources/application.properties`.

### Ejecución
Una vez que se hayan instalado las dependencias del proyecto, así como iniciada la base de datos.
Ejecutar la aplicación.

Para validar si el proyecto se ejecuto correctamente dirigirse al navegador y abrir la siguiente URL: `http://localhost:8080/swagger-ui/index.html`. En la que se podrán visualizar los endpoints disponibles para la aplicación.

### Documentación

Para obtener la documentación de la API, dirigirse al navegador y abrir la siguiente URL: `http://localhost:8080/v3/api-docs`.

### Autenticación

Para autenticarse en la aplicación, se debe utilizar el endpoint `/auth/login` con el siguiente JSON:

```json
{
  "email": "admin@admin.com",
  "password": "admin123"
}
```
Este usuario es creado automáticamente cuando se inicia la aplicación.

El endpoint devuelve un token JWT que se debe utilizar en los headers de las peticiones a los endpoints de la API.



