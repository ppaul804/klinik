package model;

import lombok.*;

@Data
public class ServicoConsulta {

    private long idServicoConsulta;
    private int quantidade;
    private double valor;
    private Consulta consulta;
    private Servico servico;

}
