package Models;

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