package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Paul
 */
public class ConsultaDAO extends DataBaseDAO {

    public ConsultaDAO() throws Exception {
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
            consulta.setUsuario(usuarioDAO.getCarregaPorId(rs.getInt("idUSUARIO")));
            consulta.setStatus(rs.getString("STATUS"));
            Date data = rs.getDate("DATA_CONSULTA");
            consulta.setData(data.toLocalDate());
            Time hora = rs.getTime("HORA_CONSULTA");
            consulta.setHora(hora.toLocalTime());
            lista.add(consulta);
        }

        this.desconectar();
        return lista;
    }

    public boolean gravar(Consulta consulta) {
        try {
            String sql;
            this.conectar();
            if (consulta.getIdConsulta() <= 0) {
                sql = "INSERT INTO consulta (idCONSULTA, idCLIENTE, idUSUARIO, STATUS, DATA_CONSULTA, HORA_CONSULTA)\n"
                        + "VALUES (?, ?, ?, ?, ?, ?);";
            } else {
                sql = "UPDATE consulta\n"
                        + "SET idCONSULTA    = ?,\n"
                        + "    idCLIENTE     = ?,\n"
                        + "    idUSUARIO     = ?,\n"
                        + "    STATUS        = ?,\n"
                        + "    DATA_CONSULTA = ?,\n"
                        + "    HORA_CONSULTA = ?\n"
                        + "WHERE idCONSULTA = ?;";
            }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, consulta.getIdConsulta());
            pstm.setInt(2, consulta.getCliente().getIdCliente());
            pstm.setInt(3, consulta.getUsuario().getIdUsuario());
            pstm.setString(4, consulta.getStatus());
            pstm.setDate(5, Date.valueOf(consulta.getData()));
            pstm.setTime(6, Time.valueOf(consulta.getHora()));
            if (consulta.getIdConsulta() > 0) {
                pstm.setInt(7, consulta.getIdConsulta());
            }

            pstm.execute();
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
        String sql = "SELECT * FROM consulta WHERE idConsulta= ?";
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
            consulta.setUsuario(usuarioDAO.getCarregaPorId(rs.getInt("idUSUARIO")));
            consulta.setStatus(rs.getString("STATUS"));
            Date data = rs.getDate("DATA_CONSULTA");
            consulta.setData(data.toLocalDate());
            Time hora = rs.getTime("HORA_CONSULTA");
            consulta.setHora(hora.toLocalTime());
        }

        this.desconectar();
        return consulta;

    }

}
