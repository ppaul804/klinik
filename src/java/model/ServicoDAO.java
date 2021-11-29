/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author COUGAR
 */
public class ServicoDAO extends DataBaseDAO{
    
    public ServicoDAO() throws Exception{}
    
    public ArrayList<Servico> getLista() throws Exception{
        
        ArrayList<Servico> lista = new ArrayList<Servico>();
        String sql = "SELECT * FROM servico";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Servico servico = new Servico();
            servico.setIdServico(rs.getInt("IdSERVICO"));
            servico.setNome(rs.getString("NOME"));
            servico.setValor(rs.getDouble("VALOR"));
            lista.add(servico);
        }
        this.desconectar();
        return lista;
    }
    
    public boolean gravar (Servico servico) {
        try{
           this.conectar();
           String sql;
           if(servico.getIdServico()==0){
                sql = "INSERT INTO servico (NOME, VALOR)"
                        + "VALUES(?,?,?)";
            }else{
               sql = "UPDATE servico SET NOME=?, VALOR=? "
                       +  "WHERE idProduto=?";
           }
           PreparedStatement pstm = conn.prepareStatement(sql);
           pstm.setString(1, servico.getNome());
           pstm.setDouble(2, servico.getValor());
           if(servico.getIdServico()>0){
               pstm.setInt(3, servico.getIdServico());
           }
           pstm.execute();
           this.desconectar();
           return true;
           
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean deletar(Servico servico){
        try{
          this.conectar();
          String sql = "DELETE FROM servico where idSERVICO=?";
          PreparedStatement pstm = conn.prepareStatement(sql);
          pstm.setInt(1, servico.getIdServico());
          pstm.execute();
          this.desconectar();
          return true;
        }catch(Exception e){
            System.out.println("");
            return false;
        }
    }
    
    public Servico getCarregaPorID(int idServico) throws Exception{
        
        Servico servico = new Servico();
        String sql = "SELECT * FROM servico WHERE idSERVICO=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idServico);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            servico.setIdServico(rs.getInt("idSERVICO"));
            servico.setNome(rs.getString("NOME"));
            servico.setValor(rs.getDouble("VALOR"));
        }
        this.desconectar();
        return servico;
    }
}

