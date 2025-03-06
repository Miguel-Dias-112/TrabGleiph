package Utils;

import Controller.DataAcessObjects.ClienteDAO;

public class LoginChecker {
    public static boolean checkLogin(String login, String senha) {
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.findByCpf(senha)
        if (login.equals("admin") && senha.equals("admin")) {
            return true;
        }
        return false;
    }
}
