package model;

import java.util.List;
import model.Utilidades.Estatisticas;

public class Ordenacao {

    public static Estatisticas bolha(List<Integer> lista) {
        long inicio = System.nanoTime();
        long comparacoes = 0, trocas = 0;
        boolean houveTroca;
        int tmp;

        do {
            houveTroca = false;
            for (int i = 0; i < lista.size() - 1; i++) {
                comparacoes++;
                if (lista.get(i) > lista.get(i + 1)) {
                    trocas++;
                    houveTroca = true;
                    tmp = lista.get(i);
                    lista.set(i, lista.get(i + 1));
                    lista.set(i + 1, tmp);
                }
            }
        } while (houveTroca);

        return new Estatisticas(comparacoes, trocas, tempoMs(inicio));
    }

    public static Estatisticas selecao(List<Integer> lista) {
        long inicio = System.nanoTime();
        long comparacoes = 0, trocas = 0;
        int posMenor, tmp;

        for (int i = 0; i < lista.size() - 1; i++) {
            posMenor = i;
            for (int j = i + 1; j < lista.size(); j++) {
                comparacoes++;
                if (lista.get(j) < lista.get(posMenor)) {
                    posMenor = j;
                }
            }
            if (i != posMenor) {
                trocas++;
                tmp = lista.get(i);
                lista.set(i, lista.get(posMenor));
                lista.set(posMenor, tmp);
            }
        }

        return new Estatisticas(comparacoes, trocas, tempoMs(inicio));
    }

    public static Estatisticas insercao(List<Integer> lista) {
        long inicio = System.nanoTime();
        long comparacoes = 0, trocas = 0;
        int i, j, tmp;

        for (i = 1; i < lista.size(); i++) {
            tmp = lista.get(i);
            for (j = i - 1; j >= 0; j--) {
                comparacoes++;
                if (tmp < lista.get(j)) {
                    lista.set(j + 1, lista.get(j));
                    trocas++;
                } else {
                    break;
                }
            }
            lista.set(j + 1, tmp);
            trocas++;
        }

        return new Estatisticas(comparacoes, trocas, tempoMs(inicio));
    }

    public static Estatisticas agitacao(List<Integer> lista) {
        long inicio = System.nanoTime();
        long comparacoes = 0, trocas = 0;
        boolean houveTroca;
        int tmp;
        int ini = 0;
        int fim = lista.size() - 1;

        do {
            houveTroca = false;
            for (int i = ini; i < fim; i++) {
                comparacoes++;
                if (lista.get(i) > lista.get(i + 1)) {
                    trocas++;
                    houveTroca = true;
                    tmp = lista.get(i);
                    lista.set(i, lista.get(i + 1));
                    lista.set(i + 1, tmp);
                }
            }
            if (!houveTroca) {
                break;
            }
            fim--;

            houveTroca = false;
            for (int i = fim; i > ini; i--) {
                comparacoes++;
                if (lista.get(i) < lista.get(i - 1)) {
                    trocas++;
                    houveTroca = true;
                    tmp = lista.get(i);
                    lista.set(i, lista.get(i - 1));
                    lista.set(i - 1, tmp);
                }
            }
            ini++;

        } while (houveTroca);

        return new Estatisticas(comparacoes, trocas, tempoMs(inicio));
    }

    private static long tempoMs(long inicioNano) {
        return (System.nanoTime() - inicioNano) / 1_000_000;
    }
}
