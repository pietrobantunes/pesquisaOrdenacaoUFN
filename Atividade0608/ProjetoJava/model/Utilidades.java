package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utilidades {
    private List<Integer> listaAleatoria = new ArrayList<>();
    private List<Integer> listaSequencial = new ArrayList<>();

    /**
     * método de classe que popula lista ou com números aleatorios e de forma sequencial dentro de uma faixa
     * @param lista
     * @param quantidadeNumeros
     * @param inicio
     * @param fim
     * @param aleatorio - se true os números devem ser aleatórios
     */
    public void popularLista(List<Integer> lista, long quantidadeNumeros, int inicio, int fim, boolean aleatorio) {
        Random gerador = new Random();
        
        if (aleatorio) {
            for (long i = 0; i < quantidadeNumeros; i++) {
                lista.add( gerador.nextInt(inicio, fim) );
            }
        }

        if (!aleatorio) {
            for (long i = inicio; i < quantidadeNumeros; i++) {
                lista.add( (int)i );
            }
        }
    }

    // método auxiliar para isolar o cálculo do tempo de execução
    public long medirTempoExecucao(Runnable rotina) {
        long tempoInicio = System.nanoTime();
        rotina.run();
        long tempoFim = System.nanoTime();
        return (tempoFim - tempoInicio) / 1000000;
    }

    public List<Integer> getListaAleatoria() { return listaAleatoria; }
    public List<Integer> getListaSequencial() { return listaSequencial; }
}
