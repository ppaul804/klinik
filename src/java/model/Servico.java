/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import lombok.*;

/**
 *
 * @author COUGAR
 */
@Getter
@Setter
@ToString
public class Servico {
    private int idServico;
    private String nome;
    private int quantidade;
    private double valor;
    private int status;

    public Servico(int idServico, String nome, int quantidade, double valor, int status) {
        this.idServico = idServico;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.status = status;
    }

    public Servico() {
    }
    
}

