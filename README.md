# Finance Easy

API REST de controle financeiro pessoal, feita com Spring Boot como projeto de estudo.

O sistema gerencia três recursos:

- **Usuários:** cadastro com senha criptografada (BCrypt).
- **Transações:** rendas e gastos com categoria, meio de pagamento e data. Cada transação guarda o saldo acumulado do usuário (`valorAtual`).
- **Metas financeiras:** metas com valor total, valor arrecadado e percentual de conclusão.

Também há consultas por tipo, categoria, meio de pagamento e período, além de totais e buscas por maior e menor valor.

## Tecnologias

- Java 27
- Spring Boot 4.1.1 (Spring Web MVC e Spring Data JPA)
- Hibernate
- Banco H2 em memória
- springdoc-openapi (Swagger UI)
- Maven (via Maven Wrapper, não precisa instalar)

## Requisitos

- [Git](https://git-scm.com/)
- **JDK 27** (confira com `java -version`)
- Conexão com a internet na primeira execução, para o Maven baixar as dependências

Não é necessário instalar banco de dados nem o Maven.

## Como rodar

**1. Clone o repositório**

```bash
git clone https://github.com/carlosHL777/finance-easy-app.git
```

**2. Entre na pasta que contém o arquivo `pom.xml`**

```bash
cd finance-easy-app
```

**3. Inicie a aplicação**

Linux, Mac ou Git Bash:

```bash
./mvnw spring-boot:run
```

Windows (PowerShell ou CMD):

```bash
mvnw.cmd spring-boot:run
```

### Rodando pela IDE

Importe como projeto Maven (*File > Import > Maven > Existing Maven Projects*, no Eclipse), atualize o Maven (*Maven > Update Project*) e execute a classe `FinanceEasyAppApplication`.

## Endereços

| O quê | Endereço |
|---|---|
| Swagger | http://localhost:8080/swagger-ui/index.html |
| Console do banco H2 | http://localhost:8080/h2-console |

Acesso banco H2:

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:mem:financetest` |
| User Name | `sa` |
| Password | (vazio) |

Todos os endpoints estão documentados no Swagger, com os parâmetros e exemplos de cada um.

## Dados iniciais

O arquivo `src/main/resources/import.sql` cria usuários, transações e metas de exemplo sempre que a aplicação inicia. O banco é **em memória**, então tudo o que for cadastrado some ao desligar a aplicação.

## Como desligar

- **Terminal:** `Ctrl+C` na janela onde a aplicação está rodando.
- **IDE:** botão de parar (quadrado vermelho) no painel do console.

## Valores aceitos

| Campo | Valores |
|---|---|
| Tipo da transação | `RENDA`, `GASTO` |
| Meio de pagamento | `PIX`, `CREDITO`, `DEBITO`, `DINHEIRO`, `TRANSFERENCIA`, `BOLETO` |
| Categoria da transação | definidas em `CategoriaEnum` (cada categoria pertence a um tipo) |
| Categoria da meta | definidas em `MetaCategoriaEnum` |

Datas usam o formato `AAAA-MM-DD` (por exemplo, `2026-11-08`) e os valores de enum devem ser escritos em maiúsculas, sem acento.

## Estrutura do projeto

```
src/main/java/br/com/fatecmaua/finance_easy_app/
    controller/   endpoints da API
    service/      regras de negócio (saldo, percentual das metas, validações)
    repository/   acesso ao banco (JPA, consultas HQL e SQL nativo)
    model/        entidades e enums
src/main/resources/
    application.properties
    import.sql    dados iniciais
```