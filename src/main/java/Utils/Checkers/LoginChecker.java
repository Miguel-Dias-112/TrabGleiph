/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Utils.Checkers;

import Controller.DataAcessObjects.CaixaDAO;
import Controller.DataAcessObjects.ClienteDAO;
import Controller.DataAcessObjects.GerenteDAO;
import Models.Usuarios.Caixa;
import Models.Usuarios.Cliente;
import Models.Usuarios.Gerente;
import Utils.Exception.CPFException;
import Utils.Exception.LoginException;

public class LoginChecker {
     public static  boolean isPasswordValid(String cpf, String senha) throws LoginException, CPFException {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.findByCpf(cpf);
        
        if(cliente == null){
            throw new CPFException("usuario nao encontrado.");
        }
        
        if (cliente.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }
    public static boolean checkLoginAvailable(String login){
        CaixaDAO caixDAO = new CaixaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        boolean founded = caixDAO.findByLogin(login)!=null ||
                            clienteDAO.findByLogin(login)!=null;
        if(founded){
            return false;
        }
       
        return true;
    }
    public static boolean checkLoginCliente(String login, String senha) throws LoginException {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.findByLogin(login);
        if (cliente == null) {
            throw new LoginException();   
        }
        String senhaCliente = cliente.getSenha();
        boolean valido = senhaCliente.equals(senha);
        return valido;
    }
    public static boolean checkLoginCaixa(String login, String senha) throws LoginException {
        CaixaDAO caixaDAO = new CaixaDAO();
        Caixa cliente = caixaDAO.findByLogin(login);
        if (cliente == null) {
            throw new LoginException();    
        }
        String senhaCaixa= cliente.getSenha();
        boolean valido = senhaCaixa.equals(senha);
           
        return valido;
    }
    
    public static boolean checkLoginGerente(String login, String senha) throws LoginException {
        GerenteDAO gerenteDAO = new GerenteDAO();
        Gerente gerente = gerenteDAO.findByLogin(login);
        if (gerente == null) {
            throw new LoginException();
        }
        String senhaGerente = gerente.getSenha();
        boolean valido = senhaGerente.equals(senha);
        
        return valido;

    }
}
