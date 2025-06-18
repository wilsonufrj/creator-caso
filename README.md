# Caso Creator

Microsserviço para gerar casos.

## Visão Geral

O Caso Creator é um microsserviço Spring Boot projetado para gerenciar 'casos'. No contexto desta aplicação, um 'caso' representa um conjunto de informações ou dados específicos utilizados para estudos, análises ou processamentos dentro dos projetos do CEPEL. Este serviço fornece funcionalidades para criar, consultar e gerenciar esses casos de forma eficiente.

## Endpoints da API

### Criar Caso
- **Endpoint:** `POST /v1/caso`
- **Descrição:** Cria um novo caso.
- **Request Body:** `CasoInfoDTO`. Exemplo: `{"nomeProjeto": "PLDPro", "descricao": "Caso de teste"}`. (Nota: A estrutura completa do `CasoInfoDTO`, incluindo campos como `id`, `dataCriacao`, etc., pode ser visualizada na documentação Swagger.)
- **Response Body:** `CasoInfoDTO` com o caso criado (incluindo o `id` gerado e `dataCriacao`).

### Buscar Caso por ID
- **Endpoint:** `GET /v1/caso/{id}`
- **Descrição:** Busca um caso específico pelo seu ID.
- **Path Parameter:** `id` (Long) - ID do caso.
- **Response Body:** `CasoInfoDTO` do caso encontrado ou erro 404 se não encontrado.

### Buscar Todos os Casos
- **Endpoint:** `GET /v1/caso`
- **Descrição:** Busca todos os casos criados.
- **Response Body:** Lista de `CasoInfoDTO`.

## Como Executar o Projeto

### Pré-requisitos
*   Java Development Kit (JDK) 19 ou superior.
*   Apache Maven 3.x ou superior.
*   Uma instância do PostgreSQL em execução.

### Configuração
1.  Clone o repositório (substitua `<URL_DO_REPOSITORIO>` pela URL correta): `git clone <URL_DO_REPOSITORIO>`
2.  Configure a conexão com o banco de dados no arquivo `src/main/resources/application.properties`. Atualize as seguintes propriedades conforme necessário:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
    spring.datasource.username=seu_usuario_postgresql
    spring.datasource.password=sua_senha_postgresql
    spring.jpa.hibernate.ddl-auto=update
    ```

### Compilação
Navegue até o diretório raiz do projeto e execute o seguinte comando para compilar o projeto e gerar o arquivo JAR:
```bash
./mvnw clean install
```
Ou, se você não tiver o Maven Wrapper configurado ou preferir usar sua instalação local do Maven:
```bash
mvn clean install
```

### Execução
Após a compilação bem-sucedida, você pode executar a aplicação com o seguinte comando:
```bash
java -jar target/caso-creator-0.0.1.jar
```
A aplicação estará disponível em `http://localhost:8080` (ou a porta configurada em `application.properties`).

## Executando com Docker

Você também pode construir e executar esta aplicação usando Docker.

### Pré-requisitos Docker
*   Docker instalado e em execução.

### Construindo a Imagem Docker
1.  Certifique-se de que o projeto foi compilado e o arquivo JAR (`target/caso-creator-0.0.1.jar`) existe. Você pode gerar o JAR com o comando:
    ```bash
    ./mvnw clean package
    ```
    (Ou `mvn clean package` se você não estiver usando o Maven Wrapper). O `package` é preferível ao `install` aqui, pois apenas o JAR é necessário para a imagem Docker.

2.  Navegue até o diretório raiz do projeto (onde o `Dockerfile` está localizado) e construa a imagem Docker:
    ```bash
    docker build -t caso-creator .
    ```

### Executando o Contêiner Docker
Após construir a imagem, você pode executar a aplicação em um contêiner Docker:
```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://<host_do_seu_banco_de_dados>:<porta_do_banco>/<nome_do_banco> \
  -e SPRING_DATASOURCE_USERNAME=<seu_usuario_postgresql> \
  -e SPRING_DATASOURCE_PASSWORD=<sua_senha_postgresql> \
  --name caso-creator-app \
  caso-creator
```
Substitua os placeholders:
*   `<host_do_seu_banco_de_dados>`: O endereço do seu servidor PostgreSQL (ex: `localhost` se estiver na mesma máquina, ou o IP/hostname do servidor de banco de dados se o Docker estiver em outra máquina ou rede). Se o PostgreSQL estiver rodando em um contêiner Docker na mesma rede Docker, você pode usar o nome do serviço do contêiner PostgreSQL.
*   `<porta_do_banco>`: A porta do seu servidor PostgreSQL (normalmente `5432`).
*   `<nome_do_banco>`: O nome do banco de dados.
*   `<seu_usuario_postgresql>`: Seu nome de usuário do PostgreSQL.
*   `<sua_senha_postgresql>`: Sua senha do PostgreSQL.

