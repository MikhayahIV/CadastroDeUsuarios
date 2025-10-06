# 👥 Cadastro de Usuários e Atividades

[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.java.com/pt-br/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Layered Architecture](https://img.shields.io/badge/Architecture-Layered-red.svg)]()
[![Swagger](https://img.shields.io/badge/API%20Doc-Swagger-85EA2D.svg)]()
[![GitHub Repo](https://img.shields.io/badge/GitHub-CadastroDeUsuarios-blue?logo=github)](https://github.com/MikhayahIV/CadastroDeUsuarios)

---

## 📝 Descrição do Projeto

**Cadastro de Usuários e Atividades** é uma API RESTful completa desenvolvida em **Java** com **Spring Boot** para fornecer funcionalidades de CRUD (Create, Read, Update, Delete) para usuários e suas atividades relacionadas.

O projeto adota a **Arquitetura de Camadas (Layered Architecture)**, garantindo a organização, a manutenção e a testabilidade do código, com foco na clareza do fluxo de dados (Controller $\rightarrow$ Service $\rightarrow$ Repository).

## 🚀 Funcionalidades

* **CRUD de Usuários:** Cadastro completo, listagem, busca por ID, atualização e remoção de usuários.
* **CRUD de Atividades:** Criação, recuperação, edição e exclusão de atividades, com a possibilidade de associá-las a usuários.
* **Documentação Interativa:** Todas as rotas documentadas via Swagger/OpenAPI.

---

## 💻 Tecnologias

O projeto utiliza um conjunto de tecnologias testadas e modernas:

| Tecnologia | Descrição |
| :--- | :--- |
| **Java 17+** | Linguagem de programação principal. |
| **Spring Boot** | Framework para rápido desenvolvimento de aplicações robustas. |
| **Spring Data JPA** | Persistência de dados com abstração e facilidade. |
| **H2 Database** | Banco de dados em memória, ideal para desenvolvimento e testes rápidos. |
| **Flyway** | Gerenciamento de versionamento e migração do banco de dados. |
| **Lombok** | Redução de código *boilerplate* (getters, setters, construtores). |
| **JUnit e Mockito** | Testes unitários e de integração. |
| **Swagger/OpenAPI** | **Documentação automática e interativa das APIs.** |

---

## 🏗️ Arquitetura (Layered Architecture)

O projeto segue a **Arquitetura de Camadas**, onde a lógica é separada em camadas verticais (Controller, Service, Repository), organizadas em módulos de domínio (`Usuarios` e `Atividades`).

### 📂 Estrutura de Pastas

A organização do código é modular, refletindo as entidades de domínio, e cada pasta de domínio (`Usuarios` e `Atividades`) contém a mesma estrutura de camadas:

| Camada/Pasta | Componentes Principais | Responsabilidade |
| :--- | :--- | :--- |
| **Controller** (`presentation`) | `AtividadeController`, `UsuarioController` | Receber requisições HTTP, validar entrada e delegar lógica ao Service. |
| **Service** (`business logic`) | `AtividadeService`, `UsuarioService` | Contém a regra de negócio central, orquestrando operações e transações. |
| **Repository** (`data access`) | `AtividadesRepository`, `UsuariosRepository` | Interface de comunicação direta com o banco de dados (Spring Data JPA). |
| **Model** (`entities`) | `AtividadesModel`, `UsuariosModel` | Representação da entidade no banco de dados (JPA Entities). |
| **DTOs/Mappers** | `AtividadesDto`, `AtividadesMapper`, `UsuariosDto`, `UsuariosMapper` | Objetos de transferência de dados (DTOs) e classes para mapeamento entre camadas. |
| **Aplicação** | `CadastroDeUsuariosApplication` | Classe principal para inicialização do Spring Boot. |

---

## 🛠️ Instalação e Execução

Como o projeto utiliza o banco de dados **H2 (em memória)**, a execução é extremamente simples e rápida.

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

* **Java 17+**
* **Maven** (ou use o wrapper incluso)

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/MikhayahIV/CadastroDeUsuarios](https://github.com/MikhayahIV/CadastroDeUsuarios)
    cd CadastroDeUsuarios
    ```

2.  **Execute a aplicação via Maven:**
    ```bash
    ./mvnw spring-boot:run
    ```
    *A aplicação estará acessível em `http://localhost:8080`.*

3.  **Acesse a Documentação da API (Swagger):**
    Com o servidor rodando, a documentação interativa do **Swagger/OpenAPI** estará disponível em:
    ```
    http://localhost:8080/swagger-ui.html
    ```

---
