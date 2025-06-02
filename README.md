# Descrição
Este projeto contém testes automatizados para a API (TESTE), utilizando a biblioteca RestAssured em Java.<br>
Os testes validam endpoints para autenticação, listagem de usuários, produtos e manipulação de produtos.

# Tecnologias utilizadas
Java 17<br>
RestAssured<br>
JUnit 5<br>
Maven (para gerenciamento de dependências e execução dos testes)
<br>
# Como rodar os testes
1- Clone o repositório: <br>
2- Execute os testes com Maven: mvn clean test


# Estrutura dos testes
1.Testes de autenticação:
Verifica login com usuário e senha corretos e incorretos.
Testam comportamento com token inválido ou expirado.
<br>
2.Testes de produtos:
Listagem de todos os produtos.
<br>
3.Busca de produto por ID (com cenário de produto inexistente)
<br>
4.Criação de novo produto (POST).
<br>
5.Cenários de falha ao criar produtos com dados inválidos.
<br>
6.Testes de usuários
<br>
7.Listagem de usuários.
<br>
8.Validação de dados esperados e tratamento de discrepâncias.

# Pipeline CI
Este projeto possui integração com GitHub Actions para execução automática dos testes a cada push na branch main.

# Gerar relatório Allure

1.Após rodar os testes, gere o relatório:
<br>
mvn allure:serve
