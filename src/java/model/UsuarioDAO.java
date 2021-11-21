package model;

import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO extends DataBaseDAO {

    public UsuarioDAO() throws Exception {}
    
    public boolean Gravar(Usuario u) {

        try {
            
            String sql;
            this.conectar();
            
            if (u.getIdUsuario() == 0) {
                sql = "INSERT INTO usuario (NOME, LOGIN, SENHA, STATUS, CPF, RG, EMAIL, TELEFONE, ENDERECO, COMPLEMENTO, CIDADE, SEXO, DATA_DE_NASCIMENTO, idPERFIL) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            } else {
                sql = "UPDATE usuario SET NOME = ?, LOGIN = ?, SENHA = ?, STATUS = ?, CPF = ?, RG = ?, EMAIL = ?, TELEFONE = ?, ENDERECO = ?, COMPLEMENTO = ?, CIDADE = ?, SEXO = ?, DATA_DE_NASCIMENTO = ?, idPERFIL = ? WHERE idUSUARIO = ?";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, u.getNome());
            pstm.setString(2, u.getLogin());
            pstm.setString(3, u.getSenha());
            pstm.setInt(4, u.getStatus());
            pstm.setString(5, u.getCpf());
            pstm.setString(6, u.getRg());
            pstm.setString(7, u.getEmail());
            pstm.setString(8, u.getTelefone());
            pstm.setString(9, u.getEndereco());
            pstm.setString(10, u.getComplemento());
            pstm.setString(11, u.getCidade());
            pstm.setString(12, Character.toString(u.getSexo()));
            Date dataPadraoSql = new Date(u.getData_de_nascimento().getTime());
            pstm.setDate(13, dataPadraoSql);
            pstm.setInt(14, u.getIdPerfil().getIdPerfil());
            if(u.getIdUsuario() > 0) {
                pstm.setInt(15, u.getIdUsuario());
            }
            
            pstm.execute();
            this.desconectar();
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean deletar(Usuario u) {
        
        try {
            
            String sql = "UPDATE usuario SET STATUS = 0 WHERE idUSUARIO = ?";
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, u.getIdUsuario());
            pstm.execute();
            
            this.desconectar();
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        
    }
    
    public Usuario getCarregaPorId(int idUsuario) throws Exception {
        
        Usuario u = new Usuario();
        
        String sql = "SELECT u.*, p.nome FROM usuario u "
                + "INNER JOIN perfil p ON p.idPERFIL = u.idPERFIL "
                + "WHERE u.idUSUARIO = ? ";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idUsuario);
        ResultSet rs = pstm.executeQuery();
        
        if (rs.next()) {
            u.setIdUsuario(rs.getInt("u.idUSUARIO"));
            /*
            u.setIdPerfil(rs.getInt("idPERFIL"));
            */
            
            Perfil p = new Perfil();
            p.setIdPerfil(rs.getInt("u.idPERFIL"));
            p.setNome(rs.getString("p.nome"));
            u.setIdPerfil(p);
            
            u.setNome(rs.getString("u.NOME"));
            u.setLogin(rs.getString("u.LOGIN"));
            u.setSenha(rs.getString("u.SENHA"));
            u.setStatus(rs.getInt("u.STATUS"));
            u.setCpf(rs.getString("u.CPF"));
            u.setRg(rs.getString("u.RG"));
            u.setEmail(rs.getString("u.EMAIL"));
            u.setTelefone(rs.getString("u.TELEFONE"));
            u.setEndereco(rs.getString("u.ENDERECO"));
            u.setComplemento(rs.getString("u.COMPLEMENTO"));
            u.setCidade(rs.getString("u.CIDADE"));
            u.setSexo(rs.getString("u.SEXO").charAt(0));
            u.setData_de_nascimento(rs.getDate("u.DATA_DE_NASCIMENTO"));
        }
        
        this.desconectar();
        return u;
        
    }

    public ArrayList<Usuario> getLista() throws Exception{
        
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        String sql = "SELECT u.*, p.nome FROM usuario u "
                + "INNER JOIN perfil p ON p.idPERFIL = u.idPERFIL";
        //String sql = "SELECT * FROM usuario";
        
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) {            
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("u.idUSUARIO"));
            /*
            u.setIdPerfil(rs.getInt("idPERFIL"));
            */
            
            Perfil p = new Perfil();
            p.setIdPerfil(rs.getInt("u.idPERFIL"));
            p.setNome(rs.getString("p.nome"));
            u.setIdPerfil(p);
            
            u.setNome(rs.getString("u.NOME"));
            u.setLogin(rs.getString("u.LOGIN"));
            u.setSenha(rs.getString("u.SENHA"));
            u.setStatus(rs.getInt("u.STATUS"));
            u.setCpf(rs.getString("u.CPF"));
            u.setRg(rs.getString("u.RG"));
            u.setEmail(rs.getString("u.EMAIL"));
            u.setTelefone(rs.getString("u.TELEFONE"));
            u.setEndereco(rs.getString("u.ENDERECO"));
            u.setComplemento(rs.getString("u.COMPLEMENTO"));
            u.setCidade(rs.getString("u.CIDADE"));
            u.setSexo(rs.getString("u.SEXO").charAt(0));
            u.setData_de_nascimento(rs.getDate("u.DATA_DE_NASCIMENTO"));
            lista.add(u);
        }
        
        this.desconectar();
        return lista;
        
    }
}

