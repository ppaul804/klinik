package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContratoServicoDAO extends DataBaseDAO {

    public ContratoServicoDAO() throws Exception {}
    
    public ArrayList<ContratoServico> getCarregaPorId(int idContrato) throws Exception {
        
        this.conectar();
        String sql = "SELECT * FROM contrato_servico WHERE idContrato = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idContrato);
        ResultSet rs = pstm.executeQuery();
        ArrayList<ContratoServico> lista = new ArrayList<ContratoServico>();
        
        while (rs.next()) {            
            ContratoServico contratoServico = new ContratoServico();
            
            ContratoDAO conDAO = new ContratoDAO();
            contratoServico.setContrato(conDAO.getCarregaPorId(rs.getInt("idCONTRATO")));
            ServicoDAO sDAO = new ServicoDAO();
            contratoServico.setServico(sDAO.getCarregaPorID(rs.getInt("idSERVICO")));
            contratoServico.setQuantidade(rs.getInt("QUANTIDADE"));
            contratoServico.setValor(rs.getDouble("VALOR"));
            lista.add(contratoServico);
        }
        
        this.desconectar();
        return lista;
    }
    
    
}
