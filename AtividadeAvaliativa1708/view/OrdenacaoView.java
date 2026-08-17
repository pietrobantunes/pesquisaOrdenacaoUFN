package view;

import java.util.List;

public class ListaView {
    public void exibirTempo(String rotina, long tempoCalculado) {
        System.out.println("Tempo (ms) " + rotina + ": " + tempoCalculado);
    }

    /**
     * método de classe que exibe conteúdo de uma lista de inteiros
     * @param lista
     * @param frase - para exibir no início do método
     */
    public void exibirLista(List<Integer> lista, String frase) {
        System.out.println(frase);
        for (Object item : lista) {
            System.out.println(item);
        }
        System.out.println("--------------------------");
        System.out.println("Total de registros: " + lista.size());
    }
}