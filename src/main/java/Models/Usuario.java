package Models; 

import Exception.CPFException;
import Utils.CPF;


public class Usuario {
    private String login;
    private String senha;
    private String nome;
    final private String cpf;
    final private String cargo;

    public Usuario(String login, String senha, String nome, String cpf, String cargo) throws CPFException {
        if(!CPF.isCPFValido(cpf)){
            throw new CPFException("Nao foi possivel cria o usuario.");
        }
        
        if(cargo != "Caixa" && cargo != "Gerente" && cargo != "Cliente"){
            throw new Error("Erro Criacao de Usuario: Cargo invalido");
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

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setSenha(String novaSenha) {
        if(novaSenha == this.senha){
            throw new Error("Error: A nova senha não pode ser igual a antiga.");
        }else{
            this.senha = novaSenha;
        }
    }
}