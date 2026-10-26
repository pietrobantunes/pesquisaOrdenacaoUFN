# Pesquisa e Ordenação
## Semana 14
# Pesquisa Digital: TRIE e PATRICIA

As árvores Trie e PATRICIA são estruturas de dados baseadas em pesquisa digital, o que significa que elas buscam informações caractere por caractere (ou bit a bit) em vez de comparar a chave inteira de uma vez só (como ocorre em uma árvore binária tradicional). Elas são amplamente utilizadas para buscas de prefixos, sistemas de autocompletar e roteamento de redes.
A principal diferença entre elas está na forma como lidam com o espaço na memória: enquanto a Trie padrão cria um nó para cada caractere, a PATRICIA compacta a estrutura unindo caminhos que não se ramificam.

## 1. Árvore Trie (Árvore de Prefixo)

A Trie (derivada da palavra retrieval) armazena chaves (geralmente strings) de forma que cada nó representa um único caractere.
• Como funciona: Se você inserir as palavras "AMOR", "AMAR" e "GATO", a árvore compartilhará o prefixo "AM" para as duas primeiras palavras. O caminho para "AMOR" se dividirá no 'O' e o de "AMAR" no 'A'.
• Vantagem: A busca é extremamente rápida. O tempo de busca depende apenas do tamanho da palavra que você está procurando, e não do número total de palavras salvas na árvore.
• Desvantagem: Alto desperdício de memória. Se houver sequências longas de caracteres sem nenhuma ramificação (como a palavra "CONSTITUCIONAL"), a árvore criará um nó separado para cada letra, gerando muitos ponteiros nulos.

## 2. Árvore PATRICIA (Trie Compacta)

A árvore PATRICIA (Practical Algorithm To Retrieve Information Coded In Alphanumeric) resolve o problema de desperdício de memória da Trie padrão através da compactação de caminhos.
• Como funciona: Sempre que um nó possui apenas um filho, ele é mesclado (compactado) com o nó pai. Em vez de testar caractere por caractere em nós separados, a PATRICIA armazena sequências de caracteres nos nós ou utiliza um índice para indicar qual bit/caractere deve ser testado na busca.
• Exemplo visual: Na palavra "GATO", se não houver nenhuma outra palavra começando com 'G', a Trie comum criaria 4 nós (G -> A -> T -> O). A PATRICIA cria um único nó contendo a string inteira "GATO".
• Vantagem: Muito mais econômica em termos de espaço de memória, mantendo a mesma velocidade de busca eficiente da Trie.

## Comparação Direta

| Característica | Árvore Trie Padrão | Árvore PATRICIA |
| :--- | :--- | :--- |
| **Estrutura do Nó** | Cada nó armazena um único caractere (ou caractere por caractere nos caminhos). | Nós agrupam sequências de caracteres ou indicam a posição do próximo teste. |
| **Gasto de Memória** | Alto (muitos nós intermediários com apenas um filho). | Baixo (otimizada eliminando nós redundantes). |
| **Ramificações** | Pode ter nós com apenas um filho. | Todos os nós internos possuem pelo menos duas ramificações. |
| **Complexidade** | Mais simples de entender e implementar. | Implementação e lógica de remoção/inserção mais complexas. |

## Simular a inserção do seguinte conjunto de palavras: "AMAR", "AMOR" e "GATO".

### 1. Estrutura na Árvore Trie Padrão

Na Trie padrão, cada caractere vira um nó individual. Se não houver ramificação, o caminho continua descendo letra por letra:

```text
       [ Raiz ]
      /        \
    [A]        [G]

     |          |
    [M]        [A]
   /   \        |
 [A]   [O]      [T]

  |     |       |
 [R]*  [R]*     [O]*
```

(O caractere * indica o fim de uma palavra válida).

  - Análise: Repare na palavra "GATO". Como não há nenhuma outra palavra que comece com "G", ela gera um caminho linear de 4 nós (G -> A -> T -> O). Isso gera um grande desperdício de memória e ponteiros.

### 2. Estrutura na Árvore PATRICIA (Trie Compacta)

