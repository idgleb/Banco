import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private int dni;
    private Banco banco;
    private List<Cuenta> cuentas  = new ArrayList<>();

    public Cliente(String nombre, int dni, Banco banco) {
        this.nombre = nombre;
        this.dni = dni;
        this.banco = banco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", dni=" + dni +
                ", banco=" + banco +
                ", cuentas=" + cuentas +
                "}\n";
    }
}
