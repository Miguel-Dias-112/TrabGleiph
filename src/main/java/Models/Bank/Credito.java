/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Models.Bank;
import Controller.DataAcessObjects.CreditoDAO;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Credito {
    final private DecimalFormat df = new DecimalFormat("000");
    private static int ultimoId = 1;
    private String id;
    private double valor;
    private String status;
    private String clienteId;

    static {
        CreditoDAO creditoDAO = new CreditoDAO();
        List<Credito> creditos = creditoDAO.findAll();

        ultimoId = 1;

        Pattern pattern = Pattern.compile("CRED(\\d+)");

        for (Credito credito : creditos) {
            Matcher matcher = pattern.matcher(credito.getId());
            if (matcher.matches()) {
                int numeroId = Integer.parseInt(matcher.group(1));
                ultimoId = Math.max(ultimoId, numeroId + 1);
            }
        }
    }
    
    public Credito(String clienteId, double valor) {
        this.id = "CRED" + df.format(ultimoId++);
        this.valor = valor;
        this.status = "Pendente";
        this.clienteId = clienteId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }
}