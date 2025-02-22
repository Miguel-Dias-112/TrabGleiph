package br.ufjf.trabfinal.persistence;

import br.ufjf.trabfinal.model.Transacao;
import java.util.List;

public interface TransacaoPersist {
    void save(List<Transacao> transacoes);
    List<Transacao> findAll(); 
}