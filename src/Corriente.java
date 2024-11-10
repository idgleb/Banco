public class Corriente extends Cuenta{
    public Corriente(int cbu, double saldo, Cliente cliente) {
        super(cbu, saldo, cliente);
    }

    @Override
    public void depositar() {

    }

    @Override
    public void retirar() {

    }

    @Override
    public void transferir() {

    }


    @Override
    public String toString() {
        return "Corriente{} " + super.toString() + "\n";
    }
}
