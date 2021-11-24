package model;

import lombok.*;

@Getter
@Setter
public class Menu {
    
    private int idMenu;
    private String nome;
    private String link;
    private String icone;
    private int exibir;
    
    public Menu() {
    }

    public Menu(int idMenu, String nome, String icone, String link, int exibir) {
        this.idMenu = idMenu;
        this.nome = nome;
        this.icone = icone;
        this.link = link;
        this.exibir = exibir;
    }
    
    @Override
    public String toString() {
        return "Menu{" + "nome=" + nome + '}';
    }
}
