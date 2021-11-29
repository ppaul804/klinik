package model;

import lombok.*;

@Getter
@Setter
@ToString
public class ContratoServico {
    
    private long idContratoServico;
    private int quantidade;
    private double valor;
    private Contrato contrato;
    private Servico servico;

    public ContratoServico() {
    }

    public ContratoServico(long idContratoServico, int quantidade, double valor, Contrato contrato, Servico servico) {
        this.idContratoServico = idContratoServico;
        this.quantidade = quantidade;
        this.valor = valor;
        this.contrato = contrato;
        this.servico = servico;
    }
    
    
    
}
