/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author COUGAR
 */
@Getter
@Setter
public class Servico {
    private int idServico;
    private String nome;
    private int quantidade;
    private double valor;

    public Servico(int idServico, String nome, int quantidade, double valor) {
        this.idServico = idServico;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Servico() {
    }

    @Override
    public String toString() {
        return "Servico{" + "idServico=" + idServico + ", nome=" + nome + ", quantidade=" + quantidade + ", valor=" + valor + '}';
    }
    
}

