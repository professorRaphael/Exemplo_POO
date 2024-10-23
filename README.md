# Sistema de Gerenciamento de Pessoas - Exemplo Java OO com Spring Boot

Este projeto foi desenvolvido como exemplo para as turmas de Orientação a Objetos com Java. Ele demonstra a criação de uma aplicação web simples usando **Spring Boot**, **Spring MVC**, **Banco de Dados SQLite** e **Tailwind CSS** para a estilização.

O sistema permite que os usuários adicionem, visualizem, editem e deletem pessoas em um banco de dados SQLite. O objetivo é servir como uma base para explicar conceitos de **Programação Orientada a Objetos (POO)**, **Arquitetura MVC (Model-View-Controller)** e integração com banco de dados.

## Funcionalidades

- **Adicionar Pessoa**: Interface para adicionar uma nova pessoa com nome, idade e altura.
- **Listar Pessoas**: Exibe uma lista de pessoas cadastradas no sistema.
- **Editar Pessoa**: Interface para editar os dados de uma pessoa existente.
- **Deletar Pessoa**: Permite a exclusão de pessoas cadastradas.

## Tecnologias Utilizadas

- **Java 11**: Linguagem de programação usada no backend.
- **Spring Boot**: Framework usado para facilitar a criação da aplicação web.
- **SQLite**: Banco de dados relacional para armazenamento das pessoas.
- **Tailwind CSS**: Framework CSS utilitário para estilização da interface.
- **HTML estático**: Para renderização de páginas de forma simples e fácil.

## Estrutura do Projeto

```bash
├── db                   # Contém o banco de dados SQLite
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.aula_jsp
│   │   │       ├── Main.java                # Ponto de entrada da aplicação
│   │   │       ├── Pessoa.java              # Classe de domínio Pessoa (entidade)
│   │   │       ├── PessoaController.java    # Controlador com os endpoints de CRUD
│   │   │       └── PessoaDAO.java           # Data Access Object para acessar o banco de dados
│   │   ├── resources
│   │   │   └── static                       # Arquivos estáticos
│   │   │       ├── index.html
│   │   │       ├── listar.html
│   │   │       └── pessoas                  # Formulario
│   │   │           └── formulario.html
└── pom.xml              # Arquivo de configuração do Maven (dependências do projeto)
```

## Como Executar o Projeto

Pré-requisitos
JDK 11+: Certifique-se de que o Java Development Kit 11 ou superior esteja instalado.
Maven: Ferramenta de automação de build que gerencia as dependências do projeto.

## Passo a Passo

- Clone o repositório:

```bash
git clone https://github.com/professorRaphael/projeto-exemplo_.git
```

- Navegue até o diretório do projeto:

```bash
cd projeto-exemplo
```

- Execute o Maven:

```bash
mvn clean install
```

- Execute o aplicativo:

```bash
mvn spring-boot:run
```

- Acesse a página:

<http://localhost:8080>

## Endpoints Disponíveis

/pessoas: Lista todas as pessoas cadastradas.

/pessoas/nova: Formulário para adicionar uma nova pessoa.

/pessoas/editar/{id}: Formulário para editar uma pessoa existente.

/pessoas/deletar/{id}: Exclui uma pessoa.

## Objetivos Pedagógicos

Este projeto foi elaborado com os seguintes objetivos pedagógicos:

- Entendimento do padrão MVC: Separação de responsabilidades entre Model (classe Pessoa), View (páginas HTML) e Controller (classe PessoaController).
- Prática de Orientação a Objetos: Implementação de uma aplicação simples com classes, métodos e controle de fluxo.
- Integração com banco de dados: Demonstração de como persistir e recuperar dados em um banco de dados SQLite utilizando o padrão DAO.
- Uso de frameworks modernos: Exposição aos conceitos de frameworks como Spring Boot e Tailwind CSS para facilitar o desenvolvimento de aplicações profissionais.

## Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests para melhorar este exemplo e ajudar outros estudantes.

______________________

Professor Raphael Mauricio Sanches de Jesus

Universidade Estácio de Sá
Curso: Orientação a Objetos em Java
