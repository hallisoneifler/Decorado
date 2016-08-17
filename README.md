## Decorado

#### Este repositório contem um projeto back-end Java(app_decorado) e o front-end desenvolvido em AngularJs.
#### Faça o download e siga as instruções abaixo.

##### Instalando o Back End

###### Pré-requisito
- Maven(https://maven.apache.org/download.cgi).

- Wildfly 9(http://wildfly.org/downloads/)

- Banco de Dados

  - Foi utilizado Mongodb para esse projeto, então baixe o Mongo(https://www.mongodb.com/download-center#community)
e crie uma base de dados chamada "app_decorado".
  - No momento em que a aplicação iniciar vai ser criado um usuario Admin para entrar no sistema.
  - Email: admin@gmail.com
  - Senha: 1234


###### Depois basta abrir o terminal
- Entrar na pasta do projeto e digitar
- mvn clean install
- Após o processo de build, navegue até /.m2/repository/app/decorado/app_decorado/0.0.1-SNAPSHOT
encontreo WAR da aplicação e copie na pasta ~/wildfly-9.0.2.Final/standalone/deployments do seu servidor Wildfly.

##### Instalando o  Front End

- Entre na sub-pasta (/front-end) do projeto e digite 
- bower install
- gulp install
- gulp build


