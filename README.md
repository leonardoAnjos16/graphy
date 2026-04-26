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

#### Estruturas de Dados

- _Árvore binária_: suporte nativo a valores que representam uma árvore binária

```
// Árvore com raiz com valor 5 e um nó filho à esquerda com valor 1
let var x = 2, var y = 3 in avbin [x + y, avbin [y - x, null, null], null]
```

- _Grafo_: suporte nativo a valores que representam um grafo direcionado sem pesos

```
// Grafo com nós de 1 a 4, com as seguintes arestas:
// 1 -> 2
// 2 -> 3
// 2 -> 4
// 3 -> 4
// 4 -> 3
let var x = 2, var y = 3 in gf <y - x -> x> | <x -> y> | <x -> 2 * x> | <y -> 2 * x> | <2 * x -> y>
```

#### Operações

Serão adicionadas as seguintes operações em árvore binárias:
- _root_: retorna o valor armazenado no nó raiz

```
let var T = avbin [5, avbin [1, null, null], null] in root T
// Retorna 5, o valor armazenado na raiz de T
```

- _left_: retorna a subárvore da esquerda do nó raiz

```
let var T = avbin [5, avbin [1, null, null], null] in left T
// Retorna avbin [1, null, null], a subárvore da esquerda da raiz de T
```

- _right_: retorna a subárvore da direita do nó raiz

```
let var T = avbin [5, avbin [1, null, null], null] in right T
// Retorna null, a subárvore da direita da raiz de T
```

- _nodes_: retorna o número de nós da árvore

```
let var T = avbin [5, avbin [1, null, null], null] in nodes T
// Retorna 2, o número de nós em T
```

De forma semelhante, serão adicionadas as seguintes operações em grafos:
- _nodes_: retorna o número de nós do grafo

```
let G = gf <1 -> 2> | <2 -> 3> | <2 -> 4> | <3 -> 4> | <4 -> 3> in nodes G
// Retorna 4, o número de nós em G
```

- _edges_: retorna o número de arestas do grafo

```
let G = gf <1 -> 2> | <2 -> 3> | <2 -> 4> | <3 -> 4> | <4 -> 3> in edges G
// Retorna 5, o número de arestas de G
```

- _adjacency_: dado o identificador de um nó **u**, retorna uma lista contendo todos os nós **v** tal que existe uma aresta de **u** para **v**

```
let G = gf <1 -> 2> | <2 -> 3> | <2 -> 4> | <3 -> 4> | <4 -> 3> in adjacency G 2
// Retorna [3, 4], os nós com os quais o nó 2 se conecta diretamente em G
```

- _reach_: dado o identificador de um nó **u**, retorna uma lista contendo todos os nós **v** tal que existe um caminho de **u** para **v**

```
let G = gf <1 -> 2> | <2 -> 3> | <2 -> 4> | <3 -> 4> | <4 -> 3> in reach G 1
// Retorna [1, 2, 3, 4], os nós alcançáveis a partir do nó 1 em G
```

#### Expressões de Compreensão

Para facilitar a geração automática de grafos, a linguagem também irá suportar a criação de um grafo a partir de expressões de compreensão:

```
// Grafo com nós de 1 a 10 em que cada nó de 1 a 5 se conecta com os nós correspondentes ao seu sucessor e dobro númerico
let var G = [<x -> x + 1> | <x -> 2 * x> for x in 1 .. 5] in G

// Grafo com nós de 1 a 8 em que cada nó de 1 a 5 se conecta com cada nó de 6 a 8 e vice-versa
let var G = [<x -> y> | <y -> x> for x, y in 1 .. 5, 6 .. 8] in G
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

<b>ValorArvore ::= "avbin" "[" Expressao "," Expressao "," Expressao "]"
       | ArvoreVazia</b>

<b>ArvoreVazia ::= "null"</b>

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
       <b>| "root" Expressao</b>
       <b>| "left" Expressao</b>
       <b>| "right" Expressao</b>
       <b>| "nodes" Expressao</b>
       <b>| "edges" Expressao</b>
       <b>| "adjacency" Expressao</b>
       <b>| "reach" Expressao</b>
       | ExpCompreensaoLista
       <b>| ExpCompreensaoGrafo</b>

ExpCompreensaoLista ::= "[" Expressao GeradorLista [Filtro] "]"

<b>ExpCompreensaoGrafo ::= "[" ListaAdjacencia GeradorGrafo "]"</b>

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

