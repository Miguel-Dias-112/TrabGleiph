package Utils.Checkers;

import Controller.DataAcessObjects.CaixaDAO;
import Controller.DataAcessObjects.ClienteDAO;
import Models.Usuarios.Cliente;
import Utils.Exception.LoginException;

public class LoginChecker {
     public static  boolean isPasswordValid(String cpf, String senha) throws LoginException {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.findByCpf(cpf);
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
    public static boolean checkLoginCliente(String login, String senha) {
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.findByLogin(login).getSenha().equals(senha);
        if (login.equals("admin") && senha.equals("admin")) {
            return true;
        }
        return false;
    }
    public static boolean checkLoginCaixa(String login, String senha) {
        CaixaDAO caixaDAO = new CaixaDAO();
        caixaDAO.findByLogin(login).getSenha().equals(senha);
        if (login.equals("admin") && senha.equals("admin")) {
            return true;
        }
        return false;
    }
}
