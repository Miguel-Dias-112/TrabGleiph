package Controller.DataAcessObjects;

import Models.Arquivo;
import Models.Cliente;
import Models.Transacao;
import Utils.Exception.EditarException;
import Utils.GsonUtil;
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

    public void realizarSaque(Cliente user, double valor) {
        List<Cliente> clientes = findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(user.getCpf())) {
                int saldo = (int) cliente.getConta().getSaldo();
                saldo -= valor;
                cliente.getConta().setSaldo(saldo);
                cliente.getConta().adicionarTransacao(new Transacao(valor));
                save(clientes); 
                return;
            }
        }
    }
    public void realizarDeposito(Cliente user, double valor) {
        List<Cliente> clientes = findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(user.getCpf())) {
                int saldo = (int) cliente.getConta().getSaldo();
                saldo += valor;
                cliente.getConta().setSaldo(saldo);
                cliente.getConta().adicionarTransacao(new Transacao(valor));
                save(clientes); 
                return;
            }
        }
    }
    public void realizarTransferencia(Cliente user, String cpfDestino, double valor) {
        List<Cliente> clientes = findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(user.getCpf())) {
                int saldo = (int) cliente.getConta().getSaldo();
                saldo -= valor;
                cliente.getConta().setSaldo(saldo);
                cliente.getConta().adicionarTransacao(new Transacao(valor));
                for (Cliente clienteDestino : clientes) {
                    if (clienteDestino.getCpf().equals(cpfDestino)) {
                        saldo = (int) clienteDestino.getConta().getSaldo();
                        saldo += valor;
                        clienteDestino.getConta().setSaldo(saldo);
                        clienteDestino.getConta().adicionarTransacao(new Transacao(valor));
                        save(clientes);
                        return;
                    }
                }
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
