package Models;

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
        boolean fezLogin = false;
        ClienteDAO usuarioDAO = new ClienteDAO();
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
    public Caixa validarLoginCaixa(String login, String senha) throws LoginException{
        boolean fezLogin = false;
        CaixaDAO caixaDAO = new CaixaDAO();
        List<Caixa> usuarios = caixaDAO.findAll();
        for(Caixa caixa : usuarios){
            if(caixa.getLogin().equals(login)){
                if(caixa.getSenha().equals(senha)){
                    setLogin(caixa);
                    fezLogin = true;
                    return caixa;
                }
            }
        }
        if(!fezLogin){
           throw new LoginException();
        }
        return null;
    }
  
}
