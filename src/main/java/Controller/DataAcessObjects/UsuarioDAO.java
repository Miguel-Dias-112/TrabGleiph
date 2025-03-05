package Controller.DataAcessObjects;

import Models.Arquivo;
import Models.Cliente;
import Models.Usuario;
import Utils.GsonUtil;
import Utils.Persistence.UsuarioPersist;

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

    public void adicionarNovoUsuario(Usuario novoUsuario) {
        List<Usuario> usuarios = findAll();
        usuarios.add(novoUsuario);
        save(usuarios);
    }

    @Override
    public List<Usuario> findAll() {
        String json = Arquivo.read(PATH);

        List<Usuario> usuarios = new ArrayList<>();
        if (!json.trim().isEmpty()) {
            Type tipoLista = new TypeToken<List<Usuario>>() {
            }.getType();
            usuarios = GsonUtil.fromJson(json, tipoLista);

            if (usuarios == null) {
                usuarios = new ArrayList<>();
            }
        }
        return usuarios;
    }
    public List<Cliente> findClients() {
        String json = Arquivo.read(PATH);

        List<Cliente> clientes = new ArrayList<>();
        if (!json.trim().isEmpty()) {
            Type tipoLista = new TypeToken<List<Cliente>>() {
            }.getType();
            clientes = GsonUtil.fromJson(json, tipoLista);
            if (clientes == null) {
                clientes = new ArrayList<>();
            }
        }
        return clientes;
    }
}
