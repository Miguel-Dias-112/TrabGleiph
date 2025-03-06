package Utils.Persistence;

import java.util.List;

import Models.Usuarios.Caixa;

public interface CaixaPersist {
    public void save(List<Caixa> usuario);
    public List<Caixa> findAll(); 
}