import java.util.ArrayList;
import java.util.List;

public class Banco {

    private String nombre;

    private List<Cliente> clientes = new ArrayList<>();

    public Banco(String nombre) {
        this.nombre = nombre;
    }

    public void registrarCliente(){

    }
    public void modificarCliente(){

    }
    public void buscarCliente(){

    }
    public void crearCuenta(){

    }
    public void reporteGeneralClientesCuentas(){

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
