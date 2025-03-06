package Controller.DataAcessObjects;

import Models.Arquivo;
import Models.Conta;
import Models.Deposito;
import Models.Saque;
import Models.Transacao;
import Models.Transferencia;
import Models.Usuario;
import Utils.GsonUtil;
import Utils.Persistence.TransacaoPersist;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class TransacaoDAO implements TransacaoPersist {
    private static final String DIRECTORY = "data";
    private static final String PATH = DIRECTORY + File.separator + "usuarios.json";

    private TransacaoPersist  transacaoPersist; // final?

  
    public void realizarSaque(Usuario conta, double valor) {
        Saque saque = new Saque(valor);
        conta.adicionarTransacao(saque);
        transacaoPersist.save(conta.getTransacoes()); 
    }

    public void realizarDeposito(Conta conta, double valor) {
        Deposito deposito = new Deposito(valor);
        conta.adicionarTransacao(deposito);
        transacaoPersist.save(conta.getTransacoes()); 
    }

    public void realizarTransferencia(Conta contaOrigem, String contaDestino, double valor) {
        Transferencia transferencia = new Transferencia(valor, contaDestino);
        contaOrigem.adicionarTransacao(transferencia);
        transacaoPersist.save(contaOrigem.getTransacoes()); 
        System.out.println("Transferência realizada com sucesso!");
    }

    public String consultarExtrato(Conta conta) {
        String extrato = "Extrato da conta " + conta.getId() + ":";
        for (Transacao transacao : conta.getTransacoes()) {
            extrato += "\n" + transacao;
        }
        return extrato;
    }


    @Override
    public void save(List<Usuario> usuarios) {
        String json = GsonUtil.toJson(usuarios);
        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs(); 
        }
        Arquivo.save(PATH, json); 
    }
    @Override
    public List<Usuario> findAll() {
        String json = Arquivo.read(PATH);
        List<Usuario> usuarios = new ArrayList<>();
        if (!json.trim().isEmpty()) {
            Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();
            usuarios = GsonUtil.fromJson(json, tipoLista);
            if (usuarios == null) {
                usuarios = new ArrayList<>();
            }
        }
        return usuarios;
    }
}