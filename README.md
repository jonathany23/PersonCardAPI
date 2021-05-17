# API para gestionar Personas y Tarjetas
## Lista de Tecnologías
* Java
* Spring Boot
* Spring Web
* Spring Security
* Spring Data
* Gradle
* H2 - Database
* Swagger

## Herramientas o Plataformas
* Intellij Idea
* Postman

## Lista de Patrones de Diseño o Metodologias Aplicadas
* Autowired Singletons
* Factory Method Pattern
* Template Method Pattern

## Documentación
Una vez desplegada la aplicación se puede consultar la los endpoints disponible en el la siguiente direccion:
[http://localhost:8080/nuvu/api/swagger-ui.html](http://localhost:8080/nuvu/api/swagger-ui.html)

![alt text](https://github.com/jonathany23/PersonCardAPI/blob/main/src/main/resources/img/swagger.png "Swagger")

___
## Usando el API
### Login
Toda los metodos u opereciones del api reuquieren autenticación.
Se uso el estandar Json Web Token (JWT) para la  gestion de la seguridad.

Por ello lo primero que se requiere hacer es generar el token, esto se lo consigue usando el metodo `authenticate` del API

#### Endpoint
```
http://localhost:8080/nuvu/api/auth/authenticate
```

#### Request Body
```json
{
    "username": "admin",
    "password":"admin"
}
```
#### cURL Request
```bash
curl -X POST \
	-H "Content-type: application/json" \
	-H "Accept: application/json" \
	-d '{ "username": "admin", "password":"admin" }' \
	"http://localhost:8080/nuvu/api/auth/authenticate"
```
#### Response Body
```json
{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyMTI3Nzc2MCwiZXhwIjoxNjIxMzEzNzYwfQ.cq6Wm13Ed8G7DDQrZwmSLczKvSrHIOfeK7fS6LeiXV8"
}
```
El JWT generado es el token que hay que pasar como parametro en todas los otros endpoints como metodo de autenticación

___
### Person
#### Nueva Persona
##### Endpoint
```
http://localhost:8080/nuvu/api/person/save
```
#### Request Body
```json
{
    "legalId": "1020753473",
    "legalIdType": "CC",
    "name": "Testnombre",
    "lastName": "testApellido",
    "phone": "089880889",
    "birthDate": "17/12/1990"
}
```
#### cURL Request
```bash
curl -X POST \
	-H "Content-type: application/json" \
	-H "Accept: application/json" \
	-H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyMTI3Nzc2MCwiZXhwIjoxNjIxMzEzNzYwfQ.cq6Wm13Ed8G7DDQrZwmSLczKvSrHIOfeK7fS6LeiXV8" \
	-d '{ "legalId": "1020753473", "legalIdType": "CC", "name": "Testnombre", "lastName": "testApellido", "phone": "089880889", "birthDate": "17/12/1990" }' \
	"http://localhost:8080/nuvu/api/person/save"
```
#### Response Body
```json
{
  "legalId": "1020753473",
  "legalIdType": "CC",
  "name": "Testnombre",
  "lastName": "testApellido",
  "phone": "089880889",
  "birthDate": "17/12/1990",
  "cards": null
}
```
___
#### Editar Persona
##### Endpoint
```
http://localhost:8080/nuvu/api/person/edit
```
#### Request Body
```json
{
    "legalId": "1020753473",
    "legalIdType": "CC",
    "name": "Juan",
    "lastName": "Garcia"
}
```
#### cURL Request
```bash
curl -X PATCH \
	-H "Content-type: application/json" \
	-H "Accept: application/json" \
	-H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyMTI3Nzc2MCwiZXhwIjoxNjIxMzEzNzYwfQ.cq6Wm13Ed8G7DDQrZwmSLczKvSrHIOfeK7fS6LeiXV8" \
	-d '{ "legalId": "1020753473", "legalIdType": "CC", "name": "Juan", "lastName": "Garcia" }' \
	"http://localhost:8080/nuvu/api/person/edit"
```
#### Response Body
```json
{
  "legalId": "1020753473",
  "legalIdType": "CC",
  "name": "Juan",
  "lastName": "Garcia",
  "phone": null,
  "birthDate": null,
  "cards": null
}
```
___

#### Obtener Persona
##### Endpoint
```
http://localhost:8080/nuvu/api/person/{person_legel_id}
```
#### Request Body
```json
No request Body
```
#### cURL Request
```bash
curl -X GET \
	-H "Content-type: application/json" \
	-H "Accept: application/json" \
	-H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyMTI3Nzc2MCwiZXhwIjoxNjIxMzEzNzYwfQ.cq6Wm13Ed8G7DDQrZwmSLczKvSrHIOfeK7fS6LeiXV8" \
	"http://localhost:8080/nuvu/api/person/001"
```
#### Response Body
```json
{
  "legalId": "001",
  "legalIdType": "CC",
  "name": "testname",
  "lastName": "testlastname",
  "phone": "089880889",
  "birthDate": "17/12/2015",
  "cards": [
    {
      "cardNumber": "1234567890123456",
      "expirationDate": "05/28",
      "securityCode": "123",
      "cardholdername": "test card name",
      "personId": "001"
    }
  ]
}
```
___
#### Elimanr Persona
##### Endpoint
```
http://localhost:8080/nuvu/api/person/delete/{person_legel_id}
```
#### Request Body
```json
No request Body
```
#### cURL Request
```bash
curl -X DELETE \
	-H "Content-type: application/json" \
	-H "Accept: application/json" \
	-H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyMTI3Nzc2MCwiZXhwIjoxNjIxMzEzNzYwfQ.cq6Wm13Ed8G7DDQrZwmSLczKvSrHIOfeK7fS6LeiXV8" \
	"http://localhost:8080/nuvu/api/person/delete/1020753473"
```
#### Response Body
```json
true
```
