package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utilidades {

    public static List<Integer> gerarLista(int quantidade, int inicio, int fim) {
        List<Integer> lista = new ArrayList<>();
        Random gerador = new Random();
        for (int i = 0; i < quantidade; i++) {
            lista.add(gerador.nextInt(fim - inicio) + inicio);
        }
        return lista;
    }

    public static class Estatisticas {
        private final long comparacoes;
        private final long trocas;
        private final long tempoMs;

        public Estatisticas(long comparacoes, long trocas, long tempoMs) {
            this.comparacoes = comparacoes;
            this.trocas = trocas;
            this.tempoMs = tempoMs;
        }

        public long getComparacoes() {
            return comparacoes;
        }

        public long getTrocas() {
            return trocas;
        }

        public long getTempoMs() {
            return tempoMs;
        }
    }
}
