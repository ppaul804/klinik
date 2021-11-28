package model;

import java.util.Date;
import lombok.*;

@Setter
@Getter
@ToString
public class Usuario {
    
    private int idUsuario;
    private Perfil idPerfil;
    private String nome;
    private String login;
    private String senha;
    private int status;
    private String cpf;
    private String rg;
    private String email;
    private String telefone;
    private String endereco;
    private String complemento;
    private String cidade;
    private char sexo;
    private Date data_de_nascimento;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nome, String telefone) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.telefone = telefone;
    }
    
}
