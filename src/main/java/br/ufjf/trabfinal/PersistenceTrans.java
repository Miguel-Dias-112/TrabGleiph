/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.trabfinal;

import java.util.List;

/**
 *
 * @author ice
 */
public interface PersistenceTrans{

    String DIRECTORY = "data";
    public void save(List<Transacao> itens);
    public List<Transacao> findAll();

}