Por exemplo, se o banco de dados estiver rodando localmente:
```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/nome_do_banco \
  -e SPRING_DATASOURCE_USERNAME=seu_usuario_postgresql \
  -e SPRING_DATASOURCE_PASSWORD=sua_senha_postgresql \
  --name caso-creator-app \
  caso-creator
```
(Nota: `host.docker.internal` é uma forma de acessar a máquina host a partir de um contêiner Docker no Docker Desktop para Windows e Mac. Para Linux, você pode precisar configurar a rede de forma diferente, como usar `--network="host"` e `localhost` na URL JDBC, ou usar o IP da interface `docker0`.)

A aplicação estará acessível em `http://localhost:8080`.

## Tecnologias Utilizadas
*   Java 19
*   Spring Boot 3.5.0
*   Spring Web
*   Spring Data JPA
*   PostgreSQL (Driver JDBC)
*   Maven
*   SpringDoc OpenAPI (para documentação Swagger)

## Estrutura do Projeto
```
caso-creator/
├── pom.xml                         # Arquivo de configuração do Maven, define dependências e build.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── cepel/dpc/caso_creator/
│   │   │       ├── CasoCreatorApplication.java # Classe principal da aplicação Spring Boot.
│   │   │       ├── config/                   # Configurações da aplicação (ex: Swagger, RestTemplate).
│   │   │       ├── controller/               # Controladores REST (ex: CasoInfoController.java).
│   │   │       ├── domain/                   # Entidades JPA (ex: CasoInfo.java).
│   │   │       ├── dto/                      # Data Transfer Objects (objetos para transferência de dados entre camadas).
│   │   │       ├── repository/               # Repositórios Spring Data JPA (interfaces para acesso a dados).
│   │   │       └── service/                  # Lógica de negócio, incluindo interfaces de serviço.
│   │   │           └── impl/                 # Implementações concretas dos serviços, podendo incluir padrões como Strategy e Factory para criação de casos.
│   │   └── resources/
│   │       └── application.properties      # Configurações da aplicação (banco de dados, porta, logging, etc.).
│   └── test/                           # Testes unitários e de integração.
├── HELP.md                         # Arquivo de ajuda gerado pelo Spring Initializr (pode ser removido se não for útil).
└── README.md                       # Este arquivo de documentação.
```
Principais Componentes:
*   `CasoCreatorApplication.java`: Ponto de entrada da aplicação Spring Boot.
*   `CasoInfoController.java`: Define os endpoints da API REST para interagir com os 'casos'.
*   `service` (e `service.impl`): Contém a lógica de negócio principal. O `service.impl` pode abrigar implementações como `CasoCreationFactory` e as diversas estratégias `ICasoCreationStrategy` para diferentes tipos de 'casos'.
*   `repository`: Interfaces que estendem `JpaRepository` (ou similar) para interações com o banco de dados de forma simplificada pelo Spring Data JPA.
*   `domain`: Contém as entidades JPA, como `CasoInfo`, que são mapeadas para tabelas no banco de dados.
*   `dto`: Objetos utilizados para transportar dados entre as camadas da aplicação (ex: da controller para o service e vice-versa), ajudando a desacoplar a API das entidades de domínio.

## Documentação da API (Swagger)

A documentação da API é gerada automaticamente utilizando SpringDoc OpenAPI.
Após iniciar a aplicação, você pode acessar a interface do Swagger UI no seguinte endereço:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Nesta interface, você pode visualizar todos os endpoints disponíveis, seus parâmetros, corpos de requisição e resposta, além de testar os endpoints diretamente pelo navegador.

(Nota: A porta `8080` é a padrão do Spring Boot, mas pode ser alterada no arquivo `application.properties`. Se for o caso, o usuário deverá ajustar a URL de acesso ao Swagger UI.)
