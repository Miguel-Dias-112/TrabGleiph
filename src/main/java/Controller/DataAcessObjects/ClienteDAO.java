/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Controller.DataAcessObjects;

import Models.Arquivo;
import Models.Bank.Transacao;
import Models.Usuarios.Cliente;
import Utils.Checkers.CpfChecker;
import Utils.Checkers.LoginChecker;
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
import java.util.logging.Level;
import java.util.logging.Logger;

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

        boolean removido = clientes.removeIf(cliente -> {
            try {
                return cliente.getCpf().equals(CpfChecker.formatarCPF(cpf));
            } catch (CPFException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        });

        if (removido) {
            save(clientes);
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
    
    public void editarCliente(String cpf, String nome, String login, String senha) throws EditarException, CPFException {
        cpf = CpfChecker.formatarCPF(cpf);
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

    public boolean realizarSaque(String cpf, double valor, String senha) throws CPFException, LoginException {
        cpf = CpfChecker.formatarCPF(cpf);
        
        if (!LoginChecker.isPasswordValid(cpf, senha)) {
            throw new LoginException();
        }
        
        List<Cliente> clientes = findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                int saldo = (int) cliente.getConta().getSaldo();
                saldo -= valor;
                cliente.getConta().setSaldo(saldo);
                cliente.getConta().adicionarTransacao(new Transacao(valor, "Saque"));
                save(clientes); 
                return true;
            }
        }
        return false;
    }

    public boolean realizarDeposito(String cpf, double valor, String senha) throws CPFException, LoginException {
        cpf = CpfChecker.formatarCPF(cpf);
        
        if (!LoginChecker.isPasswordValid(cpf, senha)) {
            throw new LoginException();
        }
        
        List<Cliente> clientes = findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                int saldo = (int) cliente.getConta().getSaldo();
                saldo += valor;
                cliente.getConta().setSaldo(saldo);
                cliente.getConta().adicionarTransacao(new Transacao(valor, "Deposito"));
                save(clientes); 
                return true;
            }
        }
        return false;
    }
    public boolean realizarTransferencia(String cpfOrigem, String cpfDestino, double valor, String senha) throws CPFException, TransacaoException, LoginException {
        
        cpfOrigem = CpfChecker.formatarCPF(cpfOrigem);
        cpfDestino = CpfChecker.formatarCPF(cpfDestino);
        
        if (cpfOrigem.isEmpty() || cpfDestino.isEmpty() || senha.isEmpty()) {
            throw new IllegalArgumentException("CPF de origem, CPF de destino e senha não podem estar vazios.");
        }   

        if (valor <= 0) {
            throw new IllegalArgumentException("O valor da transferência deve ser maior que zero.");
        }
        
        if(cpfOrigem.equals(cpfDestino)){
            throw new IllegalArgumentException("As contas origem e destino devem ser diferentes.");
        }
        
        List<Cliente> usuarios = findAll();
        try {
            boolean valida = TransChecker.isTransValida(cpfOrigem, cpfDestino, valor, senha);
            if (valida == false) {
                return false;
            }
            for (Cliente usuario : usuarios) {
                double saldo;
                if (usuario.getCpf().equals(cpfDestino)) {
                    saldo = (double) usuario.getConta().getSaldo();
                    saldo = usuario.getConta().getSaldo();
                    saldo += valor;
                    usuario.getConta().setSaldo(saldo);
                    usuario.getConta().adicionarTransacao(new Transacao(valor, "Transferência Recebida"));
    
                }
                if (usuario.getCpf().equals(cpfOrigem)) {
                    saldo = (double) usuario.getConta().getSaldo();
                    saldo = usuario.getConta().getSaldo();
                    saldo -= valor;
                    usuario.getConta().setSaldo(saldo);
                    usuario.getConta().adicionarTransacao(new Transacao(valor, "Transferência Enviada"));
                }
            }
            save(usuarios);
            return true;
        } catch (CPFException e) {
            throw new CPFException(e.getMessage());
        } catch (TransacaoException e) {
            throw new TransacaoException(e.getMessage());
        } catch (LoginException e) {
            throw new LoginException();
        }
        //return false;
    }
    
        public boolean realizarInvestimento(String cpf, double valor, String descricaoInvestimento) {
        List<Cliente> clientes = findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                double saldoAtual = cliente.getConta().getSaldo();

                if (saldoAtual < valor) {
                    return false; 
                }

                cliente.getConta().setSaldo(saldoAtual - valor);

                Transacao investimento = new Transacao(
                        -valor,
                        "Investimento em " + descricaoInvestimento);
                cliente.getConta().adicionarTransacao(investimento);

                save(clientes);
                return true;
            }
        }
        return false; 
    }
        
    public boolean aprovarCredito(String clienteId, double valor) {
        List<Cliente> clientes = findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(clienteId)) {
                double saldoAtual = cliente.getConta().getSaldo();
                cliente.getConta().setSaldo(saldoAtual + valor);

                Transacao credito = new Transacao(valor, "Crédito aprovado");
                cliente.getConta().adicionarTransacao(credito);

                save(clientes);
                return true;
            }
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
    public Cliente findByCpf(String cpf) throws CPFException {
        cpf = CpfChecker.formatarCPF(cpf);
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

    public Cliente findById(String id) {
        List<Cliente> clientes = findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }
}
