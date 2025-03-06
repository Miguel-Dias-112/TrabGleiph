package Utils.Persistence;

import Models.Cliente;
import Models.Usuario;
import java.util.List;

public interface ClientePersist {
    public void save(List<Cliente> usuario);
    public List<Cliente> findAll(); 
}