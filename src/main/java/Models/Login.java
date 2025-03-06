package Models;

import Utils.LoginChecker;
import Utils.Exception.LoginException;
import Controller.DataAcessObjects.CaixaDAO;
import Controller.DataAcessObjects.ClienteDAO;
import static java.time.Clock.system;
import java.util.List;
import static javax.swing.text.html.HTML.Tag.U;

public class Login {
    static public Usuario user;
    
    public Login(){
       
    }
    public void setLogin(Usuario user){
        Login.user = user;
    }
    public Cliente validarLoginCliente(String login, String senha) throws LoginException{
        LoginChecker.checkLoginCliente(login, senha);
        return null;
    }
    public Caixa validarLoginCaixa(String login, String senha) throws LoginException{
        LoginChecker.checkLoginCliente(login, senha);
        return null;
    }
  
}
