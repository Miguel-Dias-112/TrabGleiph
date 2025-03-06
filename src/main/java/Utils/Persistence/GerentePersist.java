package Utils.Persistence;

import java.util.List;

import Models.Usuarios.Gerente;

public interface GerentePersist {
    public void save(List<Gerente> usuario);
    public List<Gerente> findAll(); 
}