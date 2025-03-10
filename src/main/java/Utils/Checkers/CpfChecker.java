/*
    Eduarda Pereira Mourão Nunes - 202376015
    Gabriel Giácomo Paes - 202176006
    Miguel Dias - 202376013

*/

package Utils.Checkers;

import Controller.DataAcessObjects.ClienteDAO;
import Models.Usuarios.Cliente;
import Utils.Exception.CPFException;

import java.util.List;

public class CpfChecker {
    public static boolean isCPFValido(String cpf) throws CPFException {
        cpf = cpf.replaceAll("[^0-9]", "");

        if(cpf.length() != 11) {
            throw new CPFException("deve conter 11 digitos.");
        }

        if(!validarDigitosCPF(cpf)) {
            throw new CPFException("digitos verificadores incorretos.");
        }
        
        if(isCPFCadastrado(cpf)) {
            throw new CPFException("cpf já cadastrado.");
        }

        return true;
    }

    private static boolean validarDigitosCPF(String cpf) throws CPFException{
        int soma = 0, peso = 10;

        // Cálculo do primeiro dígito verificador
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * peso--;
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) primeiroDigito = 0;

        // Verificar primeiro dígito
        if (primeiroDigito != (cpf.charAt(9) - '0')) {
            return false;
        }

        // Cálculo do segundo dígito verificador
        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * peso--;
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) segundoDigito = 0;

        // Verificar segundo dígito
        return segundoDigito == (cpf.charAt(10) - '0');
    }

    public static String formatarCPF(String cpf) throws CPFException {
        
        cpf = cpf.replaceAll("[^0-9]", "");
        
        if(cpf.length() != 11) {
            throw new CPFException("deve conter 11 digitos.");
        }

        cpf = cpf.replaceAll("[^0-9]", "");
        
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + 
               cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }
    
    public static boolean isCPFCadastrado(String cpf) throws CPFException{
        cpf = formatarCPF(cpf);
        
       ClienteDAO clientesDAO = new ClienteDAO();
       List<Cliente> clientess = clientesDAO.findAll();
        
        for (Cliente clientes : clientess) {
           if (clientes.getCpf().equals(cpf)) {
               return true;
           }
        }
        return false;
        //throw new CPFException("CPF não cadastrado.");
       
    }
}
