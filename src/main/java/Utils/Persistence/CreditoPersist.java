package Utils.Persistence;

import java.util.List;

import Models.Bank.Credito;

public interface CreditoPersist {
    public void save(List<Credito> creditos);
    public List<Credito> findAll(); 
}