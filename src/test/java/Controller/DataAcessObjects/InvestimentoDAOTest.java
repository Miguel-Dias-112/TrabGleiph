package Controller.DataAcessObjects;

import Models.Bank.Investimento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class InvestimentoDAOTest {

    private InvestimentoDAO investimentoDAO;

    @BeforeEach
    void setUp() {
        investimentoDAO = new InvestimentoDAO();
    }

    @Test
    void testAdicionarInvestimento() {
        Investimento invest = new Investimento("Investimento Teste", 2027, 12.0);
        investimentoDAO.adicionarInvestimento(invest);

        List<Investimento> lista = investimentoDAO.findAll();
        assertTrue(lista.stream().anyMatch(i -> i.getNome().equals("Investimento Teste")));

        investimentoDAO.removerInvestimento("Investimento Teste");
        assertFalse(investimentoDAO.findAll().stream()
                .anyMatch(i -> i.getNome().equals("Investimento Teste")));
    }
    
    @Test
    void testRemoverInvestimento() {
        Investimento invest = new Investimento("Investimento Teste 2", 2027, 12.0);
        investimentoDAO.adicionarInvestimento(invest);

        boolean existeAntes = investimentoDAO.findAll().stream()
                .anyMatch(i -> i.getNome().equals("Investimento Teste 2"));
        assertTrue(existeAntes);

        investimentoDAO.removerInvestimento("Investimento Teste 2");
        boolean existeDepois = investimentoDAO.findAll().stream()
                .anyMatch(i -> i.getNome().equals("Investimento Teste 2"));
        assertFalse(existeDepois);
    }

    @Test
    void testFindAll() {
        Investimento invest = new Investimento("Investimento Teste 3", 2027, 12.0);
        investimentoDAO.adicionarInvestimento(invest);

        List<Investimento> lista = investimentoDAO.findAll();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());

        investimentoDAO.removerInvestimento("Investimento Teste 3");
    }
}