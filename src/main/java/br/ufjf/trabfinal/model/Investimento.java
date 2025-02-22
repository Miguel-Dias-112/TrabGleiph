package br.ufjf.trabfinal.model;

public class Investimento {
    private String id;
    private String tipo; // Ex: Renda Fixa, Renda Variável
    private double valor;
    private double taxaRendimento;

    // Construtor
    public Investimento(String id, String tipo, double valor, double taxaRendimento) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.taxaRendimento = taxaRendimento;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }
}