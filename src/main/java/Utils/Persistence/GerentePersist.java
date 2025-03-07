/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Utils.Persistence;

import java.util.List;

import Models.Usuarios.Gerente;

public interface GerentePersist {
    public void save(List<Gerente> usuario);
    public List<Gerente> findAll(); 
}