# venda-ingressos-api

Instalação
----------
- É necessário o `maven` instalado (apt-get install maven)
- É necessário o `docker` e `docker-compose` instalado

1. `docker-compose up -d` (dentro do diretório raiz do projeto)
2. `mvn install` para instalar as dependências
3. `mvn spring-boot:run` para rodar a aplicação
4. Acesse com a url: [http://localhost:8091](http://localhost:8091)

Credenciais
---------

#### RabbitMQ
~~~
username: admin
password: admin
~~~

#### MySQL
~~~
username: root
password: 12345678
~~~

### Adicione as configurações

Modifique o nome do arquivo `application-example.yml` para `application.yml` 
Dentro do `application.yml` coloque suas keys do [Stripe](https://stripe.com/br)

Para acessar o wehbook acesse o terminal local e coloque o seguinte comando: `stripe listen --forward-to localhost:8091/api/venda/webhook-listener`

Observação: caso tenha dúvidas de como fazer a configuração do Stripe na máquina, consulte a documentação do próprio Stripe.

Requisições
----------
~~~
CADATRO DE CLIENTE:

URL: localhost:8091/api/cliente

JSON:
{
    "nome": "NOME DO CLIENTE",
    "email": "teste@email.com",
    "telefone": "(85) 0000-0000",
    "cpf": "123.456.789-01",
    "cidade": "Fortaleza"
}
~~~

~~~
CADASTRO DE INGRESSOS:

URL: localhost:8091/api/ingresso

JSON:
{
    "descricao": "Ingresso para o show da banda XYZ",
    "localEvento": "Local do evento",
    "dataEvento": "2023-12-31T19:30:00", 
    "dataEncerramentoVenda": "2023-12-15",
    "valor": 50.00
}
~~~

~~~
CADASTRO DE NOVA VENDA DE INGRESSO:

URL: localhost:8091/api/venda

JSON: 
{
    "cliente": 1,
    "ingresso": 1
}

OBS: É permitido apenas a venda de dois ingressos de cada evento 
para cada cliente
~~~

FLUXO
-------
Após a venda ser efetuada, os dados serão enviados para uma fila que será consumida por uma API disparadora de emails que enviará o ingresso para o cliente.
O email será enviado após o pagamento ser realizado pelo cliente.

A API disparadora de emails deve estar sendo executada e consumindo a fila do RabbitMQ.
link da API: [disparador-emails](https://github.com/IgorBavand/disparador-emails-api)


