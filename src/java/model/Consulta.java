package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Pedro Paul
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Consulta {

    private String idConsulta;
    private String idCliente;
    private String idUsuario;
    private String status;
    private String data;
    private String hora;
    private String valor;

}
