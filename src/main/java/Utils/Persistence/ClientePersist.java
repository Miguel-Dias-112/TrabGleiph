/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Utils.Persistence;

import Models.Usuarios.Cliente;
import java.util.List;

public interface ClientePersist {
    public void save(List<Cliente> usuario);
    public List<Cliente> findAll(); 
}