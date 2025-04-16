# Prueba tecnica MyHotel

## Descripcion del Proyecto
Microservicio desarrollado en Spring Boot para la gestión de vehículos (automóviles y camiones) y sus respectivas mantenciones.
El sistema permite a los usuarios gestionar la información de los vehículos, incluyendo la creación, actualización y eliminación de registros.


## 🧩 Estructura del Proyecto
    
    ├── backend
    │   ├── src
    │   │   ├── main
    │   │   │   ├── java
    │   │   │   │   └── com
    │   │   │   │       └── myhotel
    │   │   │   │           ├── controller
    │   │   │   │           ├── model
    │   │   │   │               ├── dto
    │   │   │   │               ├── response
    │   │   │   │           ├── projection
    │   │   │   │           ├── repository
    │   │   │   │           ├── service

## ⚙️ Configuración del Ambiente

### Requisitos

- Java 21
- Maven 3.8+
- Docker (opcional, para contenedores)
- IDE: IntelliJ / VS Code (recomendado)

### Propiedades principales (`application.properties`)

```properties
spring.application.name=ms-test-myhotel

spring.h2.console.enabled=true

spring.datasource.url=jdbc:h2:mem:myhotel-test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.defer-d-datasource-init=true

server.port=8082
server.servlet.context-path=/v1

springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```


## 🚀 Endpoints Disponibles

### Vehículos
- `PUT /v1/vehiculos/updateVehiculo/{patente}`  
  Actualiza un vehículo identificado por su patente.

- `POST /v1/vehiculos/createVehiculo`  
  Crea un nuevo vehículo. El body debe contener la información específica según si es automóvil o camión.
    Ejemplos del JSON:
    ```json
    {
    "tipoVehiculo": "AUTOMOVIL",
    "tipo":"Sedan",
    "marca": "Toyota",
    "modelo": "Corolla",
    "patente": "ABC123",
    "anioFabricacion": "2021-01-01",
    "kilometraje": 5000,
    "cilindrada": "1.6",
    "cantPuertas": 4,
    "cantPasajeros": 5,
    "cantMaletero": 350
    }
    ```

    ```json
    {
    "tipoVehiculo": "CAMION",
    "tipo": "tolva",
    "marca": "Chevrolet",
    "modelo": "Tracker",
    "patente": "XYZ789",
    "anioFabricacion": "2024-10-28",
    "kilometraje": 75000,
    "cilindrada": "12.0",
    "capacidadToneladas": 20,
    "cantEjes": 4
    }
    ```

- `GET /v1/vehiculos/getVehiculos`  
  Obtiene todos los vehículos registrados.

- `GET /v1/vehiculos/getVehiculos/{patente}`  
  Obtiene un vehículo específico por su patente.
- `DELETE /v1/vehiculos/deleteVehiculo/{patente}`  
  Elimina un vehículo por su patente.

---

### Mantenciones
- `POST /v1/mantenciones/createMantencion/{patente}`  
  Registra una mantención para un vehículo.
    Ejemplo del JSON:
    ```json
    {
      "fechaMantencion": "2025-04-10",
      "descripcion": "Se cambia la rueda",
      "costo": 20000
    }
    ```

- `GET /v1/mantenciones/getMantenciones/{patente}`  
  Lista todas las mantenciones asociadas a una patente.
- `DELETE /v1/mantenciones/deleteMantencion/{patente}/{id}`  
  Borra una mantención específica por su ID y patente.

---

### Empleados y Querys solicitadas
Se detalla a continuacion las querys con sus respectivos endpoints asociados al enunciado del test:

- `GET /v1/empleados/getSegmentoSueldoDepartamento`  
  Punto 2: Utilizando la tabla “departments” se requiere realizar la misma agrupación de segmentos de sueldo, pero por departamento..

- `GET /v1/empleados/getSalarioPromedioPorDepartamento`  
  Punto 5: Salario promedio de todos los departamentos que tengan más de 10 empleados.

- `GET /v1/empleados/getMayorSueldoEmpleado`  
  Punto 3:Información del empleado con mayor sueldo de cada departamento.

- `GET /v1/empleados/getEmpleadosPorSegmentoSueldos`  
  Punto 1: Obtener cantidad de empleados dentro de los siguientes segmentos de sueldo.

- `GET /v1/empleados/getEmpleadosPorPais`  
  Punto 6: Obtener la siguiente información agrupada por país: cantidad empleados, salario promedio, salario más alto, salario más bajo, promedio años antigüedad

- `GET /v1/empleados/getEmpleadosGerenteTiempo`  
  Punto 4: Información de los gerentes que hayan sido contratados hace más de 15 años.

---

### Configuracion para ejecutar el microservicio
Para configurar el microservicio dispone de dos alternativas:

1) Ejecutar el microservicio desde su IDE (IntelliJ o VS Code) como una aplicacion Spring Boot.

```sh
$ mvn clean install
```

```sh
$ mvn spring-boot:run
```
Esto iniciara el microservicio en el puerto 8082, y la documentacion de la API estara disponible en [http://localhost:8082/v1/swagger-ui.html](http://localhost:8082/v1/swagger-ui.html).

2) Ejecutar el microservicio utilizando Docker (el mismo ya cuenta con un dockerfile).

```sh
$ sudo docker build -t ms-test-myhotel:latest .
```

```sh
$ sudo docker run -p 8082:8082 ms-test-myhotel:latest
```
Esto iniciara el microservicio en el puerto 8082, y la documentacion de la API estara disponible en [http://localhost:8082/v1/swagger-ui.html](http://localhost:8082/v1/swagger-ui.html).

---
### Documentacion de la API
La documentacion de la API esta disponible en [http://localhost:8082/v1/swagger-ui.html](http://localhost:8082/v1/swagger-ui.html).

## License

Santiago Suarez
