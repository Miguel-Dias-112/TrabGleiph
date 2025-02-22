package br.ufjf.trabfinal.model;

public class Saque extends Transacao {
    public Saque(double valor) {
        super(valor); 
    }

    // mockup para testes sem a interface grafica
    @Override
    public String toString() {
        return "Saque{" +
                "id='" + getId() + '\'' +
                ", valor=" + getValor() +
                '}';
    }
}