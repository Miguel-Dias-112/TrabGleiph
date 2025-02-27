package Models; 

import Utils.Exception.CPFException;
import Utils.Exception.CadastroException;
import Controller.DataAcessObjects.UsuarioDAO;
import Utils.CPF;
import java.util.List;

public class Usuario {
    private String login;
    private String senha;
    private String nome;
    final private String cpf;
    final private String cargo;

    public Usuario(String login, String senha, String nome, String cpf, String cargo) throws CPFException, CadastroException {
        if(!CPF.isCPFValido(cpf)){
            throw new CPFException("Nao foi possivel cria o usuario.");
        }
        
        if(!"Caixa".equals(cargo) && !"Gerente".equals(cargo) && !"Cliente".equals(cargo)){
            throw new CadastroException("Cargo invalido");
        }
        
        if(!checkLoginAvailable(login)){ 
            throw new CadastroException("Este login ja esta em uso.");
        }
        
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.cpf = CPF.formatarCPF(cpf); //verifica se é válido antes de formatar
        this.cargo = cargo;
    }
    
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
    
    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
    
    public String getCargo(){
        return cargo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setSenha(String novaSenha) {
        if(novaSenha.equals(this.senha)){
            throw new Error("Error: A nova senha não pode ser igual a antiga.");
        }else{
            this.senha = novaSenha;
        }
    }
    
    private boolean checkLoginAvailable(String login){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.findAll();
        
        for(Usuario usuario: usuarios){
            if(usuario.login.equals(login)){
                return false;
            }
        }
        return true;
    }
}