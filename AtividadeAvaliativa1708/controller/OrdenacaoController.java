package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import model.Ordenacao;
import model.Utilidades;
import model.Utilidades.Estatisticas;
import view.OrdenacaoView;

public class OrdenacaoController {
    private final OrdenacaoView view = new OrdenacaoView();

    public void executar(int quantidade, int inicio, int fim) {
        List<Integer> listaOriginal = Utilidades.gerarLista(quantidade, inicio, fim);

        ordenarExibir("Bolha", listaOriginal, Ordenacao::bolha);
        ordenarExibir("Selecao", listaOriginal, Ordenacao::selecao);
        ordenarExibir("Insercao", listaOriginal, Ordenacao::insercao);
        ordenarExibir("Agitacao", listaOriginal, Ordenacao::agitacao);
    }

    private void ordenarExibir(String nome, List<Integer> listaOriginal, Function<List<Integer>, Estatisticas> algoritmo) {
        List<Integer> lista = new ArrayList<>(listaOriginal);
        Estatisticas estatisticas = algoritmo.apply(lista);
        view.exibirEstatisticas(nome, estatisticas);
    }
}
