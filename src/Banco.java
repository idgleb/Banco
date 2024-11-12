import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Banco {

    private String nombre;

    private List<Cliente> clientes = new ArrayList<>();

    public Banco(String nombre) {
        this.nombre = nombre;
    }

    public void registrarCliente() {
        String nombre = MisFunciones.pedirStrNoVacio("Ingrese el nombre del cliente: ");
        if (nombre==null) return;
        int dni = MisFunciones.pedirNumeroMasCero("Ingrese el DNI: ");
        if (dni==-1) return;

        Cliente cliente = new Cliente(nombre, dni, this);
        clientes.add(cliente);

    }

    public void modificarCliente() {

        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay clientes");
            return;
        }

        int idCliente = JOptionPane.showOptionDialog(null, "Elije el cliente", null,
                0, 0, null, clientes.toArray(), 0);

        if (idCliente == -1) return;

        Cliente cliente = clientes.get(idCliente);

        int modCliente = JOptionPane.showOptionDialog(null, "Que queres modificar? en cliente " + cliente.getNombre(), null,
                0, 0, null, ModCliente.values(), 0);

        if (modCliente == -1) return;

        if (ModCliente.values()[modCliente] == ModCliente.DNI) {
            int dni = MisFunciones.pedirNumeroMasCero("Ingrese el nuevo DNI: ");
            if (dni == -1) return;
            cliente.setDni(dni);
            JOptionPane.showMessageDialog(null, "DNI Corregido");
        }
        if (ModCliente.values()[modCliente] == ModCliente.NOMBRE) {
            String nombre = MisFunciones.pedirStrNoVacio("Ingrese nuevo nombre del cliente: ");
            if (nombre==null) return;
            cliente.setNombre(nombre);
            JOptionPane.showMessageDialog(null, "nombre Corregido");
        }

    }

    public void buscarCliente() {

        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay clientes");
            return;
        }

        int modCliente = JOptionPane.showOptionDialog(null, "Elige de que parametro vamos a buscar el clientr ", null,
                0, 0, null, ModCliente.values(), 0);

        if (modCliente == -1) return;

        if (ModCliente.values()[modCliente] == ModCliente.DNI) {

            int dni = MisFunciones.pedirNumeroMasCero("Ingrese el DNI: ");

            if (dni == -1) return;

            for (Cliente cliente : clientes) {
                if (cliente.getDni() == dni) {
                    JOptionPane.showMessageDialog(null, "Encontrado  " + cliente);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "no encontrado el cliente");
        }

        if (ModCliente.values()[modCliente] == ModCliente.NOMBRE) {

            String nombre = MisFunciones.pedirStrNoVacio("Ingrese nombre del cliente: ");

            if (nombre == null) return;

            for (Cliente cliente : clientes) {
                if (cliente.getNombre().equals(nombre)) {
                    JOptionPane.showMessageDialog(null, "Encontrado  " + cliente);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "no encontrado el cliente");
        }

    }

    public void crearCuenta() {

        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero crea el cliente");
            return;
        }

        int idTipoCuenta = JOptionPane.showOptionDialog(null, "Elige tipo de cuenta ", null,
                0, 0, null, TipoCuenta.values(), 0);

        if (idTipoCuenta == -1) return;

        int cbu = MisFunciones.pedirNumeroMasCero("Ingrese el CBU de nueva cuenta:");

        if (cbu == -1) return;

        if (buscarCBU(cbu)) {

            JOptionPane.showMessageDialog(null, "El CBU ya existe");

        } else {

            int idCliente = JOptionPane.showOptionDialog(null, "Elige el cliente para asiciar este cuenta ", null,
                    0, 0, null, clientes.toArray(), 0);

            if (idCliente == -1) return;

            Cliente cliente = clientes.get(idCliente);

            if (TipoCuenta.values()[idTipoCuenta] == TipoCuenta.AHORRO) {
                Ahorro cuenta = new Ahorro(cbu, 0, cliente, 10, 1000);
                cliente.getCuentas().add(cuenta);
                JOptionPane.showMessageDialog(null, "El cuenta se ha registrado correctamente");
            }

            if (TipoCuenta.values()[idTipoCuenta] == TipoCuenta.CORRIENTE) {
                Corriente cuenta = new Corriente(cbu, 0, cliente);
                cliente.getCuentas().add(cuenta);
                JOptionPane.showMessageDialog(null, "El cuenta se ha registrado correctamente");
            }

        }


    }

    public void reporteGeneralClientesCuentas() {

        String[] opc = {"reporte por Cliente", "reporte por Fecha", "reporte por Cliente y Fecha"};

        ImageIcon icon = new ImageIcon("res/report.png");
        int opcion = 0;
        String reporte = "";
        do {
            opcion = JOptionPane.showOptionDialog(null, reporte, null,
                    0, 0, icon, opc, 0);
            switch (opcion) {
                case 0:
                    if (clientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay clientes");
                        break;
                    }
                    int idCliente = JOptionPane.showOptionDialog(null, "Elige el cliente", "Elige el cliente",
                            0, 0, null, clientes.toArray(), 0);
                    reporte = clientes.get(idCliente) + "\n";
                    for (Cuenta cuenta : clientes.get(idCliente).getCuentas()) {
                        for (Transaccion tr:cuenta.getTransacciones()) {
                            reporte = reporte + tr + "\n";
                        }

                    }

                    break;
                case 1:
                    if (clientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay clientes");
                        break;
                    }
                    break;
                case 2:
                    if (clientes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay clientes");
                        break;
                    }
                    //
                    break;
            }
        } while (opcion != -1);

    }

    public String reporte() {
        String reporte = "";
        for (Cliente cliente : clientes) {
            reporte += cliente + " \n";
            for (Cuenta cuenta : cliente.getCuentas()) {
                reporte += cuenta + "\n";
                for (Transaccion transaccion : cuenta.getTransacciones()) {
                    reporte += transaccion + "\n";
                }
            }
        }
        return reporte;
    }

    public boolean buscarCBU(int cbu) {
        for (Cliente cliente : clientes) {
            for (Cuenta cuenta : cliente.getCuentas()) {
                if (cuenta.getCbu() == cbu) return true;
            }
        }
        return false;
    }

    public Cuenta getCuentaPorCBU(int cbu) {
        for (Cliente cliente : clientes) {
            for (Cuenta cuenta : cliente.getCuentas()) {
                if (cuenta.getCbu() == cbu) return cuenta;
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }


    @Override
    public String toString() {
        return "Banco{" +
                "nombre='" + nombre + '\'' +
                ", clientes=" + clientes +
                "}\n";
    }


}
