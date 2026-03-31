# Linguagem Funcional com Suporte Nativo a Árvores e Grafos

**Universidade Federal de Pernambuco** - Centro de Informática \
**IN1007 2026.1** - Paradigmas de Linguagens de Programação

## Equipe

- Leonardo dos Anjos Silva - las4@cin.ufpe.br
- Vinicius Resende Barbosa - vrb@cin.ufpe.br

## Escopo

### Descrição

Este projeto busca estender a Linguagem Funcional 3 para suportar a criação, manipulação e transformação de Árvores e Grafos. Além disso, o projeto inclui a geração de novas Árvores e Grafos a partir de expressões de compreensão.

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
- **Expressões de Compreensão**:
    - _compreensaoArvore_: para gerar novas árvores a partir de expressões de compreensão.
    - _compreensaoGrafo_: para gerar novos grafos a partir de expressões de compreensão.

## Exemplos de Código

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

ValorConcreto ::= ValorInteiro
       | ValorBooleano
       | ValorString
       | ValorLista
       <b>| ValorArvore</b>
       <b>| ValorGrafo</b>

<b>ValorArvore ::= "arvore(" Expressao "," Expressao "," Expressao ")" -- valor, esquerda, direita
        | "folha(" Expressao ")" -- valor
        | "arvoreVazia"</b>

<b>ValorGrafo ::= "grafo(" Expressao ")" -- lista de adjacencia (lista de [valor, [indicesDeNosAdjacentes]])</b>

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
       <b>| ExpCompreensaoArvore</b>
       <b>| ExpCompreensaoGrafo</b>

ExpCompreensaoLista ::= Expressao Gerador
       | Expressao Gerador Filtro

<b>ExpCompreensaoArvore ::=</b>

<b>ExpCompreensaoGrafo ::=</b>

Gerador ::= "for" Id "in" Expressao
       | "for" Id "in" Expressao ["," Gerador]

Filtro ::= "if" Expressao

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

