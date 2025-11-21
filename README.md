# **Backend da VOID – Versão em Java com Spring Boot**

Este repositório contém o backend da **VOID**, uma plataforma de e-commerce de streetwear.  
Esta versão foi desenvolvida em **Java 21** com **Spring Boot 3.5.7**, seguindo boas práticas de arquitetura, segurança e padronização de APIs REST.

---

## 🚀 **Tecnologias e Dependências Principais**

### **Backend**
- Java **21**
- Spring Boot **3.5.7**

### **Módulos Spring**
- **Spring Web** – criação das APIs REST
- **Spring Data JPA** – persistência de dados
- **Spring Security** – segurança da aplicação
- **Spring Validation** – validações avançadas
- **Spring DevTools** – recarregamento automático em desenvolvimento

### **Banco de Dados**
- **PostgreSQL**

### **Autenticação**
- **JWT (jjwt 0.11.5)**
    - jjwt-api
    - jjwt-impl
    - jjwt-jackson

### **Utilidades**
- **Lombok** – redução de boilerplate
- **Maven** – build e gerenciamento de dependências

### **Testes**
- Spring Boot Starter Test
- Mockito

---

## 📌 **Sobre o Projeto**

O objetivo deste backend é fornecer a estrutura central da VOID, incluindo:

- Sistema de autenticação com JWT
- Integração com PostgreSQL
- Organização em camadas (Controller → Service → Repository)
- Arquitetura limpa e escalável
- Validações de entrada
- Padrões REST
