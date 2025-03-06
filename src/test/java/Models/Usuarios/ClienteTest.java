package Models.Usuarios;

import Models.Bank.Conta;
import Utils.Exception.CPFException;
import Utils.Exception.CadastroException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() throws CPFException, CadastroException {
        cliente = new Cliente("login", "senha", "nome do cliente", "675.739.730-71");
    }

    @Test
    void testConstrutor() {
        assertNotNull(cliente);
        assertEquals("login", cliente.getLogin());
        assertEquals("senha", cliente.getSenha());
        assertEquals("nome do cliente", cliente.getNome());
        assertEquals("675.739.730-71", cliente.getCpf());
        assertEquals("Cliente", cliente.getCargo());
        assertNotNull(cliente.getConta());
    }

    @Test
    void testSetConta() {
        Conta novaConta = new Conta();
        novaConta.setSaldo(1000);
        cliente.setConta(novaConta);

        assertEquals(novaConta, cliente.getConta());
        assertEquals(1000, cliente.getSaldo());
    }

    @Test
    void testGetSaldo() {
        cliente.getConta().setSaldo(500);
        assertEquals(500, cliente.getSaldo());
    }

    @Test
    void testConstrutorComCPFInvalido() {
        assertThrows(CPFException.class, () -> {
            new Cliente("login", "senha", "nome do cliente", "675.739.730-74");
        });
    }

    @Test
    void testConstrutorComCadastroInvalido() {
        assertThrows(CadastroException.class, () -> {
            new Cliente("", "senha", "nome do cliente", "675.739.730-71");
        });
    }
}