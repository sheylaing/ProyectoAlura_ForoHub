<h1 align="center"> Challenge BackEnd N°3: ForoHub</h1>

Este proyecto consiste en el desarrollo de una API con Spring Boot y MySQL que permite crear, editar, eliminar tópicos e interactuar con ellos gestionandolos.


## :computer:Tecnologías utilizadas

* Java 17
* Maven
* Spring Boot
* Spring Data JPA
* MySQL
* JWT (Jason Web Token)
* Springdoc OpenAPI


##  :computer:Instalación  

_Sigue estos pasos para configurar y ejecutar el proyecto en tu máquina local._

**Dependencias** 

Asegúrate de agregar las dependencias necesarias en tu archivo pom.xml para la base de datos que deseas usar.

**Configurar tu base de datos en el archivo application.properties** 

```
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.datasource.url=jdbc:mysql://localhost:3306/Nombre_Base_Datos?createDatabaseIfNotExist=true
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```


**Nota adicional**

Asegúrate que el usuario de la BD tenga los permisos y/o privilegios necesarios para que puedas crear los objetos de Base de Datos desde el IDE que utilices.



---
Realizado por :hearts: Sheyla Marin :hearts: (https://github.com/sheylaing) :blush:
