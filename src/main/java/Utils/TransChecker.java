package Utils;

import Controller.DataAcessObjects.ClienteDAO;
import Models.Cliente;
import Utils.Exception.CPFException;
import Utils.Exception.LoginException;
import Utils.Exception.TransacaoException;

import java.util.List;

public class TransChecker {
    public static  boolean isPasswordValid(String cpf, String senha) throws LoginException {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.findByCpf(cpf);
        if (cliente.getSenha().equals(senha)) {
            return true;
        }
        throw new LoginException();
    }
    public static  boolean isSaldoAvaible(String cpf, Double valor) throws TransacaoException {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.findByCpf(cpf);
        if (cliente.getSaldo() >= valor) {
            return true;
        }
        throw new TransacaoException("Saldo insuficiente");
    }
    public static boolean isTransValida(String cpfOrigem, String cpDestino, Double valor, String Senha) throws CPFException, TransacaoException, LoginException {
        //todo
        isPasswordValid(cpfOrigem, Senha);
        boolean cadastrado1 = CPF.isCPFCadastrado(cpfOrigem);
        boolean cadastrado2 =CPF.isCPFCadastrado(cpDestino);
        if (!cadastrado1 || !cadastrado2) {
            throw new CPFException("CPF não cadastrado");
        }
        isSaldoAvaible(cpfOrigem, valor);

        return true;
    }

    
}
