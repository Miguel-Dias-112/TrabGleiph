/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Utils.Persistence;

import Models.Usuarios.Usuario;
import java.util.List;

public interface TransacaoPersist {
    public void save(List<Usuario> usuarios);
    public List<Usuario> findAll(); 
}