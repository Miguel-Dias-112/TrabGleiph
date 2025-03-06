package Controller.DataAcessObjects;

import Models.Arquivo;
import Models.Bank.Transacao;
import Models.Usuarios.Cliente;
import Utils.Exception.CPFException;
import Utils.Exception.EditarException;
import Utils.Exception.LoginException;
import Utils.Exception.TransacaoException;
import Utils.GsonUtil;
import Utils.Checkers.TransChecker;
import Utils.Persistence.ClientePersist;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class ClienteDAO implements ClientePersist {
    private static final String DIRECTORY = "data";
    private static final String PATH = DIRECTORY + File.separator + "clientes.json";
    @Override
    public void save(List<Cliente> clientes) {
        String json = GsonUtil.toJson(clientes);
        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        Arquivo.save(PATH, json);
    }
 
    public void adicionarNovoCliente(Cliente novoCliente) {
        List<Cliente> clientes = findAll();
        clientes.add(novoCliente);
        save(clientes);
    }
    
    public void deletarCliente(String cpf) {
        List<Cliente> clientes = findAll();
        boolean removido = clientes.removeIf(cliente -> cliente.getCpf().equals(cpf));

        if (removido) {
            save(clientes);
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
    
    public void editarCliente(String cpf, String nome, String login, String senha) throws EditarException {
        List<Cliente> clientes = findAll();
        boolean encontrado = false;

        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            if (cliente.getCpf().equals(cpf)) {
                Cliente newCliente = cliente;
                
                try{
                    newCliente.setNome(nome);
                    newCliente.setLogin(login);
                    newCliente.setSenha(senha);
                    
                }catch(EditarException error){
                    throw new EditarException(error.toString());
                }
                
                clientes.set(i, newCliente);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            save(clientes);
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public boolean realizarSaque(String cpf, double valor, String senha) {
        List<Cliente> clientes = findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                int saldo = (int) cliente.getConta().getSaldo();
                saldo -= valor;
                cliente.getConta().setSaldo(saldo);
                cliente.getConta().adicionarTransacao(new Transacao(valor));
                save(clientes); 
                return true;
            }
        }
        return false;
    }
    public boolean realizarDeposito(String cpf, double valor, String senha) {
        List<Cliente> clientes = findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                int saldo = (int) cliente.getConta().getSaldo();
                saldo += valor;
                cliente.getConta().setSaldo(saldo);
                cliente.getConta().adicionarTransacao(new Transacao(valor));
                save(clientes); 
                return true;
            }
        }
        return false;
    }
    public boolean realizarTransferencia(String cpfOrigem, String cpfDestino, double valor, String senha) {
        List<Cliente> usuarios = findAll();
        try {
            TransChecker.isTransValida(cpfOrigem, cpfDestino, valor, senha);
            for (Cliente usuario : usuarios) {
                double saldo;
                if (usuario.getCpf().equals(cpfDestino)) {
                    saldo = (double) usuario.getConta().getSaldo();
                    saldo = usuario.getConta().getSaldo();
                    saldo += valor;
                    usuario.getConta().setSaldo(saldo);
                    usuario.getConta().adicionarTransacao(new Transacao(valor));
    
                }
                if (usuario.getCpf().equals(cpfOrigem)) {
                    saldo = (double) usuario.getConta().getSaldo();
                    saldo = usuario.getConta().getSaldo();
                    saldo -= valor;
                    usuario.getConta().setSaldo(saldo);
                    usuario.getConta().adicionarTransacao(new Transacao(valor));
                }
            }
            save(usuarios);
            return true;
        } catch (CPFException e) {
            e.printStackTrace();
        } catch (TransacaoException e) {
            e.printStackTrace();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        return false;
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
    public Cliente findByCpf(String cpf) {
        List<Cliente> clientes = findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }
    public Cliente findByLogin(String login) {
        List<Cliente> clientes = findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getLogin().equals(login)) {
                return cliente;
            }
        }
        return null;
    }
}
