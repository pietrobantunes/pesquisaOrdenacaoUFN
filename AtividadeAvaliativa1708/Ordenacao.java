package ordenacao;

import java.util.List;
import model.OrdenacaoUtilidades;
import view.OrdenacaoView;
import controller.OrdenacaoController;

public class Ordenacao {
    public static void bolha(List<> lista) {
            boolean houveTroca;
            int tmp;
            int qtdComparacoes = 0, qtdTrocas = 0;

            do {
                houveTroca = false;
                for (int i = 0; i < lista.size()-1; i++){
                    qtdComparacoes++;
                    if (lista.get(i) > lista.get(i+1)) {
                        qtdTrocas++;
                        houveTroca = true;
                        tmp = lista.get(i);
                        lista.set(i, lista.get(i+1));
                        lista.set(i+1, tmp);
                    }
                }
            } while (houveTroca);
        }

    public static void selecao(List<> lista) {
        int posMenor;
        int tmp;
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

    public static void insercao(List<> lista) {
        int i, j;
        int tmp;
        int qtdComparacoes = 0, qtdTrocas = 0;

            for (i = 1; i < lista.Count(); i++) {
                tmp = lista.get(i);
                for (j = i - 1; j >= 0; j--) {
                    qtdComparacoes++;
                    if (tmp < lista.get(j)) {
                        lista.set(j + 1, lista.get(j));
                        qtdTrocas++;
                    } else break;
                }
                lista.set(j + 1, tmp);
                qtdTrocas++;
            }
        }

    public static void agitacao(List<> lista) {
        boolean houveTroca;
        int tmp;
        int ini = 0;
        int fim = lista.size();
        int qtdComparacoes = 0, qtdTrocas = 0;

        do {
            houveTroca = false;
            for (int i = ini; i < fim-1; i++){
                qtdComparacoes++;
                if (lista.get(i) > lista.get(i+1)) {
                    qtdTrocas++;
                    houveTroca = true;
                    tmp = lista.get(i);
                    lista.set(i, lista.get(i+1));
                    lista.set(i+1, tmp);
                }
            }

            if (!houveTroca) {
                break;
            }
            fim--;

            houveTroca = false;
            for (int i = fim; i > ini+1; i--){
                qtdComparacoes++;
                if (lista.get(i) < lista.get(i-1)) {
                    qtdTrocas++;
                    houveTroca = true;
                    tmp = lista.get(i);
                    lista.set(i, lista.get(i-1));
                    lista.set(i-1, tmp);
                }
            }
            ini++;

        } while (houveTroca);
    }
}
