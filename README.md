# Informacion general

## Proyecto de practica de spring security

Este proyecto tiene como finalidad implementar spring security para la procteccion y generacion de jwt asi como tambien
la validacion de los endpoints medienta los roles de los usuarios, se tiene una BD H2 para esta demo.

*****

### Versiones utilizadas

* java 17
* spring boot 3.0.7

*****

### Probar app

Para poder probar el app se necesita un cliente rest ya sea postman o algun otro software pare el consumo de servicios
rest.

* La aplicacion levanta por default en el puerto 8080.
* Para generar un token se utiliza el endpoint localhost:8080/login que es un POST y recibe como cuerpo un username y
  password
    * {
      "username": "any2",
      "password": "1234"
      }
* Se tienen 3 usuarios para probar
    * Admin
        * usuario = any1
        * password = 1234
    * User
        * usuario = any2
        * password = 1234
    * Invitado
        * usuario = any3
        * password = 1234

------------------

### Listado de servicios

* crear usuario POST -> URL http://localhost:8080/v1/createU
    * Body
        * {
          "email": "any@email.com",
          "username": "any1",
          "password": "1234",
          "roles": [
          "USER"
          ]
          }
* eliminar usuario DELETE -> URL http://localhost:8080/v1/deleteU?id=1
    * Body
        * NA
* listar usuarios GET -> URL http://localhost:8080/v1/all
    * Body
        * NA




