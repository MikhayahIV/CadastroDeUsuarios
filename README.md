## 📁 Documentação da API: Cadastro de Usuários e Atividades

[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.java.com/pt-br/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![API Doc](https://img.shields.io/badge/API%20Doc-Swagger-85EA2D.svg)]()
[![Frontend](https://img.shields.io/badge/Frontend-Vite-yellow.svg)]()

---

## 📝 Descrição do Projeto

Esta aplicação é um sistema simples de **Cadastro e Gerenciamento de Usuários e Atividades**.

Desenvolvida em **Java** com **Spring Boot**, a API permite a criação, leitura, atualização e exclusão (**CRUD**) de dados de usuários e suas respectivas atividades.  
O sistema utiliza um banco de dados **H2 em memória/arquivo** para persistência e é totalmente desacoplado para uso com um frontend **Vite** na porta `5173`.

---

## 🚀 Funcionalidades Principais

- **Usuários:** CRUD completo para gerenciamento de registros de usuários.  
- **Atividades:** CRUD completo para gerenciamento de registros de atividades.  
- **API RESTful:** Endpoints bem definidos e documentados.  
- **Swagger/OpenAPI:** Documentação automática e interativa.  

---

## 💻 Tecnologias

O projeto utiliza o seguinte conjunto de tecnologias:

| Tecnologia | Descrição |
| :--- | :--- |
| **Java 17+** | Linguagem de programação principal. |
| **Spring Boot** | Framework para desenvolvimento de aplicações RESTful. |
| **Spring Data JPA** | Persistência de dados e abstração de repositórios. |
| **H2 Database** | Banco de dados em memória/arquivo, ideal para desenvolvimento e testes. |
| **Flyway** | Gerenciamento de versionamento e migração do banco de dados (esquema inicial). |
| **Lombok** | Redução de código *boilerplate* em DTOs e Models. |
| **Swagger/OpenAPI** | **Documentação automática e interativa da API.** |
| **Vite** | Ferramenta de build utilizada pelo frontend. |

---

## 🏗️ Estrutura da Aplicação

O projeto é organizado em dois módulos principais:

```
Nome-Do-Projeto/
├── CadastroDeUsuarios/        # Módulo da API (Backend - Spring Boot)
│   ├── src/main/java/br/com/...
│   │   ├── Atividades/        # Controller, Service, Repository, etc.
│   │   └── Usuarios/          # Controller, Service, Repository, etc.
│   └── CadastroDeUsuariosApplication.java
└── frontend/
    └── fronta/                # Módulo do Frontend (Vite)
```

---

## 🛠️ Instalação e Execução

Para rodar o projeto localmente, você deve iniciar o **Backend (API)** e o **Frontend** separadamente.

### Pré-requisitos

- **Java 17+**
- **Node.js e npm** (para o frontend)

---

### ⚙️ Configuração de Variáveis de Ambiente (H2)

Crie ou edite o arquivo de configuração de ambiente na pasta **`CadastroDeUsuarios`** para configurar o H2 Database:

| Variável | Uso | Exemplo de Uso (H2 Local) |
| :--- | :--- | :--- |
| `DATABASE_URL` | Spring Boot | `jdbc:h2:~/nome_do_seu_banco;` |
| `DATABASE_USERNAME` | Spring Boot | `sa` |
| `DATABASE_PASSWORD` | Spring Boot | (Vazio) |

---

### Passos de Execução

#### 1. Iniciar a API (Backend)

- **Localização:** Pasta **`CadastroDeUsuarios`**  
- **Ação:** Execute o arquivo principal **`CadastroDeUsuariosApplication.java`** através de sua IDE.

> 💡 A API será iniciada na porta **8080**.

---

#### 2. Iniciar o Frontend (Vite)

- **Localização:** Navegue para a pasta **`frontend/fronta`**.  
- **Comando:** Use o **`npm run dev`**.

```bash
# Navegue até a pasta correta do frontend
cd frontend/fronta

# Rode o comando para iniciar o servidor de desenvolvimento
npm run dev
```

> 💡 O frontend será acessível em:  
> **http://localhost:5173**

---

## 🌐 Rotas da API (Endpoints REST)

A documentação interativa completa de todos os endpoints está disponível via **Swagger/OpenAPI** após a inicialização do backend.

> 🔗 **Acesse o Swagger:**  
> [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

### 🧍‍♂️ A. Endpoints de Usuários (`/usuarios`)

| Método | Rota | Resumo | Resposta de Sucesso |
| :---: | :--- | :--- | :---: |
| **GET** | `/boasVindas` | Mensagem de boas-vindas. | `200` |
| **POST** | `/adicionar` | Cria um novo usuário. | `201 (Created)` |
| **GET** | `/listar` | Lista todos os usuários. | `200` |
| **GET** | `/listar/{id}` | Busca um usuário por ID. | `200` |
| **PUT** | `/atualizar/{id}` | Atualiza um usuário por ID. | `200` |
| **DELETE** | `/deletar/{id}` | Deleta um usuário por ID. | `200` |

---

### 📝 B. Endpoints de Atividades (`/atividades`)

| Método | Rota | Resumo | Resposta de Sucesso |
| :---: | :--- | :--- | :---: |
| **POST** | `/adicionar` | Cria uma nova atividade. | `201 (Created)` |
| **GET** | `/listar` | Lista todas as atividades. | `200` |
| **GET** | `/listar/{id}` | Busca uma atividade por ID. | `200` |
| **PUT** | `/atualizar/{id}` | Atualiza uma atividade por ID. | `200` |
| **DELETE** | `/deletar/{id}` | Deleta uma atividade por ID. | `200` |

---

## 🧩 Observações Finais

- A aplicação é modular, permitindo o uso independente do backend e frontend.  
- O banco H2 pode ser facilmente substituído por outro (ex: PostgreSQL ou MySQL) alterando as configurações no `application.properties`.  
- O uso do **Swagger** facilita o teste e entendimento dos endpoints.  

---
