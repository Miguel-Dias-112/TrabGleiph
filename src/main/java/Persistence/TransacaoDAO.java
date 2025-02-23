package Persistence;

import Models.Transacao;
import Utils.GsonUtil;
import Utils.Arquivo;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class TransacaoDAO implements TransacaoPersist {
    private static final String DIRECTORY = "data";
    private static final String PATH = DIRECTORY + File.separator + "transacoes.json";

    @Override
    public void save(List<Transacao> transacoes) {
        String json = GsonUtil.toJson(transacoes);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs(); 
        }

        Arquivo.save(PATH, json); 
    }

    @Override
    public List<Transacao> findAll() {
        String json = Arquivo.read(PATH);

        List<Transacao> transacoes = new ArrayList<>();
        if (!json.trim().isEmpty()) {
            Type tipoLista = new TypeToken<List<Transacao>>() {}.getType();
            transacoes = GsonUtil.fromJson(json, tipoLista);

            if (transacoes == null) {
                transacoes = new ArrayList<>();
            }
        }

        return transacoes;
    }
}