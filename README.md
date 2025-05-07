# FinanceApp Backend

README - Backend (Spring Boot)

✨ Sistema de Controle Financeiro - Backend

Este é o backend da aplicação de controle financeiro pessoal, desenvolvido em Java com Spring Boot. Ele fornece a API REST para funcionalidades como cadastro de usuários, receitas, despesas, metas financeiras, investimentos e autenticação via JWT.

⚖️ Tecnologias Utilizadas

Java 17

Spring Boot

Spring Security + JWT

JPA / Hibernate

PostgreSQL (produção) e H2 (desenvolvimento)

Lombok

🚀 Como executar localmente

1. Requisitos:

Java 17+

Maven

PostgreSQL

2. Clone o repositório:

git clone https://github.com/seuusuario/backend-financeiro.git
cd backend-financeiro

3. Configure o application.properties:

No diretório src/main/resources, edite:

spring.datasource.url=jdbc:postgresql://localhost:5432/seubanco
spring.datasource.username=seuuser
spring.datasource.password=suasenha

4. Rode o projeto:

./mvnw spring-boot:run

A API estará disponível em http://localhost:8080/api.

🌍 Endpoints principais

/api/usuarios/cadastrar

/api/auth/login

/api/receitas, /api/despesas

/api/metas, /api/investimentos
