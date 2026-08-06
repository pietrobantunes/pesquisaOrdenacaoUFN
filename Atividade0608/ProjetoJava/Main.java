import model.Utilidades;
import view.ListaView;
import controller.ListaController;

/*
Construir um programa que popule 2 listas: uma com números aleatórios e outra com números sequenciais... temporizando cada rotina
*/
public class Main {
    public static void main(String[] args) {
        Utilidades model = new Utilidades();
        ListaView view = new ListaView();
        ListaController controller = new ListaController(model, view);

        controller.executarProcessamento();
    }
}
