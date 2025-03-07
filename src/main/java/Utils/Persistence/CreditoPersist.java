/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Utils.Persistence;

import java.util.List;

import Models.Bank.Credito;

public interface CreditoPersist {
    public void save(List<Credito> creditos);
    public List<Credito> findAll(); 
}