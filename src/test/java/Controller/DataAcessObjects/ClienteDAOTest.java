package Controller.DataAcessObjects;

import Models.Usuarios.Cliente;
import Utils.Exception.CPFException;
import Utils.Exception.CadastroException;
import Utils.Exception.EditarException;
import Utils.Exception.TransacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class ClienteDAOTest {

    private ClienteDAO clienteDAO;

    @BeforeEach
    void setup() {
        clienteDAO = new ClienteDAO();
    }

    @Test
    void testAdicionarNovoCliente() throws CPFException, CadastroException {
        Cliente novoCliente = new Cliente("login13", "senha1", "Cliente Teste", "654.665.550-02");
        clienteDAO.adicionarNovoCliente(novoCliente);

        Cliente encontrado = clienteDAO.findByCpf("654.665.550-02");
        assertNotNull(encontrado);
        assertEquals("login13", encontrado.getLogin());

        clienteDAO.deletarCliente("654.665.550-02");
    }

    @Test
    void testDeletarCliente() throws CPFException, CadastroException {
        Cliente cliente = new Cliente("login2", "senha2", "Para Deletar", "285.414.830-45");
        clienteDAO.adicionarNovoCliente(cliente);

        clienteDAO.deletarCliente("285.414.830-45");
        assertNull(clienteDAO.findByCpf("285.414.830-45"));
    }

    @Test
    void testEditarCliente() throws CPFException, CadastroException, EditarException {
        Cliente cliente = new Cliente("login3", "senha3", "Para Editar", "479.207.100-30");
        clienteDAO.adicionarNovoCliente(cliente);

        clienteDAO.editarCliente("479.207.100-30", "Novo Nome", "novoLogin", "novaSenha");
        Cliente editado = clienteDAO.findByCpf("479.207.100-30");

        assertNotNull(editado);
        assertEquals("Novo Nome", editado.getNome());
        assertEquals("novoLogin", editado.getLogin());
        assertEquals("novaSenha", editado.getSenha());

        clienteDAO.deletarCliente("479.207.100-30");
    }

    @Test
    void testRealizarSaque() throws CPFException, CadastroException {
        Cliente cliente = new Cliente("login11", "senha4", "Cliente Saque", "690.799.490-94");
        cliente.getConta().setSaldo(1000.0);
        clienteDAO.adicionarNovoCliente(cliente);

        boolean sucesso = clienteDAO.realizarSaque("690.799.490-94", 200, "senha4");
        assertTrue(sucesso);
        assertEquals(800.0, clienteDAO.findByCpf("690.799.490-94").getConta().getSaldo());

        clienteDAO.deletarCliente("690.799.490-94");
    }

    @Test
    void testRealizarDeposito() throws CPFException, CadastroException {
        Cliente cliente = new Cliente("login12", "senha5", "Cliente Deposito", "734.944.880-53");
        cliente.getConta().setSaldo(500.0);
        clienteDAO.adicionarNovoCliente(cliente);

        boolean sucesso = clienteDAO.realizarDeposito("734.944.880-53", 300.0, "senha5");
        assertTrue(sucesso);
        assertEquals(800.0, clienteDAO.findByCpf("734.944.880-53").getConta().getSaldo());

        clienteDAO.deletarCliente("734.944.880-53");
    }

    @Test
    void testFindAll() {
        List<Cliente> lista = clienteDAO.findAll();
        assertNotNull(lista);
    }

    @Test
    void testFindByCpf() throws CPFException, CadastroException {
        Cliente cliente = new Cliente("login8", "senha8", "Cliente CPF", "230.541.500-11");
        clienteDAO.adicionarNovoCliente(cliente);

        Cliente encontrado = clienteDAO.findByCpf("230.541.500-11");
        assertNotNull(encontrado);

        clienteDAO.deletarCliente("230.541.500-11");
    }

    @Test
    void testFindByLogin() throws CPFException, CadastroException {
        Cliente cliente = new Cliente("uniqueLogin", "senha9", "Cliente Login", "005.462.670-68");
        clienteDAO.adicionarNovoCliente(cliente);

        Cliente encontrado = clienteDAO.findByLogin("uniqueLogin");
        assertNotNull(encontrado);

        clienteDAO.deletarCliente("005.462.670-68");
    }
}