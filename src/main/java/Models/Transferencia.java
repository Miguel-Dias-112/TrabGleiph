package Models;

public class Transferencia extends Transacao {
    private String contaDestino;

    public Transferencia(double valor, String contaDestino) {
        super(valor);
        this.contaDestino = contaDestino;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    // mockup para testes sem a interface grafica
    @Override
    public String toString() {
        return "Transferencia{" +
                "id='" + getId() + '\'' +
                ", valor=" + getValor() +
                ", contaDestino='" + contaDestino + '\'' +
                '}';
    }
}