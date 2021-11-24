package model;

import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO extends DataBaseDAO {

    public UsuarioDAO() throws Exception {}
    
    public boolean gravar(Usuario usuario) {

        try {
            
            String sql;
            this.conectar();
            
            if (usuario.getIdUsuario() == 0) {
                sql = "INSERT INTO usuario (NOME, LOGIN, SENHA, STATUS, CPF, RG, EMAIL, TELEFONE, ENDERECO, COMPLEMENTO, CIDADE, SEXO, DATA_DE_NASCIMENTO, idPERFIL) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            } else {
                sql = "UPDATE usuario SET NOME = ?, LOGIN = ?, SENHA = ?, STATUS = ?, CPF = ?, RG = ?, EMAIL = ?, TELEFONE = ?, ENDERECO = ?, COMPLEMENTO = ?, CIDADE = ?, SEXO = ?, DATA_DE_NASCIMENTO = ?, idPERFIL = ? WHERE idUSUARIO = ?";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getLogin());
            pstm.setString(3, usuario.getSenha());
            pstm.setInt(4, usuario.getStatus());
            pstm.setString(5, usuario.getCpf());
            pstm.setString(6, usuario.getRg());
            pstm.setString(7, usuario.getEmail());
            pstm.setString(8, usuario.getTelefone());
            pstm.setString(9, usuario.getEndereco());
            pstm.setString(10, usuario.getComplemento());
            pstm.setString(11, usuario.getCidade());
            pstm.setString(12, Character.toString(usuario.getSexo()));
            pstm.setDate(13, new Date(usuario.getData_de_nascimento().getTime()));
            pstm.setInt(14, usuario.getIdPerfil().getIdPerfil());
            if(usuario.getIdUsuario() > 0) {
                pstm.setInt(15, usuario.getIdUsuario());
            }
            
            pstm.execute();
            this.desconectar();
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean deletar(Usuario usuario) {
        
        try {
            
            String sql = "UPDATE usuario SET STATUS = 0 WHERE idUSUARIO = ?";
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, usuario.getIdUsuario());
            pstm.execute();
            
            this.desconectar();
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        
    }
    
    public Usuario getCarregaPorId(int idUsuario) throws Exception {
        
        Usuario usuario = new Usuario();
        
        String sql = "SELECT usuario.*, perfil.nome FROM usuario usuario "
                + "INNER JOIN perfil perfil ON perfil.idPERFIL = usuario.idPERFIL "
                + "WHERE usuario.idUSUARIO = ? ";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idUsuario);
        ResultSet rs = pstm.executeQuery();
        
        if (rs.next()) {
            usuario.setIdUsuario(rs.getInt("usuario.idUSUARIO"));
            
            Perfil perfil = new Perfil();
            perfil.setIdPerfil(rs.getInt("usuario.idPERFIL"));
            perfil.setNome(rs.getString("perfil.nome"));
            usuario.setIdPerfil(perfil);
            
            usuario.setNome(rs.getString("usuario.NOME"));
            usuario.setLogin(rs.getString("usuario.LOGIN"));
            usuario.setSenha(rs.getString("usuario.SENHA"));
            usuario.setStatus(rs.getInt("usuario.STATUS"));
            usuario.setCpf(rs.getString("usuario.CPF"));
            usuario.setRg(rs.getString("usuario.RG"));
            usuario.setEmail(rs.getString("usuario.EMAIL"));
            usuario.setTelefone(rs.getString("usuario.TELEFONE"));
            usuario.setEndereco(rs.getString("usuario.ENDERECO"));
            usuario.setComplemento(rs.getString("usuario.COMPLEMENTO"));
            usuario.setCidade(rs.getString("usuario.CIDADE"));
            usuario.setSexo(rs.getString("usuario.SEXO").charAt(0));
            usuario.setData_de_nascimento(rs.getDate("usuario.DATA_DE_NASCIMENTO"));
        }
        
        this.desconectar();
        return usuario;
        
    }

    public ArrayList<Usuario> getLista() throws Exception{
        
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        String sql = "SELECT usuario.*, perfil.nome FROM usuario usuario "
                + "INNER JOIN perfil perfil ON perfil.idPERFIL = usuario.idPERFIL";
        
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) {            
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("usuario.idUSUARIO"));
            
            Perfil perfil = new Perfil();
            perfil.setIdPerfil(rs.getInt("usuario.idPERFIL"));
            perfil.setNome(rs.getString("perfil.nome"));
            usuario.setIdPerfil(perfil);
            
            usuario.setNome(rs.getString("usuario.NOME"));
            usuario.setLogin(rs.getString("usuario.LOGIN"));
            usuario.setSenha(rs.getString("usuario.SENHA"));
            usuario.setStatus(rs.getInt("usuario.STATUS"));
            usuario.setCpf(rs.getString("usuario.CPF"));
            usuario.setRg(rs.getString("usuario.RG"));
            usuario.setEmail(rs.getString("usuario.EMAIL"));
            usuario.setTelefone(rs.getString("usuario.TELEFONE"));
            usuario.setEndereco(rs.getString("usuario.ENDERECO"));
            usuario.setComplemento(rs.getString("usuario.COMPLEMENTO"));
            usuario.setCidade(rs.getString("usuario.CIDADE"));
            usuario.setSexo(rs.getString("usuario.SEXO").charAt(0));
            usuario.setData_de_nascimento(rs.getDate("usuario.DATA_DE_NASCIMENTO"));
            lista.add(usuario);
        }
        
        this.desconectar();
        return lista;
        
    }
    
    public Usuario getRecuperarUsuario(String login) {
        
        Usuario usuario = new Usuario();
        String sql = "SELECT usuario.* FROM usuario usuario "
                + "WHERE usuario.login = ?";
        
        try {
            
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, login);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt("usuario.idUSUARIO"));
            
                PerfilDAO pDAO = new PerfilDAO();
                usuario.setIdPerfil(pDAO.getCarregaPorId(rs.getInt("usuario.idPERFIL")));

                usuario.setNome(rs.getString("usuario.NOME"));
                usuario.setLogin(rs.getString("usuario.LOGIN"));
                usuario.setSenha(rs.getString("usuario.SENHA"));
                usuario.setStatus(rs.getInt("usuario.STATUS"));
                usuario.setCpf(rs.getString("usuario.CPF"));
                usuario.setRg(rs.getString("usuario.RG"));
                usuario.setEmail(rs.getString("usuario.EMAIL"));
                usuario.setTelefone(rs.getString("usuario.TELEFONE"));
                usuario.setEndereco(rs.getString("usuario.ENDERECO"));
                usuario.setComplemento(rs.getString("usuario.COMPLEMENTO"));
                usuario.setCidade(rs.getString("usuario.CIDADE"));
                usuario.setSexo(rs.getString("usuario.SEXO").charAt(0));
                usuario.setData_de_nascimento(rs.getDate("usuario.DATA_DE_NASCIMENTO"));
            }
            
            this.desconectar();
            return usuario;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
    }
}

