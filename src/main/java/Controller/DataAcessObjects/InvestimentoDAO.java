package Controller.DataAcessObjects;

import Models.Investimento;
import Utils.Persistence.InvestimentoPersist;
import Utils.GsonUtil;
import Models.Arquivo;
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
}