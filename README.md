# Zip Code Finder

Projeto desenvolvido em Java 11, que realiza consultas por endereços através de seu CEP. Caso não encontre com um determinado CEP, substitui 1 dígito da direita para a esquerda e tenta novamente. Por exemplo: dado 12345678 e não encontrar, tenta 12345670, depois 12345600, e assim por diante.

## O projeto foi desenvolvido com uso de Spring Boot e Maven, baseado em princípios de Domain Driven Design e é dividido em quatro módulos:

* context: classes de repositório / acesso à dados
* core: classes de aplicação e serviços de aplicação
* domain: classes de domínio, entidades e serviços de domínio
* webapp: classes de recursos REST

## A busca é feita utilizando duas fontes diferentes: 
* busca por CEPs individualmente com uso do web service ViaCep
* busca por CEPs em uma base de dados em memória, populada no início da execução por dados do web service ViaCep

| Verbo HTTP  | Endpoint                      | Retorno                                                                        |
|-------------|-------------------------------|--------------------------------------------------------------------------------|
| GET         | /zipcode/{CEP}/database       | retorna o endereço correspondente ao CEP através da base de dados em memória   |
| GET         | /zipcode/{CEP}/viacep         | retorna o endereço correspondente ao CEP através do web service Via Cep        |
| POST        | /login                        | realiza a autenticação para acesso à aplicação *                               |
| GET         | /actuator                     | retorna links com informações sobre health check                               |
| GET         | /swagger-ui.html              | exibe documentação da aplicação                                                |

* a autenticação é feita sem busca no banco, através de um mock. Para testes, deve-se utilizar um objeto com username admin e password admin

## Documentação técnica

Existe uma arquivo README para cada módulo, contendo informações sobre sua implementação

No arquivo application.properties do módulo webapp existe a propriedade indicando o endereço da pasta de logs

## Testes

Para cobertura de testes, foi utilizada a ferramenta [EclEmma](https://www.eclemma.org/)

## Tecnologia

Foi utilizado Java 11 pela distribuição [Amazon Corretto OpenJDK](https://aws.amazon.com/corretto/)). Sua escolha foi devido à flexibilidade da plataforma, o avanço de suas ferramentas e a experiência prévia em seu uso. Todos os módulos foram desenvolvidos com o uso de [Spring Boot](https://spring.io/projects/spring-boot) e outras ferramentas do ecossistema Spring. Para versionamento, foi utilizado o [Git](https://git-scm.com/) e para gerenciamento de pacotes, o [Apache Maven](https://maven.apache.org/).
