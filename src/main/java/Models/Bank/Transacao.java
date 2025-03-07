/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Models.Bank;

import java.text.NumberFormat;
import java.util.Locale;

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

    @Override
    public String toString() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return "-> " + descricao + '\'' +
                ", Valor: " + currencyFormatter.format(valor) + "\n";
    }
}