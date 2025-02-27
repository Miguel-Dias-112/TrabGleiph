package Utils.Persistence;

import Models.Credito;
import java.util.List;

public interface CreditoPersist {
    public void save(List<Credito> creditos);
    public List<Credito> findAll(); 
}