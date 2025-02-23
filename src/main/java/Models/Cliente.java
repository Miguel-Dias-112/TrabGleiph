package Models;

public class Cliente extends Usuario {
    private Conta conta; 

    public Cliente(String login, String senha, String nome, String cpf) {
        super(login, senha, nome, cpf);
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }
}