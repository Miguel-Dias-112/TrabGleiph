package Controller.DataAcessObjects;

import Models.Usuarios.Cliente;
import Utils.Exception.CPFException;
import Utils.Exception.CadastroException;
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