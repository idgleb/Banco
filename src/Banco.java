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
        int dni = MisFunciones.pedirNumeroMasCero("Ingrese el DNI: ");
        Cliente cliente = new Cliente(nombre, dni, this);
        clientes.add(cliente);

    }

    public void modificarCliente() {

        int idCliente = JOptionPane.showOptionDialog(null, "Elije el cliente", null,
                0, 0, null, clientes.toArray(), 0);

        if (idCliente == -1) return;

        Cliente cliente = clientes.get(idCliente);

        int modCliente = JOptionPane.showOptionDialog(null, "Que queres modificar? en cliente "+ cliente.getNombre() , null,
                0, 0, null, ModCliente.values(), 0);

        if (modCliente == -1) return;

        if (ModCliente.values()[modCliente] == ModCliente.DNI){
            int dni = MisFunciones.pedirNumeroMasCero("Ingrese el nuevo DNI: ");
            cliente.setDni(dni);
            JOptionPane.showMessageDialog(null, "DNI Corregido");
        }
        if (ModCliente.values()[modCliente] == ModCliente.NOMBRE){
            String nombre = MisFunciones.pedirStrNoVacio("Ingrese nuevo nombre del cliente: ");
            cliente.setNombre(nombre);
            JOptionPane.showMessageDialog(null, "nombre Corregido");
        }

    }

    public void buscarCliente() {

        int modCliente = JOptionPane.showOptionDialog(null, "Elige de que parametro vamos a buscar el clientr ", null,
                0, 0, null, ModCliente.values(), 0);

        if (modCliente == -1) return;

        if (ModCliente.values()[modCliente] == ModCliente.DNI){
            int dni = MisFunciones.pedirNumeroMasCero("Ingrese el DNI: ");
            for (Cliente cliente : clientes){
                if (cliente.getDni() == dni){
                    JOptionPane.showMessageDialog(null, "Encontrado el cliente"+cliente.getNombre());
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "no encontrado el cliente");
        }

        if (ModCliente.values()[modCliente] == ModCliente.NOMBRE){
            String nombre = MisFunciones.pedirStrNoVacio("Ingrese nombre del cliente: ");
            for (Cliente cliente : clientes){
                if (cliente.getNombre().equals(nombre)){
                    JOptionPane.showMessageDialog(null, "Encontrado el cliente"+cliente.getNombre());
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "no encontrado el cliente");
        }

    }

    public void crearCuenta() {
        int cbu = MisFunciones.pedirNumeroMasCero("Ingrese el CBU de nueva cuenta:");


    }

    public void reporteGeneralClientesCuentas() {

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
