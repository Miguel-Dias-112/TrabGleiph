package Persistence;

import Models.Transacao;
import java.util.List;

public interface TransacaoPersist {
    public void save(List<Transacao> transacoes);
    public List<Transacao> findAll(); 
}