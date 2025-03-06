package Utils.Persistence;

import Models.Gerente;
import java.util.List;

public interface GerentePersist {
    public void save(List<Gerente> usuario);
    public List<Gerente> findAll(); 
}