# Zip Code Finder - Core (Application)

Camada de aplicação do serviço, foi utilizado o nome "Core" para contrastar com a classe Application do Spring Boot

Esta camada é responsável por realizar os serviços da aplicação, utilizando as classes de domínio / serviços de domínio. Esta camada é dependente do módulo Domain e do módulo Context

Ela possui três pacotes:

* util: serviços utilitários. Neste projeto foi necessário apenas um serviço: o de requisições http
* dto: classes utilitárias para transferência de dados 
* services: interfaces de serviços e suas implementações

O serviço DatabaseService possui apenas um método, para popular os dados no banco. Estes dados são obtidos através de um recurso do web service Via Cep, passam por um processo de "deserialization" e são convertidos em objetos de entidade, que são inseridos no banco de dados H2 com o uso do Spring Data JPA

Para a criação do serviço ZipCodeFinderService, foi utilizado um design pattern chamado Template Method, onde uma classe abstrata possui um esqueleto de um algoritmo que invoca um método abstrato em algum momento. Este método é implementado pelas subclasses ZipCodeFinderServiceByViaCepImpl e ZipCodeFinderServiceByDatabaseImpl, fazendo reuso de boa parte do código e aplicando apenas a lógica específica necessária para cada serviço separadamente

Os serviços ZipCodeFinderService fazem uso do serviço de domínio ZipCode, para validação e manipulação dos dados do CEP, e o template method é responsável também pelo tratamento de qualquer exception que possa ser lançada pelas classes filhas, que implementam seu método abstrato
