package Persistence;

import Models.Usuario;
import Utils.GsonUtil;
import Utils.Arquivo;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class UsuarioDAO implements UsuarioPersist {
    private static final String DIRECTORY = "data";
    private static final String PATH = DIRECTORY + File.separator + "usuarios.json";

    @Override
    public void save(List<Usuario> usuarios) {
        String json = GsonUtil.toJson(usuarios);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs(); 
        }

        Arquivo.save(PATH, json); 
    }

    @Override
    public List<Usuario> findAll() {
        String json = Arquivo.read(PATH);

        List<Usuario> usuarios = new ArrayList<>();
        if (!json.trim().isEmpty()) {
            Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();
            usuarios = GsonUtil.fromJson(json, tipoLista);

            if (usuarios == null) {
                usuarios = new ArrayList<>();
            }
        }

        return usuarios;
    }
}