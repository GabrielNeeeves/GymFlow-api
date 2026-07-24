# GymFlow
# Gym Flow

## Visão geral do projeto

- Objetivo do Gym Flow
- Arquitetura
- Tecnologias

## Como rodar o projeto

- Java
- Maven
- PostgreSQL
- Docker
- Flyway
- Variáveis de configuração
- Comandos (`docker compose up`, `mvn spring-boot:run`, etc.)

## Dependências

- Spring Web
- Spring Security
- Spring Data JPA
- PostgreSQL Driver
- Flyway
- Validation
- JWT (JJWT)
- Lombok

## Estrutura do projeto

```
config
security
exception

auth
├── AuthController
├── JwtService
├── JwtAuthenticationFilter
├── JwtProperties
├── LoginRequest
└── LoginResponse

user
├── UserController
├── UserService
├── UserRepository
├── UserDomain
├── UserRoles
├── UserDetailsServiceImpl
├── dto
│   ├── request
│   └── response
```

## Migrations atuais

```
V1__create_user_table.sql
V2__create_plan_table.sql
```

## Fluxo de autenticação

```
Login
    │
    ▼
AuthenticationManager
    │
    ▼
UserDetailsService
    │
    ▼
PasswordEncoder
    │
    ▼
JWT
    │
    ▼
Bearer Token
    │
    ▼
JwtAuthenticationFilter
    │
    ▼
Endpoints protegidos
```

## Roadmap

```
✅ Usuários
    ✅ Login
    ✅ JWT
    ✅ Spring Security
    ✅ Cadastro de EMPLOYEE

🔄 Em desenvolvimento
    ☐ Plan

Próximos
    ☐ Student
    ☐ Payment
    ☐ Dashboard
    ☐ Reconhecimento Facial
```

## Decisões de arquitetura

- Organização por feature.
- Controllers trabalham apenas com DTOs.
- Regras de negócio ficam na camada Service.
- Entidades JPA não são expostas pela API.
- Flyway para versionamento do banco.
- PostgreSQL.
- Angular 22 como frontend.
- Serviço de reconhecimento facial desacoplado do sistema financeiro.

---

Esse documento serviria como um "guia do projeto", para que você consiga retomar o desenvolvimento rapidamente mesmo após algumas semanas sem mexer no código.
