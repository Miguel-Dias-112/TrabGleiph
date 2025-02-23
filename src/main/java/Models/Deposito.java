package Models;

public class Deposito extends Transacao {
    public Deposito(double valor) {
        super(valor); 
    }

    // mockup para testes sem a interface grafica
    @Override
    public String toString() {
        return "Deposito{" +
                "id='" + getId() + '\'' +
                ", valor=" + getValor() +
                '}';
    }
}