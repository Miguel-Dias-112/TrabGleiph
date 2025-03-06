package Models; 

import Utils.Exception.CPFException;
import Utils.Exception.EditarException;
import Utils.Exception.CadastroException;
import Controller.DataAcessObjects.ClienteDAO;
import Utils.CPF;
import Utils.LoginChecker;

import java.util.List;

public class Usuario {
    private String login;
    private String senha;
    private String nome;
    final private String cpf;
    final private String cargo;
    public Usuario(String login, String senha, String nome, String cpf, String cargo) throws CPFException, CadastroException {
        
        if(cpf.length() < 1){
            throw new CadastroException("O campo cpf nao pode ficar vazio.");
        }
        
        if(!CPF.isCPFValido(cpf)){
            throw new CPFException("Nao foi possivel cria o usuario.");
        }
        
        if(!"Caixa".equals(cargo) && !"Gerente".equals(cargo) && !"Cliente".equals(cargo)){
            throw new CadastroException("Cargo invalido");
        }
        
        if(!LoginChecker.checkLoginAvailable(login)){ 
            throw new CadastroException("Este login ja esta em uso.");
        }
        
        
        if(login.length() < 1){
            throw new CadastroException("O campo login nao pode ficar vazio.");
        }
        
        if(senha.length() < 1){
            throw new CadastroException("O campo senha nao pode ficar vazio.");
        }
        
        if(nome.length() < 1){
            throw new CadastroException("O campo nome nao pode ficar vazio.");
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

    public void setNome(String novoNome) throws EditarException {
        if(novoNome.length() < 1){
            throw new EditarException("O campo nome nao pode ficar vazio.");   
        }
        this.nome = novoNome;
    }
    
    public void setLogin(String novoLogin) throws EditarException{
        if(novoLogin.length() < 1){
            throw new EditarException("O campo login nao pode ficar vazio.");   
        }
        this.login = novoLogin;
    }
    
    public void setSenha(String novaSenha) throws EditarException {
        if(novaSenha.length() < 1){
            throw new EditarException("O campo senha nao pode ficar vazio.");   
        }
        
        if(novaSenha.equals(this.senha)){
            throw new Error("Error: A nova senha não pode ser igual a antiga.");
        }else{
            this.senha = novaSenha;
        }
    }
    
    
}