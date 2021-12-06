package model;

import com.mysql.jdbc.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ContratoDAO extends DataBaseDAO {

    public ContratoDAO() throws Exception {}
    
    public boolean gravar(Contrato contrato) {
        
        try {
            
            this.conectar();
            String sql;
            if (contrato.getIdContrato() == 0) {
                sql = "INSERT INTO contrato (idCLIENTE, idUSUARIO, STATUS, DATA_CONTRATO) VALUES (?,?,?,?)";
                
                PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstm.setInt(1, contrato.getIdCliente().getIdCliente());
                pstm.setInt(2, contrato.getAtendente().getIdUsuario());
                pstm.setString(3, contrato.getStatus());
                pstm.setTimestamp(4, new Timestamp(contrato.getData_contrato().getTime()));
                pstm.execute();
                ResultSet rs = pstm.getGeneratedKeys();

                if (rs.next()) {
                    contrato.setIdContrato(rs.getInt(1));
                }
                for (ContratoServico contratoServico: contrato.getCarrinho()) {
                    String sql_contratoServico = "INSERT INTO contrato_servico (idCONTRATO, idSERVICO, QUANTIDADE, VALOR) VALUES (?,?,?,?)";

                    PreparedStatement pstm_cs = conn.prepareStatement(sql_contratoServico);
                    pstm_cs.setInt(1, contrato.getIdContrato());
                    pstm_cs.setInt(2, contratoServico.getServico().getIdServico());
                    pstm_cs.setInt(3, contratoServico.getQuantidade());
                    pstm_cs.setDouble(4, contratoServico.getValor());
                    pstm_cs.execute();
                }
            } else {
                sql = "UPDATE contrato SET idCLIENTE = ?, idUSUARIO = ?, STATUS = ?, DATA_CONTRATO = ? WHERE idCONTRATO = ?";
                
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setInt(1, contrato.getIdCliente().getIdCliente());
                pstm.setInt(2, contrato.getAtendente().getIdUsuario());
                pstm.setString(3, contrato.getStatus());
                pstm.setTimestamp(4, new Timestamp(contrato.getData_contrato().getTime()));
                if (contrato.getIdContrato() > 0) {
                    pstm.setInt(5, contrato.getIdContrato());
                }
                pstm.execute();
            }
            
            this.desconectar();
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean deletar(Contrato contrato) {
        
        try {
            
            this.conectar();
            String sqlCS = "DELETE FROM contrato_servico WHERE idContrato = ?";
            PreparedStatement pstmCS = conn.prepareStatement(sqlCS);
            pstmCS.setInt(1, contrato.getIdContrato());
            pstmCS.execute();
            
            String sqlC = "DELETE FROM contrato WHERE idContrato = ?";
            PreparedStatement pstmC = conn.prepareStatement(sqlC);
            pstmC.setInt(1, contrato.getIdContrato());
            pstmC.execute();
            
            this.desconectar();
            return true;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public Contrato getCarregaPorId(int idContrato) throws Exception {
        
        Contrato contrato = new Contrato();
        String sql = "SELECT * FROM contrato WHERE idContrato = ?";
        this.conectar();
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idContrato);
        ResultSet rs = pstm.executeQuery();
        
        if (rs.next()) {
            contrato.setIdContrato(rs.getInt("idCONTRATO"));
            contrato.setData_contrato(new Date(rs.getTimestamp("DATA_CONTRATO").getTime()));
            contrato.setStatus(rs.getString("STATUS"));
            ClienteDAO cDAO = new ClienteDAO();
            contrato.setIdCliente(cDAO.getCarregaPorId(rs.getInt("idCLIENTE")));
            UsuarioDAO uDAO = new UsuarioDAO();
            contrato.setAtendente(uDAO.getCarregaPorId(rs.getInt("idUSUARIO")));
        }
        
        this.desconectar();
        return contrato;
    }
    
    public ArrayList<Contrato> getLista() throws Exception {
        
        this.conectar();
        String sql = "SELECT * FROM contrato";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        ArrayList<Contrato> lista = new ArrayList<Contrato>();
        
        while (rs.next()) {            
            Contrato contrato = new Contrato();
            contrato.setIdContrato(rs.getInt("idCONTRATO"));
            contrato.setData_contrato(rs.getDate("DATA_CONTRATO"));
            contrato.setStatus(rs.getString("STATUS"));
            ClienteDAO cDAO = new ClienteDAO();
            contrato.setIdCliente(cDAO.getCarregaPorId(rs.getInt("idCLIENTE")));
            UsuarioDAO uDAO = new UsuarioDAO();
            contrato.setAtendente(uDAO.getCarregaPorId(rs.getInt("idUSUARIO")));
            lista.add(contrato);
        }
        
        this.desconectar();
        return lista;
    }
    
}
