package Controller.DataAcessObjects;

import Models.Arquivo;
import Models.Usuarios.Cliente;
import Models.Usuarios.Gerente;
import Utils.Exception.EditarException;
import Utils.GsonUtil;
import Utils.Persistence.GerentePersist;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class GerenteDAO implements GerentePersist {
    private static final String DIRECTORY = "data";
    private static final String PATH = DIRECTORY + File.separator + "gerentes.json";
    @Override
    public void save(List<Gerente> gerentes) {
        String json = GsonUtil.toJson(gerentes);
        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        Arquivo.save(PATH, json);
    }
 
    public void adicionarNovoGerente(Gerente novoGerente) {
        List<Gerente> gerentes = findAll();
        gerentes.add(novoGerente);
        save(gerentes);
    }
    
    public void deletarGerente(String cpf) {
        List<Gerente> gerentes = findAll();
        boolean removido = gerentes.removeIf(gerente -> gerente.getCpf().equals(cpf));

        if (removido) {
            save(gerentes);
            System.out.println("Gerente removido com sucesso!");
        } else {
            System.out.println("Gerente não encontrado.");
        }
    }
    
    public void editarGerente(String cpf, String nome, String login, String senha) throws EditarException {
        List<Gerente> gerentes = findAll();
        boolean encontrado = false;

        for (int i = 0; i < gerentes.size(); i++) {
            Gerente gerente = gerentes.get(i);
            if (gerente.getCpf().equals(cpf)) {
                Gerente newGerente = gerente;
                
                try{
                    newGerente.setNome(nome);
                    newGerente.setLogin(login);
                    newGerente.setSenha(senha);
                    
                }catch(EditarException error){
                    throw new EditarException(error.toString());
                }
                
                gerentes.set(i, newGerente);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            save(gerentes);
            System.out.println("Gerente atualizado com sucesso!");
        } else {
            System.out.println("Gerente não encontrado.");
        }
    }
    @Override
    public List<Gerente> findAll() {
        String json = Arquivo.read(PATH);
        List<Gerente> gerentes = new ArrayList<>();
        if (!json.trim().isEmpty()) {
            Type tipoLista = new TypeToken<List<Gerente>>() {
            }.getType();
            gerentes = GsonUtil.fromJson(json, tipoLista);
            if (gerentes == null) {
                gerentes = new ArrayList<>();
            }
        }
        return gerentes;
    }
        public Gerente findByLogin(String login) {
        List<Gerente> gerentes = findAll();
        for (Gerente gerente : gerentes) {
            if (gerente.getLogin().equals(login)) {
                return gerente;
            }
        }
        return null;
    }

        public Gerente findByCpf(String cpf) {
        List<Gerente> gerentes = findAll();
        for (Gerente gerente : gerentes) {
            if (gerente.getCpf().equals(cpf)) {
                return gerente;
            }
        }
        return null;
    }
}
