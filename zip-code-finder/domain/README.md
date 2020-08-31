# Zip Code Finder - Domain

Camada de domínio da aplicação. Nela, se encontram as classes de domínio, como as entidades (classes que representam as tabelas no banco de dados) e os serviços de domínio.

A implantação deste módulo foi baseada no conceito de Domínios Ricos, onde uma classe de domínio possui funcionalidades em seu modelo que incorporam em seu comportamento. 

Por exemplo: a classe ZipCode é um serviço de domínio, onde toda a funcionalidade e inteligência que trabalha com seu objeto é tida nela mesma. A criação, validação e manipulação das suas propriedades é interna, garantindo assim a imutabilidade (o objeto 'current' só pode ser alterado dentro da classe, seu método getter retorna uma string, que é imutável)

Esta classe realiza todas as validações da String necessária em seu construtor, impedindo que haja alguma Exception na sua criação. Porém quando há algum dado inválido (por exemplo: um valor não numérico ou null), o atributo boolean 'valid' se tornará false, indicando que o objeto é inválido e evitando o lançamento de Exceptions

A classe Address, por sua vez, é uma classe de entidade. Por apenas representar um objeto sem funcionalidades, ela apenas valida o CEP a ser incluso, retirando o traço da String

Seguindo o Domain Driven Design, o módulo de domínio é independente dos demais módulos, sendo o único que não possui referência aos outros em seu pom.xml. A camada é isolada, e cada classe possui uma única responsabilidade, se mantendo nos padrões do SOLID.
