package model;

import com.mysql.jdbc.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO extends DataBaseDAO {

    public ConsultaDAO() throws Exception {
    }

    public boolean gravar(Consulta consulta) {
        try {
            this.conectar();
            String sql;
            if (consulta.getIdConsulta() == 0) {
                sql = "INSERT INTO consulta (idCLIENTE, idUSUARIO, STATUS, DATA_CONSULTA) VALUES (?,?,?,?)";

                PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstm.setInt(1, consulta.getCliente().getIdCliente());
                pstm.setInt(2, consulta.getAtendente().getIdUsuario());
                pstm.setString(3, consulta.getStatus());
                pstm.setTimestamp(4, new Timestamp(consulta.getData_consulta().getTime()));
                pstm.execute();
                ResultSet rs = pstm.getGeneratedKeys();

                if (rs.next()) {
                    consulta.setIdConsulta(rs.getInt(1));
                }
                for (ServicoConsulta servicoConsulta : consulta.getCarrinho()) {
                    String sql_servicoConsulta = "INSERT INTO servico_consulta (idCONSULTA, idSERVICO, QUANTIDADE, VALOR) VALUES (?,?,?,?)";

                    PreparedStatement pstm_cs = conn.prepareStatement(sql_servicoConsulta);
                    pstm_cs.setInt(1, consulta.getIdConsulta());
                    pstm_cs.setInt(2, servicoConsulta.getServico().getIdServico());
                    pstm_cs.setInt(3, servicoConsulta.getQuantidade());
                    pstm_cs.setDouble(4, servicoConsulta.getValor());
                    pstm_cs.execute();
                }
            } else {
                sql = "UPDATE consulta SET idCLIENTE = ?, idUSUARIO = ?, STATUS = ?, DATA_CONSULTA = ? WHERE idCONSULTA = ?";

                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setInt(1, consulta.getCliente().getIdCliente());
                pstm.setInt(2, consulta.getAtendente().getIdUsuario());
                pstm.setString(3, consulta.getStatus());
                pstm.setTimestamp(4, new Timestamp(consulta.getData_consulta().getTime()));
                if (consulta.getIdConsulta() > 0) {
                    pstm.setInt(5, consulta.getIdConsulta());
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

    public boolean deletar(Consulta consulta) {
        try {
            this.conectar();

            String sqlSC = "DELETE FROM servico_consulta WHERE idCONSULTA = ?";
            PreparedStatement pstmCS = conn.prepareStatement(sqlSC);
            pstmCS.setInt(1, consulta.getIdConsulta());
            pstmCS.execute();

            String sql = "DELETE FROM consulta WHERE idCONSULTA = ?;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, consulta.getIdConsulta());
            pstm.execute();

            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public Consulta getCarregaPorId(int idConsulta) throws Exception {
        String sql = "SELECT * FROM consulta WHERE idConsulta = ?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idConsulta);

        ResultSet rs = pstm.executeQuery();

        Consulta consulta = new Consulta();
        ClienteDAO clienteDAO = new ClienteDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (rs.next()) {
            consulta.setIdConsulta(rs.getInt("idCONSULTA"));
            consulta.setCliente(clienteDAO.getCarregaPorId(rs.getInt("idCLIENTE")));
            consulta.setAtendente(usuarioDAO.getCarregaPorId(rs.getInt("idUSUARIO")));
            consulta.setStatus(rs.getString("STATUS"));
            consulta.setData_consulta(new Date(rs.getTimestamp("DATA_CONSULTA").getTime()));
        }

        this.desconectar();
        return consulta;

    }

    public List<Consulta> getLista() throws Exception {
        String sql = "SELECT * FROM consulta";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        List<Consulta> lista = new ArrayList<>();
        ClienteDAO clienteDAO = new ClienteDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        while (rs.next()) {
            Consulta consulta = new Consulta();
            consulta.setIdConsulta(rs.getInt("idCONSULTA"));
            consulta.setCliente(clienteDAO.getCarregaPorId(rs.getInt("idCLIENTE")));
            consulta.setAtendente(usuarioDAO.getCarregaPorId(rs.getInt("idUSUARIO")));
            consulta.setStatus(rs.getString("STATUS"));
            consulta.setData_consulta(new Date(rs.getTimestamp("DATA_CONSULTA").getTime()));
            lista.add(consulta);
        }

        this.desconectar();
        return lista;
    }
    
    public ArrayList<Consulta> consultasEmAberto() throws Exception {

        this.conectar();
        String sql = "SELECT idCONSULTA, idCLIENTE, DATA_CONSULTA FROM consulta WHERE STATUS = 'aberto'";

        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        ArrayList<Consulta> lista = new ArrayList<Consulta>();

        while (rs.next()) {
            Consulta consulta = new Consulta();
            consulta.setIdConsulta(rs.getInt("idCONSULTA"));
            consulta.setData_consulta(new Date(rs.getTimestamp("DATA_CONSULTA").getTime()));
            ClienteDAO cDAO = new ClienteDAO();
            consulta.setCliente(cDAO.getCarregaPorId(rs.getInt("idCLIENTE")));
            lista.add(consulta);
        }

        this.desconectar();
        return lista;
    }

}
