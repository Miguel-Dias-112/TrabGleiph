/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.trabfinal;

import static br.ufjf.trabfinal.PersistenceTrans.DIRECTORY;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
/**
 *
 * @author ice
 */
public class TransDAO implements PersistenceTrans{
    
    private static final String PATH = DIRECTORY+ File.separator +"contatos.json";
    
 
    @Override
    public void save(List<Transacao> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);


    }
    @Override
    public List<Transacao> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Transacao> contatos = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Transacao>>() {}.getType();
            contatos = gson.fromJson(json, tipoLista);

            if (contatos == null)
                contatos = new ArrayList<>();
        }

        return contatos;
    }
}
