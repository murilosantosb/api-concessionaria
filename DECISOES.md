Este documento registra os pontos ambíguos do case da Concessionária Marcelo Gomes e a decisão tomada para cada um.

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