# Pesquisa e Ordenação
---
## Semana 5
### compareTo()
- [-1] -> 1º elemento é menor que o 2º
- [+2] -> 1º elemento é maior que o 2º
- [0] -> 1º e 2º elemento são idênticos 
---
## Semana 4
- Entrega da Atividade Avaliativa: https://github.com/pietrobantunes/pesquisaOrdenacaoUFN/tree/main/AtividadeAvaliativa1708
### Continuação dos métodos de ordenação
Pente (comb sort)
- É baseado no Bolha, ou seja, é uma tentativa de melhorar o Bolha.
- ATENÇÃO: a partir deste método, há COMPARAÇÕES a uma distância X. Isso gera uma pré-organização da estrutura, diminuindo número de comparações e trocas.
- Instável
- Memória Interna
- A estrutura possui 2 porções/partes (1a é a ordenada; 2a é a desordenada)
- Há as variáveis clássicas do bolha: i, houveTroca, tmp.
- Há a variável distancia que é calculada pelo tamanho da estrutura dividido por 1.3
- Adequado somente para listas ou estruturas prontas tipo lista de uma linguagem de programação
```
n = 7
0   1   2   3   4   5   6   
7   1   4   2   3   9   8       distancia = (int)n / 1.3 = 5
7   1   4   2   3   9   8       distancia = (int)distancia / 1.3 = 3
2   1   4   7   3   9   8       distancia = (int)n / 1.3 = 2
2   1   3   7   4   9   8       distancia = (int)n / 1.3 = 1
1   2   3   4   7   8   9       distancia = (int)n / 1.3 = 1
1   2   3   4   7   8   9
```
```
void pente(List<> lista) {
    bool houveTroca;
    int tmp;
    int distancia = lista.size();
    int qtdComparacoes = 0, qtdTrocas = 0;

    do {
        distancia = (int)distancia / 1.3;
        if (distancia < 1) {
            distancia = 1;
        }
        houveTroca = false;
        for (int i = 0; i+distancia < lista.size(); i++){
            qtdComparacoes++;
            if (lista.get(i) > lista.get(i+distancia)) {
                qtdTrocas++;
                houveTroca = True;
                tmp = lista[i];
                lista.set(i, lista[i+distancia]);
                lista.set(i+distancia, tmp);
            }
        }
    } while (houveTroca || distancia > 1);
}
```
---
## Semana 3
### Ordenação
Bolha (bubble sort)
- memória interna
- estável
- O(nˆ2) = Alta complexidade
- porção ordenada: final
- A estrutura possui 2 partes/porções (1a é a desordenada; 2a parte é a ordenada)
- Adequado para listaes e listas
- Trabalha com o conceito de trocas, ou seja, enquanto houver trocas, o algoritmo não para.
- É considerado bolha, porque 'leva' os maiores para o final da estrutura
- OBS: se a estrutura estiver ordenada ou quase, o bolha terá excelente desempenho
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
```
void bolha(List<> lista) {
    boolean houveTroca;
    int tmp;
    int qtdComparacoes = 0, qtdTrocas = 0;

    do {
        houveTroca = False;
        for (int i = 0; i < lista.size()-1; i++){
            qtdComparacoes++;
            if (lista.get(i) > lista.get(i+1)) {
                qtdTrocas++;
                houveTroca = True;
                tmp = lista.get(i);
                lista.set(i, lista.get(i+1));
                lista.set(i+1, tmp);
            }
        }
    } while (houveTroca);
}
```

Seleção (selection sort)
- memória interna
- instável
- O(nˆ2) = Alta complexidade
- porção ordenada: início
- A estrutura possui 2 partes/porções (1a é a ordenada; 2a parte é a desordenada)
- Adequado para listaes e listas
- Trabalha com conceito de trocas não contíguas. É considerado seleção porque ele seleciona os menores para o início da estrutura
- OBS: se a estrutura estiver ordenada, o método vai funcionar/processar como se a estrutura estivesse desordenada
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
```
void selecao(List<> lista) {
    int posMenor;
    int qtd_comparacoes = 0, qtd_trocas = 0;
    for (int i = 0; i < lista.size()-1; i++) {
        posMenor = i;
        for (int j = i+1; j < lista.size(); j++) {
            qtd_comparacoes++;
            if (lista.get(j) < lista.get(posMenor)) {
                posMenor = j;
            }
        }
        if (i != posMenor) {
            qtd_trocas++;
            tmp = lista.get(i);
            lista.set(i, lista.get(posMenor));
            lista.set(posMenor, tmp);
        }
    }
}
```

Inserção (inserction sort)
- memória interna
- estável
- O(nˆ2) = Alta complexidade
- porção ordenada: início
- A estrutura possui 2 partes/porções (1a é a ordenada; 2a parte é a desordenada)
- Adequado para listaes e listas
- Trabalha com o conceito de inserção na porção inicial, com isso, pode diminuir o número de comparações
- OBS: se a estrutura estiver ordenada ou quase, o inserção terá excelente desempenho
- Qual o melhor método de ordenação? Depende: tamanho da estrutura; de como a estrutura já está previamente ordenada;
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
```
void insercao(Lista<> lista) {
    int i, j;
    int tmp;
    int qtdComparacoes = 0, qtdTrocas = 0;

    for (i = 1; i < lista.Count(); i++) {
        tmp = lista[i];
        for (j = i - 1; j >= 0; j--) {
            qtdComparacoes++;
            if (tmp < lista[j]) {
                lista[j + 1] = lista[j]; //shift eh trocar para o lado
                qtdTrocas++;
            } else break;
        }
        lista[j + 1] = tmp;
        qtdTrocas++;
    }
}
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

agitação (shakesort ou cocktailsort)
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
