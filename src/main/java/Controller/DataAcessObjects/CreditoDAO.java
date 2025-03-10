/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Controller.DataAcessObjects;

import Models.Arquivo;
import Models.Bank.Credito;
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

    public void adicionarCredito(Credito novoCredito) {
        List<Credito> creditos = findAll();
        creditos.add(novoCredito);
        save(creditos);
    }

    public void atualizarCredito(Credito creditoAtualizado) {
        List<Credito> creditos = findAll();
        for (int i = 0; i < creditos.size(); i++) {
            if (creditos.get(i).getId().equals(creditoAtualizado.getId())) {
                creditos.set(i, creditoAtualizado);
                break;
            }
        }
        save(creditos);
    }
}