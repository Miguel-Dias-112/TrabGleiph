/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package br.ufjf.trabfinal;
import java.io.File;
import java.util.List;

/**
 *
 * @author MeuPawDeAsa
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        TransDAO t = new TransDAO();
        List<Transacao> transacoes= List.of(
               // new Transacao()
        );
        t.save(transacoes);
    }
}
