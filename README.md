
# Documentación de API Rest Inventario

Se realizo un CRUD utilizando la implementacion de JWT Y Spring Boot.

## Tecnologias utilizadas:
* Spring Boot 
* Spring Security
* jsonwebtoken
* Hibernate
* MYSQL
* Lombok
* Jakarta

## MODELO BASE DE DATOS
![Screenshot 2024-05-30 225546](https://github.com/JavierlNeira/JAVA-INVENTARIO-FINAL/assets/114690233/f895cb6b-0664-495c-bdec-0519d44d6f84)
## Accesos
Tener en cuenta la configuracion de la base de datos, en este caso la realice en mi localhost y el nombre del esquema es PROYECTO_FINAL, pido por favor colocar los datos respectivos de la base de datos MYSQL donde se requieren hacer pruebas

```javascript
spring.datasource.url= jdbc:mysql://localhost:3306/PROYECTO_FINAL
spring.datasource.username=root
spring.datasource.password=12345
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update

```

## Acceso a la UI de la documentación de la API mediante POSTMAN COLLECTION 

Adjunto link a la coleccion de Postman para poder ejecutar las APIs
```
https://github.com/JavierlNeira/JAVA-INVENTARIO-FINAL/blob/a917e9f9d2d12ebf9d1b1c8e71329c509cddb224/API_INVENTARIO_PRODUCTOS.postman_collection.json
```
Pido mil disculpas no tener la documentacion en Swagger, tuve un incoveniente y por mas que intente cargar las APIs nunca lo hizo, creo que es tema del JWT, intente desviar la ip de swagger para que estuviera fuera de JWT pero no me funciono

## EXPLICACION APIs Postman - Funcionamiento.
Se ejecuta Aplicacion luego nos dirigimos a las MICROSERVICOS:
![image](https://github.com/JavierlNeira/JAVA-INVENTARIO-FINAL/assets/114690233/7efd7819-9ffd-4fbc-b658-0a3318475aa4)

Primero debemos ir a la carpeta LOGIN y luego a CREAR USUARIO

![image](https://github.com/JavierlNeira/JAVA-INVENTARIO-FINAL/assets/114690233/7b6ef2b9-c9db-4d98-97b5-883eeaa30b30)

## POST
```
http://localhost:8080/auth/register
```
JSON PARA CREAR USUARIO:
```
{
    "username":"Jneira",
    "password":"123456",
    "role":"admin",
    "lastname":"Neira",
    "firstname":"Javier"
}
```
La respuesta de este Microservicio generara el token para acceder a los demas Microservicios, este MS crea el usuario  y lo almacena en la base de datos y la password la encripta en base 64 para mayor proteccion:
![image](https://github.com/JavierlNeira/JAVA-INVENTARIO-FINAL/assets/114690233/03863430-6faa-4e6a-a1c0-504da139066b)
![image](https://github.com/JavierlNeira/JAVA-INVENTARIO-FINAL/assets/114690233/c9aaf707-642f-42e5-9622-d40c2288346e)

El otro microservicio genera el token, dado que ya existe el usuario no se debe utilizar el de creación... El token tiene una duracion de 24 minutos, expirado ese tiempo se invoca el MS para generar nuevo token

## POST
```
http://localhost:8080/auth/login
```
JSON PARA GENERAR TOKEN 
```
{
    "username":"Jneira",
    "password":"123456"
}
```
Respuesta:
![image](https://github.com/JavierlNeira/JAVA-INVENTARIO-FINAL/assets/114690233/4791b9bf-6153-4c03-931f-c0fd25d49be8)


Una vez generado el token podemos invocar a los demas Microservicios.

## Lógica de Negocio.

Este api Rest se encargara de crear productos y categorias para los productos, lo primero que debemos hacer es crear la categoria dado que el body de creacion producto tiene el id_categoria, asi que realice una validacion de que la categoria debe existir antes de crear el producto:

![image](https://github.com/JavierlNeira/JAVA-INVENTARIO-FINAL/assets/114690233/a651f5a8-daa2-430e-b174-28f2f6261d10)

La imagen anterior visualiza todos los Mircroservicios

La carpeta Categoria tiene 4 MS

![image](https://github.com/JavierlNeira/JAVA-INVENTARIO-FINAL/assets/114690233/4c103aa2-fe78-4e22-830a-e7e1d5c29273)

Crea - Leer - Actualizar - Eliminar.

Cada Microservicio tiene validaciones internas, que el tipo de campo sea el correcto, que se envien todos los parametros, si se quiere eliminar una categoria esta debe existir en la tabla categoria, de lo contrario nos muestre un mensaje de error.

Veamos solo un ejemplo para validar lo anterior:

Primero colocamos el token

![image](https://github.com/JavierlNeira/JAVA-INVENTARIO-FINAL/assets/114690233/08d5388e-cd97-4b76-95a0-84bbdf073ef2)

## POST
```
http://localhost:8080/api/v1/categorias
```
JSON PARA GENERAR TOKEN 
```
{
"nombre_categoria":"122",
"nombre_proveedor_categoria":"121"
}
```
En el body anterior envio numeros

![image](https://github.com/JavierlNeira/JAVA-INVENTARIO-FINAL/assets/114690233/a69a8965-1ba5-4a4c-98c1-0da8a8e58ec6)

Si envio campo vacio

![image](https://github.com/JavierlNeira/JAVA-INVENTARIO-FINAL/assets/114690233/ba70ab94-29a7-403a-a8f3-6ce83549a52a)

Como vemos no nos permite crear la categoria

Si envio correctamente el MS:
![image](https://github.com/JavierlNeira/JAVA-INVENTARIO-FINAL/assets/114690233/b6171a57-86a4-4980-a877-0a0913eb5c8b)

Correcto y crea el registro en la base de datos:

![image](https://github.com/JavierlNeira/JAVA-INVENTARIO-FINAL/assets/114690233/8a2a49e0-c00c-4b4b-ae71-f058af1854a4)

### Conclusión

Todos los Microservicios contienen validaciones y se debe respetar el orden de los APIs, primero crear categoria y luego el producto, ya luego podemos eliminar, actualizar, listar; El parametro de busqueda de los MS es a partir de ID, este ID se genera automaticamente, y para poder ver cuales hay, se invoca el Ms de traer la info sea categoria o producto.

Muchas Gracias



