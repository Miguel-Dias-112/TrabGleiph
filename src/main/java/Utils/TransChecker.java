package Utils;

import Controller.DataAcessObjects.ClienteDAO;
import Models.Cliente;
import Utils.Exception.CPFException;
import Utils.Exception.TransacaoException;

import java.util.List;

public class TransChecker {
    public static  boolean isPasswordValid(String cpf, String senha) throws CPFException {
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf) && cliente.getSenha().equals(senha)) {
                return true;
            }
        }
        throw new CPFException("Senha incorreta");
    }
    public static  boolean isSaldoAvaible(String cpf, Double valor) throws TransacaoException {
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.findAll();
       
        throw new TransacaoException("Saldo insuficiente");
    }
    public static boolean isTransValida(String cpfOrigem, String cpDestino, Double valor, String Senha) throws CPFException, TransacaoException {
        //todo
        isPasswordValid(cpfOrigem, Senha);
        CPF.isCPFCadastrado(cpfOrigem);
        CPF.isCPFCadastrado(cpDestino);
        isSaldoAvaible(cpfOrigem, valor);
 

        return true;
    }

    
}
