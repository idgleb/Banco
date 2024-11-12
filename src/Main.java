import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Banco banco = new Banco("ddd");

        String[] opc = {"registrarCliente", "buscarCliente", "modificarCliente",
                "crearCuenta", "reporteGeneral", "para clientes"};

        ImageIcon icon = new ImageIcon("res/banco.png");
        int opcion = 0;
        do {
            opcion = JOptionPane.showOptionDialog(null, banco.reporte(), null,
                    0, 0, icon, opc, 0);
            switch (opcion) {
                case 0:
                    banco.registrarCliente();
                    break;
                case 1:
                    banco.buscarCliente();
                    break;
                case 2:
                    banco.modificarCliente();
                    break;
                case 3:
                    banco.crearCuenta();
                    break;
                case 4:
                    banco.reporteGeneralClientesCuentas();
                    break;
                case 5:
                    Cliente.operarCuentas(banco);
                    break;

            }
        } while (opcion != -1);


    }
}