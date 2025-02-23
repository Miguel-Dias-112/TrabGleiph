package Persistence;

import Models.Usuario;
import java.util.List;

public interface UsuarioPersist {
    public void save(List<Usuario> usuario);
    public List<Usuario> findAll(); 
}