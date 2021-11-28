package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PerfilDAO extends DataBaseDAO {
    
    public PerfilDAO() throws Exception {}
    
    public boolean gravar (Perfil perfil) {
        
        try{
            
            String sql;
            this.conectar();
            
            if(perfil.getIdPerfil() == 0) {
                sql = "INSERT INTO perfil (NOME) VALUES (?)";
            } else {
                sql = "UPDATE perfil SET NOME = ? WHERE idPERFIL = ?";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, perfil.getNome());
            if (perfil.getIdPerfil() > 0) {
                pstm.setInt(2, perfil.getIdPerfil());
            }
            pstm.execute();
            this.desconectar();
            
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        
    }
    
    public boolean deletar(Perfil perfil) throws Exception {
        try {
            String SQL = "DELETE FROM perfil WHERE idPERFIL = ?";
            
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setInt(1, perfil.getIdPerfil());
            pstm.execute();
            
            this.desconectar();
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public Perfil getCarregaPorId(int idPerfil) throws Exception {
        
        Perfil perfil = new Perfil();
        
        String SQL = "SELECT * FROM perfil WHERE idPERFIL = ?";
        
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(SQL);
        pstm.setInt(1, idPerfil);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()) {
            perfil.setIdPerfil(rs.getInt("idPERFIL"));
            perfil.setNome(rs.getString("NOME"));
            perfil.setMenus(menusVinculadosPorPerfil(idPerfil));
            perfil.setNaoMenus(menusNaoVinculadosPorPerfil(idPerfil));
        }
        this.desconectar();
        
        return perfil;
    }
    
    public ArrayList<Perfil> getLista() throws Exception {
        
        ArrayList<Perfil> lista = new ArrayList<Perfil>();
        
        String sql = "SELECT * FROM perfil";
        
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        
        
        while(rs.next()) {
            Perfil perfil = new Perfil();
            perfil.setIdPerfil(rs.getInt("idPERFIL"));
            perfil.setNome(rs.getString("NOME"));
            lista.add(perfil);
            System.out.println(perfil);
        }
        
        this.desconectar();
        
        return lista;
        
    }
    
    public ArrayList<Menu> menusVinculadosPorPerfil(int idPerfil) throws Exception {
        
        ArrayList<Menu> lista = new ArrayList<Menu>();
        String sql = "SELECT menu.* FROM menu_perfil menuPerfil, menu as menu "
                + "WHERE menuPerfil.idMENU = menu.idMENU AND menuPerfil.idPERFIL = ?";
        
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idPerfil);
        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) {            
            Menu menu = new Menu();
            menu.setIdMenu(rs.getInt("menu.idMENU"));
            menu.setNome(rs.getString("menu.NOME"));
            menu.setLink(rs.getString("menu.LINK"));
            menu.setIcone(rs.getString("menu.ICONE"));
            menu.setExibir(rs.getInt("menu.EXIBIR"));
            lista.add(menu);
        }
        
        this.desconectar();
        return lista;
    }
    
    public ArrayList<Menu> menusNaoVinculadosPorPerfil(int idPerfil) throws Exception {
        
        ArrayList<Menu> lista = new ArrayList<Menu>();
        String sql = "SELECT menu.* FROM menu as menu "
                + "WHERE menu.idMENU NOT IN (SELECT menuPerfil.idMENU FROM menu_perfil as menuPerfil WHERE menuPerfil.idPerfil = ?)";
        
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idPerfil);
        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) {            
            Menu menu = new Menu();
            menu.setIdMenu(rs.getInt("menu.idMENU"));
            menu.setNome(rs.getString("menu.NOME"));
            menu.setLink(rs.getString("menu.ICONE"));
            menu.setIcone(rs.getString("menu.ICONE"));
            menu.setExibir(rs.getInt("menu.EXIBIR"));
            lista.add(menu);
        }
        
        this.desconectar();
        return lista;
    }
    
    public boolean vincular(int idPerfil, int idMenu) {
        
        try {
            
            String sql = "INSERT INTO menu_perfil (idPERFIL, idMENU) VALUES (?,?)";
            
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idPerfil);
            pstm.setInt(2, idMenu);
            pstm.execute();
            
            this.desconectar();
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        
    }
    
    public boolean desvincular(int idPerfil, int idMenu) {
        
        try {
            
            String sql = "DELETE FROM menu_perfil WHERE idMENU = ? AND idPERFIL = ?";
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idMenu);
            pstm.setInt(2, idPerfil);
            pstm.execute();
            
            this.desconectar();
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        
    }
}