A árvore PATRICIA elimina os nós redundantes de filho único, agrupando os caracteres que não se ramificam em um único bloco:
```text
       [ Raiz ]
      /        \
   "AM"       "GATO"*
   /   \
 "AR"* "OR"*
```

  - Análise: O prefixo comum "AM" foi mantido, mas as terminações "AR" e "OR" foram compactadas em nós únicos após a ramificação. O termo "GATO" não possui ramificações, por isso foi totalmente compactado em um único nó logo abaixo da raiz.

### Resumo do Ganho de Espaço

  - Trie Padrão: Precisou de 9 nós no total para armazenar as 3 palavras.
  - PATRICIA: Precisou de apenas 4 nós no total para armazenar exatamente as mesmas palavras.

## Código Python

```python
class NodeTrie:
    def __init__(self):
        # Cada nó possui um dicionário de filhos (caractere -> NodeTrie)
        self.filhos = {}
        # Flag para indicar se o nó atual marca o fim de uma palavra válida
        self.fim_da_palavra = False

class Trie:
    def __init__(self):
        self.raiz = NodeTrie()

    def inserir(self, palavra: str) -> None:
        """Insere uma palavra na árvore caractere por caractere."""
        no_atual = self.raiz
        for caractere in palavra:
            # Se o caractere não existir nos filhos do nó atual, cria um novo nó
            if caractere not in no_atual.filhos:
                no_atual.filhos[caractere] = NodeTrie()
            # Avança para o nó filho
            no_atual = no_atual.filhos[caractere]
        # Ao final da palavra, marca o nó como o fim de uma palavra válida
        no_atual.fim_da_palavra = True

    def buscar(self, palavra: str) -> bool:
        """Busca se uma palavra exata existe na árvore."""
        no_atual = self.raiz
        for caractere in palavra:
            if caractere not in no_atual.filhos:
                return False
            no_atual = no_atual.filhos[caractere]
        return no_atual.fim_da_palavra

    def comeca_com(self, prefixo: str) -> bool:
        """Verifica se existe alguma palavra na árvore que começa com o prefixo dado."""
        no_atual = self.raiz
        for caractere in prefixo:
            if caractere not in no_atual.filhos:
                return False
            no_atual = no_atual.filhos[caractere]
        return True

# --- Testando a implementação ---
if __name__ == "__main__":
    trie = Trie()
    
    # Inserindo as palavras do nosso exemplo anterior
    palavras = ["AMAR", "AMOR", "GATO"]
    for p in palavras:
        trie.inserir(p)
    
    # Testes de busca exata
    print(trie.buscar("AMOR"))   # Saída: True
    print(trie.buscar("AMO"))    # Saída: False (Existe o prefixo, mas não a palavra exata)
    print(trie.buscar("CÃO"))    # Saída: False
    
    # Testes de busca por prefixo (Autocompletar)
    print(trie.comeca_com("AM"))  # Saída: True (Atende a "AMAR" e "AMOR")
    print(trie.comeca_com("GA"))  # Saída: True (Atende a "GATO")
    print(trie.comeca_com("XE"))  # Saída: False

```
---
## Semana 11 + 12
### Hash
Hash ou Tabelas Hash O(1)
- Técnica de 'espalhamento' ou posicionamento, via endereço, por cálculo a partir de uma chave de entrada.
  - Técnica do Sistema Operacional (SO) para gerenciamento de arquivos em memória secundária,
    ou seja, quando um arquivo é criado, o SO aplica a função hash sobre um dos campos do arquivo (chave),
    em geral identificador, e define uma posição na memória secundária.
    - Vantagens da técnica:
      - acesso direto, ou seja, pesquisa/busca otimizada
      - Problema pode ocorrer?
        COLISÃO, ou seja, chaves iguais que levam ao mesmo endereção
      - Subtécnicas de tratamento de colisão
        - via uso de vetor (alocação estática)
          - Open addressing (endereçamento aberto) com TABELA HASH FECHADA (tem limite do vetor)
          - via uso de lista encadeada (alocação dinâmica)
            - Linked list com TABELA HASH ABERTA (com limite só da memória)
      - IDEAIS para estruturas de dados MUIIIIITO GRANDES, para facilitar o espalhamento, ou seja,
        diminuir as colisões
 ```
 package hash;

import java.util.HashSet;

public class ExemploHash1 {
    
    public static void main(String[] args) {
        HashSet<String> tabelaHashString = new HashSet<>();
        
        tabelaHashString.add("alexandre");
        tabelaHashString.add("hermes");
        tabelaHashString.add("davi");
        tabelaHashString.add("alan");
        tabelaHashString.add("alexandre");
        tabelaHashString.add("davi");
        
        System.out.println(tabelaHashString);
    }
}
```
### Desafio Semestral HashMap
Construir um programa em Java que:
1) armazene em um HashMap os dados do arquivo telephony_sessions.txt, usando como
   classe a classe implementada em Sessao.java
