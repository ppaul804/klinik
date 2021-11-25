package model;

import lombok.*;

@Getter
@Setter
public class Perfil {
    
    private int idPerfil;
    private String nome;

    public Perfil() {
    }

    public Perfil(int idPerfil, String nome) {
        this.idPerfil = idPerfil;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Perfil{" + "nome=" + nome + '}';
    }
    
}
