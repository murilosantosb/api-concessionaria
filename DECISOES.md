Este documento registra os pontos ambíguos do case da Concessionária Marcelo Gomes e a decisão tomada para cada um.


## Entrega 1 — Modelagem (10/08)

## 1. Vendedor não virou entidade
O case cita vendedor só para explicar o problema (dois vendedores negociando o mesmo carro), sem pedir nenhum dado cadastral dele. Por isso não virou entidade.

## 2. CPF como String
Número perderia o zero à esquerda e CPF não é uma quantidade, é um identificador. String evita esses problemas.

## 3. Preço como BigDecimal
float/double têm erro de arredondamento e não servem para dinheiro. BigDecimal é o tipo correto para valores financeiros.

## 4. Ano de fabricação e ano do modelo separados
O case avisa que são coisas diferentes. Criei anoFabricacao e anoModelo, ambos Integer e obrigatórios.

## 5. Placa aceita nulo
Carro novo pode chegar sem placa até o emplacamento. Mantive unique = true, já que placas não se repetem e bancos relacionais permitem múltiplos nulos numa coluna única.

## 6. Chassi obrigatório e único
Todo carro tem chassi, inclusive zero-km. O case confirma que chassi não repete.

## 7. Tipo do carro (novo/seminovo) como Enum
Valores fixos e conhecidos, então enum evita erro de digitação. Persistido com @Enumerated(EnumType.STRING) para não depender da ordem dos valores.

## 8. Status de venda como Enum
Mesmo raciocínio do item anterior: disponível, reservado, vendido, salvo como STRING.

## 9. Cor como String livre
Cada fabricante nomeia cor de um jeito diferente, então uma lista fixa (enum) seria limitada demais.

## 10. Quilometragem Integer, IDs Long
Quilometragem não passa do limite de Integer. IDs usam Long, padrão comum em projetos JPA para evitar limitação de capacidade.

---

## Entrega 2 — DTOs e Validação (12/08)

### 1. Carro: id e status de venda não entram no cadastro
Quem cadastra um carro não escolhe o id (isso é o banco que gera) nem o status de venda. Todo carro criado começa automaticamente como "disponível", quem muda isso depois é o sistema, não quem tá cadastrando.

### 2. CPF aparece na resposta do Cliente
Decidi manter o CPF visível quando a API devolve os dados do cliente, já que ele é usado pra identificar o cliente no dia a dia da loja.

### 3. Validação de dinheiro e ano
Segui as reclamações do dono da loja: preço não pode ser zero ou negativo, e ano de fabricação não pode passar de 2026 (nem ser muito antigo, tipo antes de 1950). Ano do modelo aceitei até 2027, porque no mercado de carro é comum vender o modelo do ano seguinte antes de virar o ano.

### 4. CPF validado de verdade, não só o formato
Em vez de só checar se tem letra ou número, usei uma validação que confere se o CPF é matematicamente válido (os dígitos verificadores batem). Isso pega erro de digitação também, não só letra no meio.

### 5. Erro de validação e erro de duplicidade têm formatos diferentes
Quando o problema é validação (campo errado, vazio, fora do limite), devolvo uma lista com cada campo e a mensagem do que está errado — assim dá pra corrigir vários erros de uma vez. Quando é erro de "já existe" (chassi, placa ou CPF repetido) ou "não encontrei", devolvo só uma mensagem direta, porque nesses casos não faz sentido uma lista, é só uma coisa errada.

### 6. Chassi, placa e CPF duplicados nunca chegam a virar erro feio do banco
Antes de salvar, o sistema checa se já existe alguém com aquele chassi, placa ou CPF, e avisa isso de um jeito claro. Assim o erro nunca aparece como uma mensagem técnica de banco de dados pro usuário.