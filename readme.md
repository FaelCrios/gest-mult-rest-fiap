# GestMultiRest - Tech Challenge (Fase 2)

Bem-vindo ao **GestMultiRest**, um sistema de gestão partilhada para múltiplos restaurantes. Este projeto foi desenvolvido como parte do Tech Challenge da pós-graduação em Arquitetura e Desenvolvimento Java, com o objetivo de permitir que diversos estabelecimentos façam a gestão das suas operações (utilizadores, restaurantes e itens de cardápio) de forma centralizada e escalável.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.2** (Web, Data JPA, Validation)
- **PostgreSQL** (Base de dados relacional)
- **H2 Database** (Base de dados em memória para testes de integração)
- **Docker & Docker Compose** (Contentorização)
- **JUnit 5 & Mockito** (Testes unitários e de integração)
- **JaCoCo** (Relatórios de cobertura de testes)
- **Swagger / OpenAPI** (Documentação da API)
- **Lombok**

## Arquitetura (Clean Architecture)

O projeto foi estruturado seguindo os princípios da **Arquitetura Limpa (Clean Architecture)**, garantindo um baixo acoplamento e uma alta coesão através da separação de responsabilidades nas seguintes camadas:

- **`domain`**: O centro da aplicação. Contém as entidades de negócio (`Usuario`, `Restaurante`, `ItemCardapio`, etc.), as exceções de domínio e as interfaces dos repositórios (Gateways). **Não possui qualquer dependência de frameworks externos.**
- **`application/usecases`**: Contém as regras de negócio da aplicação. Cada caso de uso (ex: `CadastrarRestauranteUseCase`) tem uma responsabilidade única e comunica com a infraestrutura apenas através de interfaces.
- **`infrastructure`**: A camada mais externa, responsável pelos detalhes técnicos:
    - **Controllers**: Endpoints REST (`@RestController`).
    - **Gateways/Adapters**: Implementação das interfaces do domínio, comunicando com a base de dados via Spring Data JPA.
    - **Mappers**: Conversão bidirecional entre Entidades de Domínio, Entidades de Persistência (JPA) e DTOs.
    - **Configurações**: Definições de Beans, Swagger, e tratamento global de exceções.

## Pré-requisitos e Execução

Para executar o projeto localmente, necessita de ter instalado:
- **Docker** e **Docker Compose**
- *(Opcional)* Java 21 e Maven (caso pretenda compilar localmente sem o Docker)

### Como executar a aplicação via Docker:

1. Clone o repositório.
2. Na raiz do projeto, abra o terminal e execute o comando:
   ```bash
   docker-compose up --build -d

A aplicação ficará disponível na porta 8080.

## Documentação da API (Swagger)
Com o projeto a correr, pode testar todos os endpoints (criação de utilizadores, restaurantes e itens de cardápio) diretamente pelo Swagger:

 Aceder ao Swagger UI: http://localhost:8080/swagger-ui/index.html

## Testes e Cobertura
O projeto possui testes unitários e de integração, para executar os testes localmente, utilize o Maven:
   ``` bash 
   mvn clean test
   ```

Pode visualizar o relatório visual de cobertura abrindo o ficheiro gerado em: target/site/jacoco/index.html

## Apresentação do Projeto
No vídeo abaixo, é feita a demonstração da aplicação a funcionar, a explicação da Clean Architecture e a validação da cobertura de testes:

https://youtu.be/-XEPa4xFrKc

Desenvolvido por: Rafael Colin


***
