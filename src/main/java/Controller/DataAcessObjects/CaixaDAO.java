package Controller.DataAcessObjects;

import Models.Arquivo;
import Models.Caixa;
import Utils.Exception.EditarException;
import Utils.GsonUtil;
import Utils.Persistence.CaixaPersist;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class CaixaDAO implements CaixaPersist {
    private static final String DIRECTORY = "data";
    private static final String PATH = DIRECTORY + File.separator + "caixas.json";
    @Override
    public void save(List<Caixa> caixas) {
        String json = GsonUtil.toJson(caixas);
        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        Arquivo.save(PATH, json);
    }
 
    public void adicionarNovoCaixa(Caixa novoCaixa) {
        List<Caixa> caixas = findAll();
        caixas.add(novoCaixa);
        save(caixas);
    }
    
    public void deletarCaixa(String cpf) {
        List<Caixa> caixas = findAll();
        boolean removido = caixas.removeIf(caixa -> caixa.getCpf().equals(cpf));

        if (removido) {
            save(caixas);
            System.out.println("Caixa removido com sucesso!");
        } else {
            System.out.println("Caixa não encontrado.");
        }
    }
    
    public void editarCaixa(String cpf, String nome, String login, String senha) throws EditarException {
        List<Caixa> caixas = findAll();
        boolean encontrado = false;

        for (int i = 0; i < caixas.size(); i++) {
            Caixa caixa = caixas.get(i);
            if (caixa.getCpf().equals(cpf)) {
                Caixa newCaixa = caixa;
                
                try{
                    newCaixa.setNome(nome);
                    newCaixa.setLogin(login);
                    newCaixa.setSenha(senha);
                    
                }catch(EditarException error){
                    throw new EditarException(error.toString());
                }
                
                caixas.set(i, newCaixa);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            save(caixas);
            System.out.println("Caixa atualizado com sucesso!");
        } else {
            System.out.println("Caixa não encontrado.");
        }
    }
    @Override
    public List<Caixa> findAll() {
        String json = Arquivo.read(PATH);
        List<Caixa> caixas = new ArrayList<>();
        if (!json.trim().isEmpty()) {
            Type tipoLista = new TypeToken<List<Caixa>>() {
            }.getType();
            caixas = GsonUtil.fromJson(json, tipoLista);
            if (caixas == null) {
                caixas = new ArrayList<>();
            }
        }
        return caixas;
    }
    public Caixa findByCpf(String cpf) {
        List<Caixa> caixas = findAll();
        for (Caixa caixa : caixas) {
            if (caixa.getCpf().equals(cpf)) {
                return caixa;
            }
        }
        return null;
    }
}
