package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ClienteDAO extends DataBaseDAO {
    
    public ClienteDAO() throws Exception {}
    public ArrayList<Cliente> getLista() throws Exception{
        
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        String sql = "SELECT * FROM cliente";
        
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) {            
            Cliente cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("cliente.idCliente"));
  
            cliente.setNome(rs.getString("cliente.NOME"));
            cliente.setCpf(rs.getString("cliente.CPF"));
            cliente.setRg(rs.getString("cliente.RG"));
            cliente.setEmail(rs.getString("cliente.EMAIL"));
            cliente.setTelefone(rs.getString("cliente.TELEFONE"));
            cliente.setEndereco(rs.getString("cliente.ENDERECO"));
            cliente.setComplemento(rs.getString("cliente.COMPLEMENTO"));
            cliente.setCidade(rs.getString("cliente.CIDADE"));
            cliente.setSexo(rs.getString("cliente.SEXO").charAt(0));
            cliente.setData_de_nascimento(rs.getDate("cliente.DATA_DE_NASCIMENTO"));
            lista.add(cliente);
        }
        
        this.desconectar();
        return lista;
        
    }
}