- ler o arquivo
- splitar as linhas
- criar um objeto do tipo Sessao
- adicionar (put) no HashMap
2) permite procurar um número de celular e o retorne seja os dados da sessão
3) DESAFIO: pesquise sobre o uso de threads e tente procurar N celulares ao mesmo tempo
---
## Semana 10
### Pesquisa
Pesquisa Binária
```
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Principal {
    public static void main(String a[]) {
        ArrayList<Integer> lista = new ArrayList<>();
        Util.carregarArquivoEmLista("numeros.txt", lista);
        Ordenacao.pente(lista);
        Util.exibirLista(lista);
        System.out.println("Total de elementos na lista: " + lista.size());

        int numeroPesquisa = Integer.parseInt(JOptionPane.showInputDialog("Digite um numero inteiro para pesquisar: "));
        JOptionPane.showMessageDialog(null,"Resultado: " + lista.contains(numeroPesquisa) + "  " + Ordenacao.pesquisaBinaria(numeroPesquisa, lista));

    }
}
```
```
import java.util.ArrayList;

public class Ordenacao {
    public static boolean contido(int numero, ArrayList<Integer> lista) {
        long qtdComparacoes = 0;
        for (Integer item : lista) {
            qtdComparacoes++;
            if (item == numero) {
                return true;
            }
        }
        return false;
    }


    public static boolean pesquisaBinaria(int numero, ArrayList<Integer> lista) {
        int ini = 0;
        int fim = lista.size()-1;
        int meio;
        long qtdComparacoes = 0;
        do {
            meio = (int)(ini+fim)/2;

            qtdComparacoes++;
            if (numero == lista.get(meio)) {
                return true;
            }
            if (numero < lista.get(meio)) {
                fim = meio - 1;                
            } else {
                ini = meio + 1;
            }
        } while (ini <= fim);
        System.out.println("Qauntidade de comparaçoes: " + qtdComparacoes);

        return false;
    }


    public static ArrayList bolha(ArrayList<Integer> lista) {
        ArrayList<Float> metricas = new ArrayList<>();
        long qtdComparacoes = 0;
        long qtdTrocas = 0;
        int aux;
        boolean houveTroca;
        int i;
        do {
            houveTroca = false;
            for (i = 0; i < lista.size() - 1; i++) {
                qtdComparacoes++;
                if (lista.get(i) > lista.get(i + 1)) {
                    houveTroca = true;
                    aux = lista.get(i);
                    lista.set(i, lista.get(i + 1));
                    lista.set(i + 1, aux);
                    qtdTrocas++;
                }
            }
        } while (houveTroca);
        metricas.add((float) qtdComparacoes);
        metricas.add((float) qtdTrocas);
        return metricas;
    }

    public static ArrayList selecao(ArrayList<Integer> lista) {
        ArrayList<Float> metricas = new ArrayList<>();
        long qtdComparacoes = 0;
        long qtdTrocas = 0;
        int i, j, posMenor, aux;
        posMenor = 0;

        for (i = 0; i < lista.size(); i++) {
            posMenor = i;
            for (j = i + 1; j < lista.size(); j++) {
                qtdComparacoes++;
                if (lista.get(j) < lista.get(posMenor)) {
                    posMenor = j;
                }
            }
            if (posMenor != i) {
                aux = lista.get(i);
                lista.set(i, lista.get(posMenor));
                lista.set(posMenor, aux);
                qtdTrocas++;
            }
        }

        metricas.add((float) qtdComparacoes);
        metricas.add((float) qtdTrocas);
        return metricas;
    }

    public static ArrayList insercao(ArrayList<Integer> lista) {
        ArrayList<Float> metricas = new ArrayList<>();
        long qtdComparacoes = 0;
        long qtdTrocas = 0;
        int i, j, aux;

        for (i = 1; i < lista.size(); i++) {
            aux = lista.get(i);
            for (j = i - 1; j > 0 && aux < lista.get(j); j--, qtdComparacoes++) {
                qtdTrocas++;
                lista.set(j + 1, lista.get(j));
            }
            lista.set(j + 1, aux);
        }
        metricas.add((float) qtdComparacoes);
        metricas.add((float) qtdTrocas);
        return metricas;
    }

    public static ArrayList pente(ArrayList<Integer> lista) {
        ArrayList<Float> metricas = new ArrayList<>();
        long qtdComparacoes = 0;
        long qtdTrocas = 0;
        int aux;
        boolean houveTroca;
        int i;
        int distancia = lista.size();
        do {
            distancia = (int) (distancia / 1.3);
            if (distancia <= 0) {
                distancia = 1;
            }
            houveTroca = false;
            for (i = 0; i + distancia < lista.size(); i++) {
                qtdComparacoes++;
                if (lista.get(i) > lista.get(i + distancia)) {
                    houveTroca = true;
                    aux = lista.get(i);
                    lista.set(i, lista.get(i + distancia));
                    lista.set(i + distancia, aux);
                    qtdTrocas++;
                }
            }
        } while (distancia > 1 || houveTroca);
        metricas.add((float) qtdComparacoes);
        metricas.add((float) qtdTrocas);
        return metricas;
    }
}
```
```
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Util {

    public static boolean carregarArquivoEmLista(String nomeArquivo, ArrayList<Integer> lista) {
        try {
            FileReader procurador;
            procurador = new FileReader(nomeArquivo);
            BufferedReader leitor = new BufferedReader(procurador);
            String linha;
            do {
                linha = leitor.readLine();
                if (linha != null) {
                    lista.add(Integer.parseInt(linha));
                }                
            } while (linha != null);
            leitor.close();
            return true;
        } catch (Exception e) {
            //System.out.println("Erro " + e.getMessage());
            return false;
        }
    }

    public static void exibirLista(ArrayList<Integer> lista) {
        for (Integer item : lista) {
            System.out.println(item);
        }
    }
}
```
---
## Semana 9
### Continuação dos métodos de ordenação
Shellsort
- É baseado no Inserção, ou seja, é uma tentativa de melhoria via o uso da ANÁLISE A DISTÂNCIA (tipo pente)
- Instável 
- Memória Interna
```
void shell(Lista<> lista) {
    int i, j;
    int tmp;
    int qtdComparacoes = 0, qtdTrocas = 0;
    int distancia = 1;

    int referenciaTamanho = 3;

    do {
        distancia = referenciaTamanho * distancia + 1;
    } while (distancia < n);
    
    do {
        distancia = (int)(distancia / referenciaTamanho);
        
        for (i = distancia; i < n; i++) {
            tmp = vetor[i];
            for (j = i - distancia; j >= 0; j = j - distancia) {
                qtdComparacoes++;
                if (tmp < vetor[j]) {
                    vetor[j + distancia] = vetor[j];
                    qtdTrocas++;
                } else break;
            }
            vetor[j + distancia] = tmp;
            qtdTrocas++;
        }
    } while (distancia > 1);
}
```

Heapsort
- WIP

Bucket
- WIP

Radix
- WIP

---
## Semana 7 + 8
- Atividade extra no NetBeans usando JavaSwing
---
## Semana 5 + 6
### compareTo()
- [-1] -> 1º elemento é menor que o 2º
- [+2] -> 1º elemento é maior que o 2º
- [0] -> 1º e 2º elemento são idênticos
```
// Ordenação Padrão
public int compareTo(Pessoa o) {
    int resultadoNome = this.nome.compareTo(o.getNome());
    if (resultadoNome != 0) {
        return resultadoNome;
    }
    return Integer.compare(this.idade, o.getIdade());
}

// Ordenação Temporária
lista.sort((p1, p2) -> {
    int compIdade = Integer.compare(p1.getIdade(), p2.getIdade());
    if(compIdade != 0) {
        return compIdade;
    }
    return p1.getNome().compareTo(p2.getNome());
});
```
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
