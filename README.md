# pagamento_api

Swagger: http://localhost:8080/swagger-ui/index.html#/


Dados Mockagem

==================================================

POST

Pagamento com BOLETO (erro 500)
{
  "id": 0,
  "codigoDebito": 120,
  "cpfCnpj": "123.456.789-10",
  "numeroCartao": "5451425203",
  "valorPagamento": 130,
  "tipoPagamento": "BOLETO",
  "statusPagamento": "PENDENTE_PROCESSAMENTO",
  "ativo": true
}

Pagamento com BOLETO (sem erro)
{
  "id": 0,
  "codigoDebito": 120,
  "cpfCnpj": "123.456.789-10",
  "valorPagamento": 130,
  "tipoPagamento": "BOLETO",
  "statusPagamento": "PENDENTE_PROCESSAMENTO",
  "ativo": true
}

Pagamento com PIX (erro 500)
{
  "id": 0,
  "codigoDebito": 120,
  "cpfCnpj": "080.222.449-10",
  "numeroCartao": "2551225203",
  "valorPagamento": 60,
  "tipoPagamento": "PIX",
  "statusPagamento": "PENDENTE_PROCESSAMENTO",
  "ativo": true
}

Pagamento com PIX (sem erro)
{
  "id": 0,
  "codigoDebito": 120,
  "cpfCnpj": "080.222.449-10",
  "valorPagamento": 60,
  "tipoPagamento": "PIX",
  "statusPagamento": "PENDENTE_PROCESSAMENTO",
  "ativo": true
}


Pagamento com cartão (erro 400)
{
  "id": 0,
  "codigoDebito": 123,
  "cpfCnpj": "123.456.789-10",
  "valorPagamento": 2000,
  "tipoPagamento": "CARTAO_CREDITO",
  "statusPagamento": "PENDENTE_PROCESSAMENTO",
  "ativo": true
}


Pagamento com cartão (sem erro)
{
  "id": 0,
  "codigoDebito": 123,
  "cpfCnpj": "123.456.789-10",
  "numeroCartao": "5251425203",
  "valorPagamento": 120,
  "tipoPagamento": "CARTAO_CREDITO",
  "statusPagamento": "PENDENTE_PROCESSAMENTO",
  "ativo": true
}

==================================================
