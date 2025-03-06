package Utils.Persistence;

import Models.Caixa;
import java.util.List;

public interface CaixaPersist {
    public void save(List<Caixa> usuario);
    public List<Caixa> findAll(); 
}