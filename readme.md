# GestMultiRest - Tech Challenge (Fase 2)

Bem-vindo ao **GestMultiRest**, um sistema robusto de gestão partilhada para múltiplos restaurantes. Este projeto foi desenvolvido como parte do Tech Challenge da pós-graduação em Arquitetura e Desenvolvimento Java, com o objetivo de permitir que diversos estabelecimentos façam a gestão das suas operações (utilizadores, restaurantes e itens de cardápio) de forma centralizada e escalável.

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

O projeto foi rigorosamente estruturado seguindo os princípios da **Arquitetura Limpa (Clean Architecture)**, garantindo um baixo acoplamento e uma alta coesão através da separação de responsabilidades nas seguintes camadas:

- **`domain`**: O coração da aplicação. Contém as entidades de negócio (`Usuario`, `Restaurante`, `ItemCardapio`, etc.), as exceções de domínio e as interfaces dos repositórios (Gateways). **Não possui qualquer dependência de frameworks externos.**
- **`application/usecases`**: Contém as regras de orquestração da aplicação. Cada caso de uso (ex: `CadastrarRestauranteUseCase`) tem uma responsabilidade única e comunica com a infraestrutura apenas através de interfaces.
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