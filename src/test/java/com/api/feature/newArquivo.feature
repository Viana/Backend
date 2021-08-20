Feature: [Nome da feature]

  Background:
    Given que acesse a API [variável da URL]

  @all @classificacao
  Scenario: [Nome do Cenário]
    Given que acessei o endpoint "<EndPoint>"
    When executar o metodo GET com Content-Type igual "application/json"
    Then o status da requisição deve ser <Status Code>
   

