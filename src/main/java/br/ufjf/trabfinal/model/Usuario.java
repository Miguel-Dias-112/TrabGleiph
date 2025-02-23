package br.ufjf.trabfinal.model;

// login vai ser cpf e senha 

public class Usuario {
    private String login;
    private String senha;
    private String nome;
    final private String cpf;

    public Usuario(String login, String senha, String nome, String cpf) {
        this.login = login;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String novaSenha) {
        if(novaSenha == this.senha){
            throw new Error("Error: A nova senha não pode ser igual a antiga.");
        }else{
            this.senha = novaSenha;
        }
    }
}