package Utils.Persistence;

import java.util.List;

import Models.Bank.Investimento;

public interface InvestimentoPersist {
    public void save(List<Investimento> investimentos);
    public List<Investimento> findAll(); 
}