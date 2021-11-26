package model;

import java.util.ArrayList;
import lombok.*;

@Getter
@Setter
public class Perfil {
    
    private int idPerfil;
    private String nome;
    
    private ArrayList<Menu> menus;
    private ArrayList<Menu> naoMenus;

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
