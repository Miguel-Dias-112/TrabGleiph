package Models;

public class Investimento {
    private String nome;
    private int ano;
    private double retorno;

    // Construtor, getters e setters
    public Investimento(String nome, int ano, double retorno) {
        this.nome = nome;
        this.ano = ano;
        this.retorno = retorno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getRetorno() {
        return retorno;
    }

    public void setRetorno(double retorno) {
        this.retorno = retorno;
    }

    @Override
    public String toString() {
        return nome + " - Ano: " + ano + " - Retorno: " + retorno + "%";
    }
}