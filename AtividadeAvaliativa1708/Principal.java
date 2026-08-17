import controller.OrdenacaoController;

public class Principal {
    public static void main(String[] args) {
        OrdenacaoController controller = new OrdenacaoController();
        controller.executar(1000, 0, 10000);
    }
}
