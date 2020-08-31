# Zip Code Service - WebApp

Módulo web, que serve de "porta de entrada" para toda a aplicação. Este módulo disponibiliza recursos REST, classes para a configuração de recursos adicionais como o Swagger e o Actuator, além das classes necessárias para a implementação do Spring Security JWT

O módulo possui cinco pacotes:
* services: contém a implementação de UserDetailsService, utilizado pelo Spring Security. Nela, é usado um usuário mockado
* dtos: possui objetos anêmicos, utilizados na transferência de dados. Possui apenas uma classe, que representa as credenciais do usuário
* configurations: contém classes de configuração, do Swagger e do Spring Security
* auth: contém classes de filtro de autorização, filtro de autenticação, implementação da interface UserDetails e classe de utilitários do Jwt, todos recursos necessários ao Spring Security
* resources: contém os recursos REST da aplicação. Possui dois recursos, ambos respondem objetos JSON e retornam um endereço válido quando encontrado por algum dos serviços, ou uma mensagem de erro, também em JSON

No pacote default, a classe WebappApplication realiza a leitura dos serviços e entidades e toda a configuração necessária pelo Spring Boot. Além disso, faz uma chamada ao DatabaseService para popular a base de dados (o banco em memória é recriado a cada execução) e exibe um log contendo o endereço da API para acesso à documentação e ao serviço de health check
