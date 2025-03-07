package Models.Bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransacaoTest {

    private Transacao transacao;

    @BeforeEach
    void setUp() {
        transacao = new Transacao(100.0, "Depósito");
    }

    @Test
    void testConstructorWithValor() {
        Transacao transacao = new Transacao(200.0);
        assertEquals(200.0, transacao.getValor());
    }

    @Test
    void testConstructorWithValorAndDescricao() {
        Transacao transacao = new Transacao(300.0, "Saque");
        assertEquals(300.0, transacao.getValor());
    }

    @Test
    void testGetIdAndSetId() {
        transacao.setId("12345");
        assertEquals("12345", transacao.getId());
    }

    @Test
    void testGetValorAndSetValor() {
        transacao.setValor(400.0);
        assertEquals(400.0, transacao.getValor());
    }

    @Test
    void testToString() {
        String expected = "-> Depósito', Valor: R$ 100,00\n";
        assertEquals(expected, transacao.toString());
    }

    @Test
    void testToStringWithDifferentLocale() {
        Transacao transacao = new Transacao(500.0, "Pagamento");
        String expected = "-> Pagamento', Valor: R$ 500,00\n";
        assertEquals(expected, transacao.toString());
    }
}