package Controller.DataAcessObjects;

import Models.Arquivo;
import Models.Cliente;
import Models.Conta;
import Models.Transacao;
import Models.Usuario;
import Utils.CPF;
import Utils.GsonUtil;
import Utils.Persistence.ClientePersist;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class ClienteDao implements ClientePersist {
    private static final String DIRECTORY = "data";
    private static final String PATH = DIRECTORY + File.separator + "usuarios.json";
    @Override
    public void save(List<Cliente> usuarios) {
        String json = GsonUtil.toJson(usuarios);
        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        Arquivo.save(PATH, json);
    }
 
    public void adicionarNovoUsuario(Cliente novoUsuario) {
        List<Cliente> usuarios = findAll();
        usuarios.add(novoUsuario);
        save(usuarios);
    }
    
    public void deletarUsuario(String cpf) {
        List<Cliente> usuarios = findAll();
        boolean removido = usuarios.removeIf(usuario -> usuario.getCpf().equals(cpf));

        if (removido) {
            save(usuarios);
            System.out.println("Usuário removido com sucesso!");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void realizarSaque(Cliente user, double valor) {
        List<Cliente> usuarios = findAll();
        for (Cliente usuario : usuarios) {
            if (usuario.getCpf().equals(user.getCpf())) {
                int saldo = (int) usuario.getConta().getSaldo();
                saldo -= valor;
                usuario.getConta().setSaldo(saldo);
                usuario.getConta().adicionarTransacao(new Transacao(valor));
                save(usuarios); 
                return;
            }
        }
    }
    public void realizarDeposito(Cliente user, double valor) {
        List<Cliente> usuarios = findAll();
        for (Cliente usuario : usuarios) {
            if (usuario.getCpf().equals(user.getCpf())) {
                int saldo = (int) usuario.getConta().getSaldo();
                saldo += valor;
                usuario.getConta().setSaldo(saldo);
                usuario.getConta().adicionarTransacao(new Transacao(valor));
                save(usuarios); 
                return;
            }
        }
    }
    public void realizarTransferencia(Cliente origem, String cpfDestino, double valor, String senha) {
        if (!origem.getSenha().equals(senha)) {
            System.out.println("Senha incorreta!");
            return;
        }
       
        List<Cliente> usuarios = findAll();
        for (Cliente usuario : usuarios) {
            double saldo;
            if (usuario.getCpf().equals(cpfDestino)) {
                saldo = (double) usuario.getConta().getSaldo();
                saldo = usuario.getConta().getSaldo();
                saldo += valor;
                usuario.getConta().setSaldo(saldo);
                usuario.getConta().adicionarTransacao(new Transacao(valor));
               
            }
            if (usuario.getCpf().equals(origem.getCpf())) {
                saldo = (double) origem.getConta().getSaldo();
                saldo = origem.getConta().getSaldo();
                saldo -= valor;
                origem.getConta().setSaldo(saldo);
                origem.getConta().adicionarTransacao(new Transacao(valor));
                
                
            }
        }
        save(usuarios);
        return;
    }
    @Override
    public List<Cliente> findAll() {
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
