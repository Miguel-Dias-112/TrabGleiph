package Utils.Persistence;

import Models.Usuarios.Cliente;
import Models.Usuarios.Usuario;

import java.util.List;

public interface ClientePersist {
    public void save(List<Cliente> usuario);
    public List<Cliente> findAll(); 
}