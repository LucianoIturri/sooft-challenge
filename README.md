# SOOFT-challenge
Technical assessment for SOOFT

## Descripción

Esta es una API para gestionar información de empresas y transferencias. Para interactuar con la aplicación y probar los diferentes endpoints, puedes utilizar Swagger.

## Swagger UI

Puedes ver la documentación interactiva de los endpoints en Swagger accediendo a la siguiente URL:

[http://localhost:8080/swagger-ui/index.html]

## Endpoints

A continuación se detallan los endpoints disponibles en la API:

- **POST /api/v1/enterprise/create**: Adhesión de una nueva empresa.
- **GET /api/v1/enterprise/newer**: Obtener las empresas recientemente adheridas.
- **GET /api/v1/enterprise/last-transfers**: Obtener las transferencias realizadas en el último mes.
- **POST /api/v1/transfer/create**: Crear una nueva transferencia (Este endpoint es adicional y no estaba solicitado originalmente, pero es necesario para agregar transferencias a través de la API).

## Tech Stack

La aplicación está construida utilizando las siguientes tecnologías:

- **Java 17**
- **PostgreSQL**
- **Docker**
- **Docker Compose**

## Levantar la aplicación con Docker

Para poder levantar la aplicación utilizando Docker, sigue los siguientes pasos:

1. Asegúrate de tener Docker y Docker Compose instalados en tu máquina.
2. Da permisos al script `build.sh` para que sea ejecutable con el siguiente comando:

   ```bash
   chmod +x build.sh

