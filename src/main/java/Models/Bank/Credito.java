package Models.Bank;
import java.text.DecimalFormat;

public class Credito {
    final private DecimalFormat df = new DecimalFormat("000");
    private static int ultimoId = 1;
    private String id;
    private double valor;
    private String status;
    private String clienteId;

    public Credito(String clienteId, double valor) {
        this.id = "CRED" + df.format(ultimoId++);
        this.valor = valor;
        this.status = "Pendente";
        this.clienteId = clienteId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }
}