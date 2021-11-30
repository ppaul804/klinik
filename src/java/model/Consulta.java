package model;

import java.time.LocalDate;
import java.time.LocalTime;
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

    private int idConsulta;
    private Cliente cliente;
    private Usuario usuario;
    private String status;
    private LocalDate data;
    private LocalTime hora;
    private String valor;

}
