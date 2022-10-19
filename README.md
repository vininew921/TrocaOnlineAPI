#  Troca Online
# Sobre
 ## Configurações para rodar a aplicação

Para rodar o projeto é necessário a instalação do JDK para compilar o projeto. Após isso é necessário clonar o projeto para a máquina local com o comando "git clone"
```
git clone https://github.com/vininew921/TrocaOnlineAPI.git
```
Após isso é necessário mapear o banco utilizado no postgre, as tabelas são criadas/atualizadas automaticamente  com a função "ddl-auto: update" assim que o projeto é inicializado.

# Testar online pelo Heroku

Para testar a API online, sem a necessidade de clonar e rodar o projeto local, disponibilizou-se um swagger mapeando os endpoints da API.
```
https://troca-online-api-prod.herokuapp.com/swagger-ui/index.html
```

## Introdução Arquitetura

Para estruturar o Backend do projeto foi proposto a utilização do Framework Spring para criação de APIs Rest para realizar a manipulação dos dados persistidos no banco de dados da solução.

O padrão de arquitetura escolhido para estruturar os componentes criados para a API foi o MVC (Model-View-Controller) adaptado pelo time.


## Camada Controller

Camada responsável por receber os Requests e responder adequadamente seguindo a estrutura de uma API Rest. Com isso, nesta camada implementa-se serviços responsáveis por informar ao Controller como encapsular uma resposta para o destinatário.  
Para identificar o tipo de transação a ser realizada para cada serviço, o Controller deve especificar um Endpoint relacionando-o com um [HTTP Verb](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods "https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods") adequado. Por exemplo, um endpoint “[GET] /anuncios” deve realizar uma transação de consulta por anúncios, retornando um payload com dados de anúncios.

No encapsulamento da response, o Controller difunde apenas a informação oriunda do serviço, não alterando-a. Fora o payload, o Controller também é responsável por designar o  [HTTP Response Status Code](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status") correto.

## **Camada Service**

Camada responsável por invocar artefatos que permitem acesso controlado as informações do banco de dados. Nesta camada realiza-se a consulta aos “dados brutos” manipulando-os para ter uma resposta adequada para cada endpoint.

Nesta camada implementa-se as regras de negócio adequadas para retornar de acordo com o tipo de requisição. Para auxiliar na manipulação das respostas, nesta camada permite-se aplicar [DTOs](https://www.linkedin.com/pulse/padr%C3%A3o-dto-uma-maneira-diferente-para-transfer%C3%AAncia-tiago-perroni/?originalSubdomain=pt "https://www.linkedin.com/pulse/padr%C3%A3o-dto-uma-maneira-diferente-para-transfer%C3%AAncia-tiago-perroni/?originalSubdomain=pt") para enviar somente dados que deseja-se enviar para um destinatário.

Nesta camada também permite-se invocar mais de um tipo de artefato, como um AnuncioRepository e AlunoRepository ao mesmo tempo, assim como invocar outros serviços.

## **Camada Repository**

Camada abstraída da camada “Model”, permite-se através dela realizar transações pelo banco de dados utilizando objetos mapeados. Cada objeto mapeado representa uma Entidade que permite abstrair dados de uma tabela do banco de dados para um “artefato java”. A partir do artefato, o Spring Data JPA realiza uma conexão com a base de dados possibilitando comunicar e persistir dados em alto nível.

(Referência útil: [![](https://www.treinaweb.com.br/assets/images/favicon-16x16.png)Iniciando com Spring Data JPA](https://www.treinaweb.com.br/blog/iniciando-com-spring-data-jpa) )

