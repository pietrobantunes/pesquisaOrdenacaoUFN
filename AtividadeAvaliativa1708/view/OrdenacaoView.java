package view;

import java.util.List;
import model.Utilidades.Estatisticas;

public class OrdenacaoView {

    public void exibirLista(List<Integer> lista, String frase) {
        System.out.println(frase);
        for (Integer item : lista) {
            System.out.println(item);
        }
        System.out.println("--------------------------");
        System.out.println("Total de registros: " + lista.size());
    }

    public void exibirEstatisticas(String rotina, Estatisticas estatisticas) {
        System.out.println(rotina + " -> comparacoes: " + estatisticas.getComparacoes()
                + ", trocas: " + estatisticas.getTrocas()
                + ", tempo (ms): " + estatisticas.getTempoMs());
    }
}
