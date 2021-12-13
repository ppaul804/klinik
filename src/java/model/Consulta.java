package model;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Pedro Paul
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

    private int idConsulta;
    private Cliente cliente;
    private Usuario atendente;
    private String status;
    private Date data_consulta;
    private List<ServicoConsulta> carrinho;

}
