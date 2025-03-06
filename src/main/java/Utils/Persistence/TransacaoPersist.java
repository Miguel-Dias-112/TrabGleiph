package Utils.Persistence;

import Models.Bank.Transacao;
import Models.Usuarios.Usuario;

import java.util.List;

public interface TransacaoPersist {
    public void save(List<Usuario> usuarios);
    public List<Usuario> findAll(); 
}