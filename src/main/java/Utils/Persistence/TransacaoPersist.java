package Utils.Persistence;

import Models.Transacao;
import Models.Usuario;

import java.util.List;

public interface TransacaoPersist {
    public void save(List<Usuario> usuarios);
    public List<Usuario> findAll(); 
}