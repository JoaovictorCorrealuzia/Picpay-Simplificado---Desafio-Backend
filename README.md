## 👨‍💻Desafio Back-end | 🏦PicPay Simplificado

Este projeto tem como objetivo desenvolver uma versão simplificada do **PicPay**. Nesse desafio, foi criada uma **API REST** que permite o cadastro de usuários e a realização de transferências bancárias. As funcionalidades foram implementadas seguindo _regras de negócio_ bem estabelecidas, tornando o projeto mais profissional e funcional.
## 🖥️ Tecnologias

**Linguagem: Java 17**

**Frameworks: Spring Boot, JUnit, Mockito**

**Banco de Dados: H2 DataBase**


## 📜 Regras de Negócio

- Apenas usuários comuns _(Usuario tipo COMMUN)_ podem enviar dinheiro;

- Lojistas _(Usuario tipo MERCHANT)_ apenas recebem pagamentos;

- O remetente precisa possuir saldo suficiente;

- A transação precisa ser autorizada por um serviço externo;





## 📍End Points

### User Controller

#### Listar os Usuarios

```http
  GET /users
```
Lista os usuarios cadastrados na API, retorna uma List, atravez do ResponseEntity;

#### Criar Usuario

```http
  POST /users
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `UserDTO`      | `JSON` | **Requer** um JSON enviado viado pelo Body na requisição |

#### JSON:
```json
{
  "firstName": "João",
  "lastName": "Victor",
  "email": "joao@email.com",
  "document": "12345678900",
  "password": "123456",
  "balance": 1000,
  "userType": "COMMON"
}
```


### Transactions Controller

#### Fazer Uma Transação

```http
  POST /transactions
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `TransactionDTO`      | `JSON` | **Requer** um JSON enviado viado pelo Body na requisição |


#### JSON:
```json
{
	"senderId":2,
	"receiverId": 1,
	"value":500
}
```
## 📂 Arquitetura do Projeto

```text
src
├── main
│   ├── java
│   │   └── com/br/joaovictor/picpaysimplificado
│   │       ├── controller
│   │       ├── dto
│   │       ├── entity
│   │       ├── exceptions
│   │       ├── repository
│   │       ├── service
│   │       └── config
│   └── resources
└── test
    └── java
        └── com/br/joaovictor/picpaysimplificado/services
            ├── TransactionServiceTest.java
            └── UserServiceTest.java
```

## 🧑‍💻 Rodar Localmente

Clone o projeto, abra-o em sua IDE de preferência e execute-o;

Utilize um serviço como o Insomnia ou Postman para executar as requisições;

```bash
  git clone https://github.com/JoaovictorCorrealuzia/Picpay-Simplificado---Desafio-Backend
```
## ⚙️ Tests Unitarios;

#### Classe UserServiceTest:

```java
  ValidateTransactionSuccessfully()
```
```java
  ValidateTransactionWhenBalanceIsInsufficient()
```
```java
  ValidateTransactionWhenUserIsMerchant()
```
```java
  findUserByIdSuccessfully()
```
```java
  findUserByIdNotFound()
```
```java
  createUserSuccessfully()
```

#### Classe TransactionServiceTest:

```java
  authorizeTransactionSuccessfully()
```

```java
  authorizeTransactionNotAuthorized()
```

```java
  authorizeTransactionAPIdontWorking()
```

## 🧑‍💻 Execute os Tests

Para executar os testes, como com o projeto principal, deve-se clonar o projeto com o Git, ir até o diretório de testes:

```text
src
│   
└── test
    └── java
        └── com/br/joaovictor/picpaysimplificado/services
            ├── TransactionServiceTest.java
            └── UserServiceTest.java
```

E executar as classes TransactionServiceTest e UserServiceTests;
## 💡Aprendizados

Esse foi meu primeiro "grande" projeto, um projeto simples, porém mais incorporado que uma To-Do List. Com esse projeto, aprendi a desenvolver testes unitários, além de colocar em prática tudo e mais um pouco com o Spring e Java. A arquitetura do projeto e as implementações fora do Java foram grandes aprendizados. Por fim, boas práticas e muitas dicas para projetos futuros.

### 📚️ Principais aprendizados:

- Desenvolvimento de testes unitários com JUnit e Mockito;

- Implementação de conexão com APIs externas;

- Implementação de Tratamento de exceções;

- As boas práticas aplicadas nesse projeto e aprendizados para próximas aplicações;

- Arquitetura mais bem estruturada.
## Autor

- [João Victor Corrêa luzia](https://github.com/JoaovictorCorrealuzia)
