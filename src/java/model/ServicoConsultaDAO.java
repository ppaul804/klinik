package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ServicoConsultaDAO extends DataBaseDAO {

    public ServicoConsultaDAO() throws Exception {}
    
    public ArrayList<ServicoConsulta> getCarregaPorId(int idConsulta) throws Exception {
        
        this.conectar();
        String sql = "SELECT * FROM servico_consulta WHERE idCONSULTA = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idConsulta);
        ResultSet rs = pstm.executeQuery();
        ArrayList<ServicoConsulta> lista = new ArrayList<ServicoConsulta>();
        
        while (rs.next()) {            
            ServicoConsulta contratoServico = new ServicoConsulta();
            
            ConsultaDAO conDAO = new ConsultaDAO();
            contratoServico.setConsulta(conDAO.getCarregaPorId(rs.getInt("idCONSULTA")));
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
