# API de Clientes - Spring Boot

## Requisitos
- Java 17+
- Maven 3.6+
- Puerto 8090 disponible

## Instalación
```bash
mvn clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```
## Uso

Endpoint principal:
```bash
GET /api/customers/{C|P}/{documentNumber}
```
Ejemplo:
```bash
http://localhost:8090/api/customers/C/23445322
```
##Configuración

Editar: src/main/resources/application.properties
```bash
server.port=8090
```

Pruebas

Ejecutar pruebas unitarias:
```bash
mvn test
```