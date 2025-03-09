/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Controller.DataAcessObjects;

import Models.Arquivo;
import Models.Usuarios.Gerente;
import Utils.Checkers.CpfChecker;
import Utils.Exception.CPFException;
import Utils.Exception.EditarException;
import Utils.GsonUtil;
import Utils.Persistence.GerentePersist;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public boolean deletarGerente(String cpf) {
        List<Gerente> gerentes = findAll();

        boolean removido = gerentes.removeIf(gerente -> {
            try {
                // Retorna true se os CPFs forem iguais
                return gerente.getCpf().equals(CpfChecker.formatarCPF(cpf));
            } catch (CPFException ex) {
                Logger.getLogger(GerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false; // Retorna false se der erro na formatação
            }
        });

        return removido;
    }
    
    public boolean editarGerente(String cpf, String nome, String login, String senha) throws EditarException, CPFException {
        cpf = CpfChecker.formatarCPF(cpf);
        List<Gerente> gerentes = findAll();
        boolean editado = false;

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
                editado = true;
                break;
            }
        }

        return editado;
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
