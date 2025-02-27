package Utils.Persistence;

import Models.Investimento;
import java.util.List;

public interface InvestimentoPersist {
    public void save(List<Investimento> investimentos);
    public List<Investimento> findAll(); 
}