package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MenuDAO extends DataBaseDAO {

    public MenuDAO() throws Exception {}
    
    public boolean gravar(Menu menu) {
        try {
            
            String sql;
            this.conectar();
            
            if (menu.getIdMenu() == 0) {
                sql = "INSERT INTO menu (NOME, LINK, ICONE, EXIBIR) VALUES (?,?,?,?)";
            } else {
                sql = "UPDATE menu SET NOME = ?, LINK = ?, ICONE = ?, EXIBIR = ? WHERE idMENU = ?";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, menu.getNome());
            pstm.setString(2, menu.getLink());
            pstm.setString(3, menu.getIcone());
            pstm.setInt(4, menu.getExibir());
            if(menu.getIdMenu() > 0) {
                pstm.setInt(5, menu.getIdMenu());
            }
            pstm.execute();
            this.desconectar();
            
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return false;
    }
    
    public boolean deletar(Menu menu) {
        
        try {
            
            String sql = "DELETE FROM menu WHERE idMENU = ?";
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, menu.getIdMenu());
            pstm.execute();
            
            this.desconectar();
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        
    }
    
    public Menu getCarregaPorId(int idMenu) throws Exception{
        
        Menu menu = new Menu();
        
        String sql = "SELECT * FROM menu WHERE idMENU = ?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idMenu);
        ResultSet rs = pstm.executeQuery();
        
        if (rs.next()) {
            menu.setIdMenu(idMenu);
            menu.setNome(rs.getString("NOME"));
            menu.setLink(rs.getString("LINK"));
            menu.setIcone(rs.getString("ICONE"));
            menu.setExibir(rs.getInt("EXIBIR"));
        }
        
        this.desconectar();
        return menu;
        
    }
    
    public ArrayList<Menu> getLista() throws Exception {
        
        ArrayList<Menu> lista = new ArrayList<Menu>();
        String sql = "SELECT * FROM menu";
        this.conectar();
        PreparedStatement pstm = conn.prepareCall(sql);
        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) {
            Menu m = new Menu();
            m.setIdMenu(rs.getInt("idMENU"));
            m.setNome(rs.getString("NOME"));
            m.setLink(rs.getString("LINK"));
            m.setIcone(rs.getString("ICONE"));
            m.setExibir(rs.getInt("EXIBIR"));
            lista.add(m);
        }
        this.desconectar();
        
        return lista;
    }
    
}
