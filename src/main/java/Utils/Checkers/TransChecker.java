/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Utils.Checkers;

import Controller.DataAcessObjects.ClienteDAO;
import Models.Usuarios.Cliente;
import Utils.Exception.CPFException;
import Utils.Exception.LoginException;
import Utils.Exception.TransacaoException;


public class TransChecker {
   
    public static  boolean isSaldoAvaible(String cpf, Double valor) throws TransacaoException, CPFException {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.findByCpf(cpf);
        if (cliente.getSaldo() >= valor) {
            return true;
        }
        return false;
    }
    
    public static boolean isSameAcc(String cpfOrigem, String cpDestino) throws TransacaoException {
        if (cpfOrigem.equals(cpDestino)) {
            return true;
        }
        return false;
    }
    public static boolean tudoPreenchido(String cpfOrigem, String cpDestino, Double valor, String Senha) throws TransacaoException {
        if (cpfOrigem.equals("") || cpDestino.equals("") || valor == 0 || Senha.equals("")) {
            return false;
        }
        return true;
    }
    public static boolean isTransValida(String cpfOrigem, String cpDestino, Double valor, String Senha) throws CPFException, TransacaoException, LoginException {
        //todo
        if(!tudoPreenchido(cpfOrigem, cpDestino, valor, Senha)){
            throw new TransacaoException("Preencha todos os campos");
        }
        if(!LoginChecker.isPasswordValid(cpfOrigem, Senha)){
            throw new LoginException();
        }
        boolean cadastrado1 = CpfChecker.isCPFCadastrado(cpfOrigem);
        boolean cadastrado2 =CpfChecker.isCPFCadastrado(cpDestino);
        if (!cadastrado1 || !cadastrado2) {
            throw new CPFException("CPF não cadastrado");
        }
        if(isSameAcc(cpfOrigem, cpDestino)){
            throw new TransacaoException("Não é possível transferir para a mesma conta");
        }
        if(!isSaldoAvaible(cpfOrigem, valor)){
            throw new TransacaoException("Saldo insuficiente");
        }
        if (valor <= 0) {
            throw new TransacaoException("O valor da transferência deve ser maior que zero.");
        }

        return true;
    }

    
}
