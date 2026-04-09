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
let var x = 2, var y = 3 in avbin [x + y, avbin [y - x, null, null], null]
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

ValorConcreto ::= ValorInteiro
       | ValorBooleano
       | ValorString
       | ValorLista

ValorArvore ::= "avbin" "[" Expressao "," Expressao "," Expressao "]"
       | ArvoreVazia

ArvoreVazia ::= "null"

ValorFuncao ::= "fn" Id Id "." Expressao

ExpUnaria ::= "-" Expressao
       | "not" Expressao
       | "length" Expressao
       | head(Expressao)
       | tail(Expressao)
       <b>| "root" Expressao</b>
       <b>| "left" Expressao</b>
       <b>| "right" Expressao</b>
       | ExpCompreensaoLista

ExpCompreensaoLista ::= "[" Expressao GeradorLista [Filtro] "]"

GeradorLista ::= "for" Id "in" Expressao
       | "for" Id "in" Expressao ["," GeradorLista]

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

