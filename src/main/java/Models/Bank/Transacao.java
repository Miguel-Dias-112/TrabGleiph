package Models.Bank;

//tirei data/hora, acredito que nao seja necessario. mas seria interessante

public class Transacao {
    private String id;
    private double valor;
    private String descricao;

    public Transacao(double valor) {
        this.valor = valor;
    }

    public Transacao(double valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
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

    // mockup para testes sem a interface grafica
    @Override
    public String toString() {
        return "Transacao{" +
                "id='" + id + '\'' +
                ", valor=" + valor +
                '}';
    }
}