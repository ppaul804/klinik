package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MenuDAO extends DataBaseDAO {

    public MenuDAO() throws Exception {
    }

    public boolean gravar(Menu m) {
        try {

            String sql;
            this.conectar();

            if (m.getIdMenu() == 0) {

                sql = "INSERT INTO menu (NOME, LINK, ICONE, EXIBIR) VALUES (?,?,?,?)";
            } else {
                sql = "UPDATE menu SET NOME=?, LINK=?, ICONE=?, EXIBIR=? WHERE idMENU=?";
            }

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, m.getNome());
            pst.setString(2, m.getLink());
            pst.setString(3, m.getIcone());
            pst.setInt(4, m.getExibir());

            if (m.getIdMenu() > 0) {
                pst.setInt(5, m.getIdMenu());
            }

            pst.execute();
            this.desconectar();
            return true;

        } catch (Exception e) {
            System.out.println("Error en:" + e);
            return false;
        }
    }

    public ArrayList<Menu> getLista() throws Exception {

        ArrayList<Menu> lista = new ArrayList<Menu>();

        String sql = "SELECT * FROM menu";

        this.conectar();
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Menu m = new Menu();
            m.setIdMenu(rs.getInt("idMenu"));
            m.setNome(rs.getString("nome"));
            m.setLink(rs.getString("link"));
            m.setIcone(rs.getString("icone"));
            m.setExibir(rs.getInt("exibir"));
            lista.add(m);
        }

        this.desconectar();
        return lista;
    }

    public Menu getCarregarPorID(int idMenu) throws Exception {

        Menu m = new Menu();
        String sql = "SELECT * FROM menu WHERE idMENU=?";

        this.conectar();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, idMenu);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            m.setIdMenu(idMenu);
            m.setNome(rs.getString("nome"));
            m.setLink(rs.getString("link"));
            m.setIcone(rs.getString("icone"));
            m.setExibir(rs.getInt("exibir"));
        }

        this.desconectar();
        return m;
    }

    public boolean deletarMenu(Menu m) {

        try {

            String sql = "DELETE FROM menu WHERE idMENU=?";

            this.conectar();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, m.getIdMenu());

            pst.execute();

            this.desconectar();
            return true;

        } catch (Exception e) {
            System.out.println("Error en: " + e);
            return false;
        }
    }
}
