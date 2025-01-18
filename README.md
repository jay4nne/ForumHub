## Apresentação

API Rest desenvolvida para a aplicação FórumHub como parte do programa Oracle Next Education. O projeto tem como objetivo principal implementar um CRUD (*Create, Read, Update, Delete*), simulando um fórum com funcionalidades de cadastro de tópicos e respostas.

## Objetivo

Este projeto busca demonstrar as habilidades adquiridas em desenvolvimento Java e Spring Boot durante a Fase de Especialização do Programa ONE. Além disso, apresenta um exemplo prático de implementação de CRUD para consolidar os conceitos aprendidos.

## Funcionalidades

- **Login de usuário**: Autenticação de usuários presentes no banco de dados, com geração de token JWT para validação das requisições.

- **CRUD de tópicos**: Cadastro, listagem, atualização e exclusão de tópicos.

- **Gerenciamento de respostas**: Adição e exclusão de respostas nos tópicos, com atualizações automáticas no status do tópico.

## Tecnologias Utilizadas

- **Linguagem e Framework**: Java (versão 21) e Spring Boot (versão 3.4);
- **Gerenciador de Dependências**: Maven;
- **Banco de Dados**: MySQL;
-  **Persistência**: JPA Hibernate e Flyway Migrations;
- **Segurança**: Spring Security com autenticação baseada em token JWT.

## Endpoints

- **Autenticação**

    - `POST /login`: Gera um token JWT, que deve ser enviado nas requisições subsequentes para autenticação.

- **Tópicos**

    - `GET /topicos`: Retorna a lista de todos os tópicos cadastrados.
    - `GET /topicos/{id}`: Detalha um tópico específico com base no ID.
    - `POST /topicos`: Cadastra um novo tópico no fórum.
    - `PUT /topicos/{id}`: Atualiza as informações de um tópico existente com base no ID.
    - `DELETE /topicos/{id}`: Remove um tópico, desde que o usuário autenticado seja o autor.

- **Respostas**

    - `POST /topicos/{topicoId}/respostas`: Adiciona uma resposta a um tópico específico. Se for a primeira resposta, o status do tópico muda para **RESPONDIDO**.
    - `DELETE /topicos/{topicoId}/respostas/{respostaId}`: Remove uma resposta, desde que o usuário autenticado seja o autor. Se a única resposta do tópico for removida, o status do tópico retorna para **NAO_RESPONDIDO**.

## Melhorias Futuras

- Implementar o CRUD completo para usuários.
- Adicionar funcionalidade para atualizar respostas.
- Listar tópicos associados a um usuário específico.
- Melhorar a experiência de navegação e manipulação dos dados com paginação e ordenação.

## Variáveis de Ambiente

Para rodar esse projeto, é necessário adicionar as seguintes variáveis de ambiente:

`DB_HOST`
`DB_NAME`
`DB_USERNAME`
`DB_PASSWORD`
