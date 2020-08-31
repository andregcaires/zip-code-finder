# Zip Code Finder - Context

Módulo de acesso a dados da aplicação. Se baseia nas entidades de domínio em seus repositórios.

Este módulo foi criado baseando-se em conceitos de CQRS (mas não sua implementação completa, por utilizar apenas 1 banco de dados). Ele possui duas ferramentas de acesso à dados:

* Spring Data JPA
* Spring Data JDBC

O Spring Data JPA é utilizado no repositório "command", responsável por realizar manipulação dos dados como inserção, atualização ou remoção (neste projeto só é utilizada a inserção). Esta ferramenta é utilizada por ter uma implementação muito simples e ágil, atendendo a demanda destas operações

O Spring Data JDBC é utilizado no repositório "query", responsável apenas por realizar as buscas no banco de dados. Esta ferramenta possui uma implementação mais complexa que a JPA, porém é mais performática. Como a operação de busca de dados é feita em um alto fluxo neste projeto, esta ferramenta foi escolhida por conta de sua performance

Os serviços de repositório deste projeto são utilizados nos serviços de aplicação da camada Core, e possuem responsabilidades segregadas
