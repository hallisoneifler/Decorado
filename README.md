# Decorado

Este repositório contem um projeto back-end Java(app_decorado) e o front-end desenvolvido em AngularJs.
Fazer o download dos dois repositorios.

- Back End

Pre requisito
Maven(https://maven.apache.org/download.cgi).
No terminal, entre na pasta do projeto e digite

mvn clean install

Apos o processo de build, va ate /.m2/repository/app/decorado/app_decorado/0.0.1-SNAPSHOT
encontreo WAR da aplicação e copie na pasta deployments do seu servidor Wildfly.

- Front End

Entre na pasta do projeto e digite

bower install

gulp install

gulp build



- Banco de Dados

Foi utilizado Mongodb para esse projeto, então baixe o Mongo(https://www.mongodb.com/download-center#community)
e crie uma base de dados chamada "app_decorado".

No momento em que a aplicação iniciar vai ser criado um usuario Admin para entrar no sistema.

Email:admin@gmail.com
Senha:1234






