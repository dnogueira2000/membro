# Members Service

API simples para cadastro e consulta de membros (pessoas com nome e cargo). Atua como **fonte de verdade externa** para o `portfolio-service`.

## Stack

- Java 21
- Spring Boot 3.3
- Spring Data JPA
- PostgreSQL 16 + Flyway
- springdoc-openapi (Swagger)
- JUnit 5

## Endpoints

A documentação está em http://localhost:8081/swagger-ui.html

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/membros` | Cria um membro |
| GET | `/api/membros` | Lista todos os membros |
| GET | `/api/membros/{id}` | Busca por id |

### Cargos suportados

- `FUNCIONARIO`
- `GERENTE`

Apenas membros com cargo `FUNCIONARIO` podem ser alocados em projetos pelo `portfolio-service`.

A API ficará em http://localhost:8081 e o PostgreSQL na porta 5432.

## Dados

A migration `V1__create_membro.sql` insere 6 membros iniciais:

| ID | Nome           | Cargo |
|----|----------------|-------|
| 1  | Maria Silva    | GERENTE |
| 2  | Bruno Souza    | FUNCIONARIO |
| 3  | Carla Mendes   | FUNCIONARIO |
| 4  | Diego Pereira  | FUNCIONARIO |
| 5  | Elena Costa    | FUNCIONARIO |
| 6  | Joao Luiz      | FUNCIONARIO |
| 7  | Jose Silva     | FUNCIONARIO |
| 8  | Luiz Carlos    | FUNCIONARIO |
| 9  | Paulo Oliveira | FUNCIONARIO |
| 10 | Pietro Costa   | GERENTE |
