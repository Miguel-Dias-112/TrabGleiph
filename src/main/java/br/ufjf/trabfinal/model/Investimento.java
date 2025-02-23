package br.ufjf.trabfinal.model;

import java.text.DecimalFormat;

public class Investimento {
    final private DecimalFormat df = new DecimalFormat("000");
    private static int ultimoId;
    private String id;
    private String tipo;
    private double valor;
    private double taxaRendimento;

    public Investimento(String id, String tipo, double valor, double taxaRendimento) {
        this.id = "INVEST" + df.format(ultimoId++);
        this.tipo = tipo;
        this.valor = valor;
        this.taxaRendimento = taxaRendimento;
    }

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