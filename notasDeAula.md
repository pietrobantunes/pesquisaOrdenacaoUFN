# Pesquisa e Ordenação
---
## Semana 3
### Ordenação
Bolha (bubble sort): A ideia da bolha é pq o maior valor em cada "passada" é levado até o final da estrutura
- memória interna
- estável
- O(nˆ2)
- porção ordenada: final
```
 0     1     2     3     4
40    10    50    10    30
20    40    10    30    50
20    10    30    40    50
10    20    30    40    50
10    20    30    40    50
                        __
                  __    __
            __    __    __
      __    __    __    __
```

Seleção (selection sort)
- memória interna
- instável
- O(nˆ2)
- porção ordenada: início
```
 0     1     2     3     4
40    20    50    10    30
10    20    50    40    30
10    20    50    40    30
10    20    30    40    50
10    20    30    40    50
__
__    __
__    __    __
__    __    __    __
```

Inserção (inserction sort)
- memória interna
- estável
- O(nˆ2)
- porção ordenada: início
```
 0     1     2     3     4
40    20    50    10    30
20    40    50    10    30
20    40    50    10    30
10    20    40    50    30
10    20    30    40    50
      __
      __    __
      __    __    __
      __    __    __    __
```
---
## Semana 2
### Model View Controller (MVC)
- Dividir código em partes para melhor organização
- https://github.com/alexandrezamberlan/estruturasDeDados/tree/master/00%20-%20anosAnteriores/exemploJava_usoLista_MVC
- Ordem de criação: MODEL (classes para construir) -> CONTROLLER (menu) -> VIEW (classes para exibir)

### Algoritmos de Ordenação
A complexidade dos algoritmos é medida pela quantidade de COMPARAÇÕES e TROCAS
- Categorias:
```
> Complexidade Fatorial    - O(n!)    - Menos eficiente
> Complexidade Polinomial  - O(n^k)
> Complexidade Exponencial - O(n^2)
> Complexidade Linear      - O(n)
> Complexidade Logarítmica - O(log n) - Mais eficaz
```
- Eficiente X Eficaz: Ambos atingem objetivos, só que eficaz tem relação com o tempo
- Qual o melhor algoritmo de ordenação? Depende do tamanho da estrutura + do quanto já está ordenado (ex: Quanto maior, os exponenciais são piores)

### Pesquisa:
**Pesquisar na literatura, internet ou IA Generativa sobre os métodos de ordenação e categoriza-los em:**
- *algoritmo de memória interna ou memória externa*
- *estabilidade (estável ou instável)*
- *complexidade*
- *porções de ordenação*
    
bolha (bubble sort)
- memória interna
- estável
- O(nˆ2)
- porção ordenada: final

seleção (selection sort)
- memória interna
- instável
- O(nˆ2)
- porção ordenada: início

inserção (inserction sort)
- memória interna
- estável
- O(nˆ2)
- porção ordenada: início

pente (combsort)
- memória interna
- instável
- O(n^2)
- porção ordenada: uniforme (via contração de gaps)

agitação (shakesort ou cocktailsort (🍾))
- memória interna
- estável
- O(n^2)
- porção ordenada: extremidades (início de final alternadamente)

shellsort
- memória interna
- instável
- O(n^2) ou O(n^1.5) (depende dos intervalos)
- porção ordenada: subvetores intercalados

bucketsort
- memória interna
- estável
- O(n + k) (linear no caso médio)
- porção ordenada: baldes indexados

radix
- memória interna
- estável
- O(n * d) (linear)
- porção ordenada: baseada na posição dos dígitos

heapsort
- memória interna
- instável
- O(n log n)
- porção ordenada: final

mergesort
- memória interna ou externa
- estável
- O(n log n)
- porção ordenada: subvetores intercalados

quicksort
- memória interna
- instável
- O(n log n) (no caso médio)
- porção ordenada: ao redor do pivô

### Premissas da "Disciplina"
- Padrão de desenvolvimento:
    - Ideia de MVC
    - Documentação
    - Benchmark
        - Medições
            - Tempo
            - Complexidade
                - Quantidade de Comparações
                - Quantidade de Trocas
                
**ATIVIDADE 06/08/2026:** Organizar o seguinte [código](https://github.com/alexandrezamberlan/pesquisa_ordenacao/blob/master/2_ExemploMedicaoTempo.java) no modelo MVC
- Projeto para se basear: https://github.com/alexandrezamberlan/pesquisa_ordenacao/tree/master/14-Ideia_MVC

---

## Semana 1
### Introdução da Disciplina
- Criação do repositório da disciplina + revisão de Estrutura de Dados
