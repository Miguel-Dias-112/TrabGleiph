package br.ufjf.trabfinal.persistence;

import br.ufjf.trabfinal.model.Transacao;
import java.util.List;

public interface TransacaoPersist {
    public void save(List<Transacao> transacoes);
    public List<Transacao> findAll(); 
}