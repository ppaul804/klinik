package model;

import br.com.caelum.stella.format.CPFFormatter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDAO extends DataBaseDAO {

    private static final CPFFormatter CPF_FORMATTER = new CPFFormatter();

    public ClienteDAO() throws Exception {}

    public ArrayList<Cliente> getLista() throws Exception {

        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        String sql = "SELECT * FROM cliente";

        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("cliente.idCliente"));

            cliente.setNome(rs.getString("cliente.NOME"));
            cliente.setCpf(CPF_FORMATTER.format(rs.getString("cliente.CPF")));
            cliente.setRg(rs.getString("cliente.RG"));
            cliente.setEmail(rs.getString("cliente.EMAIL"));
            cliente.setTelefone(rs.getString("cliente.TELEFONE"));
            cliente.setStatus(rs.getInt("cliente.STATUS"));
            cliente.setEndereco(rs.getString("cliente.ENDERECO"));
            cliente.setComplemento(rs.getString("cliente.COMPLEMENTO"));
            cliente.setCidade(rs.getString("cliente.CIDADE"));
            cliente.setSexo(rs.getString("cliente.SEXO").charAt(0));
            cliente.setData_de_nascimento(rs.getDate("cliente.DATA_DE_NASCIMENTO"));
            cliente.setObservacao(rs.getString("cliente.OBSERVACAO"));
            lista.add(cliente);
        }

        this.desconectar();
        return lista;

    }

    public boolean gravar(Cliente cliente) {

        try {

            String sql;
            this.conectar();

            if (cliente.getIdCliente() == 0) {
                sql = "INSERT INTO cliente (NOME, CPF, RG, EMAIL, TELEFONE, STATUS, ENDERECO, COMPLEMENTO, CIDADE, SEXO, DATA_DE_NASCIMENTO,OBSERVACAO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            } else {
                sql = "UPDATE cliente SET NOME = ?, CPF = ?, RG = ?, EMAIL = ?, TELEFONE = ?, STATUS = ?, ENDERECO = ?, COMPLEMENTO = ?, CIDADE = ?, SEXO = ?, DATA_DE_NASCIMENTO = ?,OBSERVACAO = ? WHERE idCLIENTE=?";
            }

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getCpf());
            pstm.setString(3, cliente.getRg());
            pstm.setString(4, cliente.getEmail());
            pstm.setString(5, cliente.getTelefone());
            pstm.setInt(6, cliente.getStatus());
            pstm.setString(7, cliente.getEndereco());
            pstm.setString(8, cliente.getComplemento());
            pstm.setString(9, cliente.getCidade());
            pstm.setString(10, Character.toString(cliente.getSexo()));
            pstm.setDate(11, new Date(cliente.getData_de_nascimento().getTime()));
            pstm.setString(12, cliente.getObservacao());

            if (cliente.getIdCliente() > 0) {
                pstm.setInt(13, cliente.getIdCliente());
            }

            pstm.execute();
            this.desconectar();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deletar(Cliente cliente) {

        try {
            this.conectar();
            String sql = "UPDATE cliente SET STATUS = 0 WHERE idCLIENTE=?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, cliente.getIdCliente());
            pstm.execute();
            this.desconectar();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public Cliente getCarregaPorId(int idCliente) throws Exception {

        Cliente cliente = new Cliente();

        String sql = "SELECT * FROM cliente WHERE idCliente= ?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idCliente);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            cliente.setIdCliente(rs.getInt("cliente.idCLIENTE"));

            cliente.setNome(rs.getString("cliente.NOME"));
            cliente.setCpf(rs.getString("cliente.CPF"));
            cliente.setRg(rs.getString("cliente.RG"));
            cliente.setEmail(rs.getString("cliente.EMAIL"));
            cliente.setTelefone(rs.getString("cliente.TELEFONE"));
            cliente.setStatus(rs.getInt("cliente.STATUS"));
            cliente.setEndereco(rs.getString("cliente.ENDERECO"));
            cliente.setComplemento(rs.getString("cliente.COMPLEMENTO"));
            cliente.setCidade(rs.getString("cliente.CIDADE"));
            cliente.setSexo(rs.getString("cliente.SEXO").charAt(0));
            cliente.setData_de_nascimento(rs.getDate("cliente.DATA_DE_NASCIMENTO"));
            cliente.setObservacao(rs.getString("cliente.OBSERVACAO"));
        }

        this.desconectar();
        return cliente;

    }

    public ArrayList<Cliente> ultimosCadastrados() throws Exception {

        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        String sql = "SELECT idCLIENTE, NOME, CPF FROM cliente ORDER BY idCLIENTE desc LIMIT 5";

        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("cliente.idCliente"));

            cliente.setNome(rs.getString("cliente.NOME"));
            cliente.setCpf(CPF_FORMATTER.format(rs.getString("cliente.CPF")));
            lista.add(cliente);
        }

        this.desconectar();
        return lista;

    }
}
