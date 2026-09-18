# DevShowcase API

API REST desenvolvida em Java com Spring Boot para gerenciamento e apresentação de projetos de desenvolvedores.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Jakarta Validation
- REST API

## Funcionalidades

A API permite:

- Cadastrar projetos;
- Listar projetos;
- Cadastrar feedbacks para projetos;
- Listar feedbacks de um projeto;
- Calcular a nota média dos projetos;
- Registrar upvotes;
- Associar projetos a perfis;
- Associar projetos a tecnologias;
- Validar notas de feedback entre 1 e 5;
- Retornar erro HTTP 404 quando o projeto não existe.

## Estrutura do projeto

```text
src/main/java/br/com/devshowcase/api
│
├── controller
│   ├── ProfileController.java
│   ├── ProjectController.java
│   └── TechnologyController.java
│
├── dto
│   ├── FeedbackRequest.java
│   ├── FeedbackResponse.java
│   ├── ProfileResponse.java
│   ├── ProjectRequest.java
│   ├── ProjectResponse.java
│   ├── TechnologyRequest.java
│   └── TechnologyResponse.java
│
├── exception
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
│
├── model
│   ├── Feedback.java
│   ├── Profile.java
│   ├── Project.java
│   └── Technology.java
│
├── repository
│   ├── FeedbackRepository.java
│   ├── ProfileRepository.java
│   ├── ProjectRepository.java
│   └── TechnologyRepository.java
│
└── service
    └── ProjectService.java