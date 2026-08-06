package controller;

import model.Utilidades;
import view.ListaView;

public class ListaController {
    private Utilidades model;
    private ListaView view;

    public ListaController(Utilidades model, ListaView view) {
        this.model = model;
        this.view = view;
    }

    public void executarProcessamento() {
        // rotina1 - popular uma lista com 100000 aleatorios na faixa 100 a 100000
        long tempoRotina1 = model.medirTempoExecucao(() -> 
            model.popularLista(model.getListaAleatoria(), 100000, 100, 100000, true)
        );
        // view.exibrLista(model.getListaAleatoria(), "Lista aleatoria");
        view.exibirTempo("rotina 1", tempoRotina1);

        // rotina2 - popular uma lista com 100000 de forma crescente 0 na posição 0, 1 na posição 1, e assim por diante
        long tempoRotina2 = model.medirTempoExecucao(() -> 
            model.popularLista(model.getListaSequencial(), 100000, 1, 100000, false)
        );
        // view.exibrLista(model.getListaSequencial(), "Lista sequencial");
        view.exibirTempo("rotina 2", tempoRotina2);
    }
}
