
package model;


import java.util.Date;
import lombok.*;

@Getter
@Setter
@ToString
public class Cliente {
    private int idCliente;
    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private String endereco;
    private String complemento;
    private String cidade;
    private char sexo;
    private String telefone;
    private int status;
    private Date data_de_nascimento;
    private String observacao;

    public Cliente(){
        
    }
    public Cliente(int idCliente, String nome,String cpf,String rg,String email,String endereco,String complemento,String cidade,char sexo, String telefone,Date data_de_nascimento,String observacao) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf=cpf;
        this.rg=rg;
        this.email=email;
        this.endereco=endereco;
        this.complemento=complemento;
        this.cidade=cidade;
        this.sexo=sexo;
        this.telefone = telefone;
        this.data_de_nascimento= data_de_nascimento;
        this.observacao=observacao;
    }
    
}
