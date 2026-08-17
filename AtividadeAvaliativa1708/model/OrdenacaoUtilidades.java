package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ordenacao;

public class OrdenacaoUtilidades {
    public List<Integer> lista = new ArrayList<>();

    public void popularLista(List<Integer> lista, long quantidadeNumeros, int inicio, int fim) {
        Random gerador = new Random();
        
        for (long i = 0; i < quantidadeNumeros; i++) {
                lista.add(gerador.nextInt(inicio, fim));
        }
    }

    public long medirTempoExecucao(Runnable tempo) {
        long tempoInicio = System.nanoTime();
        tempo.run();
        long tempoFim = System.nanoTime();
        return (tempoFim - tempoInicio) / 1000000;
    }

    public void ordenar(List<Integer> lista){
          Ordenacao.selecao(lista);
    }

    public List<Integer> getListaAleatoria() { return lista; }
}