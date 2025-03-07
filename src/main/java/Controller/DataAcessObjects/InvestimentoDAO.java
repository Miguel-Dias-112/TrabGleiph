/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Controller.DataAcessObjects;

import Models.Arquivo;
import Models.Bank.Investimento;
import Utils.GsonUtil;
import Utils.Persistence.InvestimentoPersist;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class InvestimentoDAO implements InvestimentoPersist {

    private static final String DIRECTORY = "data";
    private static final String PATH = DIRECTORY + File.separator + "investimentos.json";

    @Override
    public void save(List<Investimento> investimentos) {
        String json = GsonUtil.toJson(investimentos);
        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs(); 
        }
        Arquivo.save(PATH, json);
    }

    @Override
    public List<Investimento> findAll() {
        String json = Arquivo.read(PATH);
        List<Investimento> investimentos = new ArrayList<>();
        if (!json.trim().isEmpty()) {
            Type tipoLista = new TypeToken<List<Investimento>>() {}.getType();
            investimentos = GsonUtil.fromJson(json, tipoLista);
            if (investimentos == null) {
                investimentos = new ArrayList<>();
            }
        }
        return investimentos;
    }

    public void adicionarInvestimento(Investimento novoInvestimento) {
        List<Investimento> investimentos = findAll();
        investimentos.add(novoInvestimento);
        save(investimentos);
    }

    public void removerInvestimento(String nomeInvestimento) {
        List<Investimento> investimentos = findAll();
        boolean removido = investimentos.removeIf(investimento -> investimento.getNome().equals(nomeInvestimento));

        if (removido) {
            save(investimentos);
            System.out.println("Investimento removido com sucesso!");
        } else {
            System.out.println("Investimento não encontrado.");
        }
    }
}