# Linguagem Funcional com Suporte Nativo a Árvores e Grafos

**Universidade Federal de Pernambuco** - Centro de Informática \
**IN1007 2026.1** - Paradigmas de Linguagens de Programação

## Equipe

- Leonardo dos Anjos Silva - las4@cin.ufpe.br
- Vinicius Resende Barbosa - vrb@cin.ufpe.br

## Escopo

### Descrição

Este projeto busca estender a Linguagem Funcional 3 para suportar a criação, manipulação e transformação de Árvores e Grafos. Para isso, o projeto inclui a geração de Grafos a partir de expressões de compreensão e adiciona alguns operadores essenciais para valores do tipo Árvore ou Grafo.

### Elementos Adicionados

- **Estruturas de Dados**:
    - _arvore_
    - _grafo_
- **Operações**:
    - **Árvores**:
       - _raiz_
       - _esquerda_
       - _direita_
    - **Grafos**:
       - _nos_
       - _arestas_
- **Expressão de Compreensão**:
    - _compreensaoGrafo_: para gerar novos grafos a partir de expressões de compreensão.

## Exemplos de Código

```
// Árvore com raiz com valor 5 e um nó filho à esquerda com valor 1
let var x = 2, var y = 3 in avbin x + y / avbin y - x \ null

// Grafo com nós de 1 a 4, com as arestas listadas
gf <1 -> 2> | <2 -> 3> | <2 -> 4> | <3 -> 4> | <4 -> 3>

// Grafo com nós de 1 a 10 em que cada nó de 1 a 5 se conecta com os nós correspondentes ao seu sucessor e dobro númerico
<x -> x + 1> | <x -> 2 * x> for x in 1 .. 5

// Grafo com nós de 1 a 8 em que cada nó de 1 a 5 se conecta com cada nó de 6 a 8 e vice-versa
<x -> y> | <y -> x> for x, y in 1 .. 5, 6 .. 8
```

## Gramática

<pre>
Programa ::= Expressao

Expressao ::= Valor
       | ExpUnaria
       | ExpBinaria
       | ExpDeclaracao
       | Id
       | Aplicacao
       | IfThenElse

Valor ::= ValorConcreto
       | ValorAbstrato

ValorAbstrato ::= ValorFuncao
       <b>| ValorArvore</b>
       <b>| ValorGrafo</b>

ValorConcreto ::= ValorInteiro
       | ValorBooleano
       | ValorString
       | ValorLista
       <b>| ValorVazio</b>

<b>ValorVazio ::= "null"</b>

<b>ValorArvore ::= "avbin" Expressao / ValorArvore \ ValorArvore -- valor, esquerda, direita
        | ValorVazio</b>

<b>ValorGrafo ::= "gf" ListaAdjacencia</b>

<b>ListaAdjacencia ::= Adjacencia
        | Adjacencia "|" ListaAdjacencia</b>

<b>Adjacencia ::= "<" Expressao "->" Expressao ">"</b>

ValorFuncao ::= "fn" Id Id "." Expressao

ExpUnaria ::= "-" Expressao
       | "not" Expressao
       | "length" Expressao
       | head(Expressao)
       | tail(Expressao)
       <b>| root(Expressao)</b>
       <b>| left(Expressao)</b>
       <b>| right(Expressao)</b>
       <b>| nodes(Expressao)</b>
       <b>| edges(Expressao)</b>
       | ExpCompreensaoLista
       <b>| ExpCompreensaoGrafo</b>

ExpCompreensaoLista ::= Expressao GeradorLista
       | Expressao GeradorLista Filtro

<b>ExpCompreensaoGrafo ::= ListaAdjacencia GeradorGrafo</b>

GeradorLista ::= "for" Id "in" Expressao
       | "for" Id "in" Expressao ["," GeradorLista]

Filtro ::= "if" Expressao

<b>GeradorGrafo ::= GeradorLista
        | "for" Id "," Id "in" Expressao "," Expressao</b>

ExpBinaria ::= Expressao "+" Expressao
       | Expressao "-" Expressao
       | Expressao "*" Expressao
       | Expressao ">" Expressao
       | Expressao "<" Expressao
       | Expressao "and" Expressao
       | Expressao "or" Expressao
       | Expressao "==" Expressao
       | Expressao "++" Expressao
       | Expressao ".." Expressao
       | Expressao ":" Expressao
       | Expressao "^^" Expressao

ExpDeclaracao ::= "let" DeclaracaoFuncional "in" Expressao

DeclaracaoFuncional ::= DecVariavel
       | DecFuncao
       | DecComposta

DecVariavel ::= "var" Id "=" Expressao

DecFuncao ::= "fun" ListId "=" Expressao

DecComposta ::= DeclaracaoFuncional "," DeclaracaoFuncional

ListId ::= Id
       | Id , ListId

Aplicacao ::= Expressao "(" ListExp ")"

ListExp ::= Expressao
       | Expressao, ListExp
</pre>

