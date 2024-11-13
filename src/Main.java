import javax.swing.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Banco banco = new Banco("ddd");

        llenarDatos(banco);

        String[] opc = {"registrarCliente", "buscarCliente", "modificarCliente",
                "crearCuenta", "reporteGeneral", "para clientes"};

        ImageIcon icon = new ImageIcon("res/banco.png");
        int opcion = 0;
        do {
            opcion = JOptionPane.showOptionDialog(null, banco.reporteGeneral(), null,
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

    private static void llenarDatos(Banco banco) {
        Cliente cliente = new Cliente("Bagabundo", 9876, banco);
        Cliente cliente1 = new Cliente("Jonatan", 6789, banco);

        banco.getClientes().add(cliente);
        banco.getClientes().add(cliente1);

        Ahorro cuenta = (Ahorro) banco.crearCuenta(TipoCuenta.AHORRO, 123, cliente);
        Corriente cuenta1 = (Corriente) banco.crearCuenta(TipoCuenta.CORRIENTE,321, cliente1);

        cuenta.depositar(10000, LocalDateTime.of(2024, 9, 12, 10, 30));
        cuenta1.depositar(5500, LocalDateTime.of(2024, 9, 12, 11, 30));
        cuenta.depositar(2000, LocalDateTime.of(2024, 9, 13, 10, 30));
        cuenta1.depositar(2300, LocalDateTime.of(2024, 9, 13, 10, 33));
        cuenta1.retirar(550, LocalDateTime.of(2024, 9, 13, 11, 22));
        cuenta.depositar(3300, LocalDateTime.of(2024, 9, 13, 11, 30));
        cuenta.depositar(1000, LocalDateTime.of(2024, 9, 13, 11, 55));
        cuenta.retirar(400, LocalDateTime.of(2024, 9, 14, 11, 56));
        cuenta.transferir(321, 250, LocalDateTime.of(2024, 9, 14, 12, 11));
        cuenta1.transferir(123, 770, LocalDateTime.of(2024, 9, 15, 22, 1));
        cuenta1.depositar(360, LocalDateTime.of(2024, 9, 15, 12, 22));
        cuenta1.depositar(900, LocalDateTime.of(2024, 9, 16, 14, 11));

    }
}

