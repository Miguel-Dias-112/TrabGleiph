package Controller.DataAcessObjects;

import Models.Arquivo;
import Models.Credito;
import Utils.GsonUtil;
import Utils.Persistence.CreditoPersist;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class CreditoDAO implements CreditoPersist {
    private static final String DIRECTORY = "data";
    private static final String PATH = DIRECTORY + File.separator + "analisesDeCredito.json";

    @Override
    public void save(List<Credito> creditos) {
        String json = GsonUtil.toJson(creditos);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs(); 
        }

        Arquivo.save(PATH, json); 
    }

    @Override
    public List<Credito> findAll() {
        String json = Arquivo.read(PATH);

        List<Credito> creditos = new ArrayList<>();
        if (!json.trim().isEmpty()) {
            Type tipoLista = new TypeToken<List<Credito>>() {}.getType();
            creditos = GsonUtil.fromJson(json, tipoLista);

            if (creditos == null) {
                creditos = new ArrayList<>();
            }
        }

        return creditos;
    }
}