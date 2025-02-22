package br.ufjf.trabfinal.model;

public class Credito {
    private String id;
    private double valor;
    private String status; // Ex: Pendente, Aprovado, Negado

    // Construtor
    public Credito(String id, double valor) {
        this.id = id;
        this.valor = valor;
        this.status = "Pendente";
    }

    // Getters e Setters
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
}