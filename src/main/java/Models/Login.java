package Models;

import Utils.Exception.LoginException;
import Controller.DataAcessObjects.ClienteDao;
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
    public Cliente validarlogin(String login, String senha) throws LoginException{
        boolean fezLogin = false;
        ClienteDao usuarioDAO = new ClienteDao();
        List<Cliente> usuarios = usuarioDAO.findAll();
        for(Cliente usuario : usuarios){
            if(usuario.getLogin().equals(login)){
                if(usuario.getSenha().equals(senha)){
                    setLogin(usuario);
                    fezLogin = true;
                    System.out.println("logou");
                    return usuario;
                }
            }
        }
        if(!fezLogin){
           throw new LoginException();
        }
        return null;
    }
}
