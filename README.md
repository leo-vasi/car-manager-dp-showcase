# 🚗 Car Manager - CRUD com Showcase de Design Patterns

[![Java](https://img.shields.io/badge/Java-17%2B-blue)](https://openjdk.org/)
[![License](https://img.shields.io/badge/License-MIT-green)](https://opensource.org/licenses/MIT)

Projeto demonstrando a implementação de um sistema CRUD para gestão de veículos utilizando padrões de projeto Java, com ênfase em:
- **Command Pattern** para operações
- **Builder Pattern** para construção de objetos
- **Factory Pattern** para criação de comandos

## ⚙️ Funcionalidades
- Cadastro/Edição/Exclusão de veículos
- Buscas por placa, CNH ou telefone
- Validação de dados
- Registro de timestamps (criação/atualização)

## 🛠️ Tecnologias
- Java Servlet (Controller)
- Padrão MVC
- JDBC (Persistência)
- JSP (Na view)
- Design Patterns aplicados

## 🎯 Objetivo Didático
Servir como referência para:
- Implementação clean de CRUDs
- Aplicação prática de design patterns
- Separação de responsabilidades


## ✨ Destaques de Arquitetura
```java
// Exemplo Command Pattern em ação
ICommand command = CommandFactory.create(op);
String pagina = command.executar(request, response, carroDAO);
```

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java 17+
- **IDE**: [Apache NetBeans](https://netbeans.apache.org/)
- **Gerenciador de Dependências**: [Maven](https://maven.apache.org/) (`pom.xml`)
- **Banco de Dados**: [MySQL Workbench](https://www.mysql.com/products/workbench/)
- **Servidor Web**: Apache Tomcat (embeddo no NetBeans)
- **Bibliotecas**:
  - Java Servlet API (para o Controller)
  - JDBC (conexão com banco de dados)
  - JSP (para as views)


 ## 🤝 **Contribuinte**:
[Isabela Shihara](https://github.com/isabela-tamie15)

