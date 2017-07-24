package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Funcionario;
import pojo.Padre;

public class DaoPadre {
    private Padre padre;
    private final String SQL_INCLUIR =
            "INSERT INTO PADRE VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    
    private final String SQL_ALTERAR =
            "UPDATE PADRE SET NOME = ?, DATANASCIMENTO = ?, RG = ?, CPF = ?, TELEFONE = ?,"
            + "CELULAR = ?, ENDERECO = ?, NUMCASA = ?, BAIRRO = ?"
            + "SITUACAO = ?, IDCIDADE = ? WHERE IDPADRE = ?";
    
    private final String SQL_EXCLUIR =
            "DELETE FROM PADRE WHERE IDPADRE = ?";
    
    private final String SQL_CONSULTAR =
            "SELECT * FROM PADRE WHERE IDPADRE = ?";
    
//    public static final String SQL_PESQUISAR =
//            "SELECT CEB.IDCEB, CEB.NOME, COLABORADOR.NOME "
//            + "FROM CEB, COLABORADOR "
//            + "WHERE COLABORADOR.IDCOLABORADOR = CEB.IDCOLABORADORORDER BY CEB.NOME";
                
    
    public DaoPadre(Padre padre) {
        this.padre = padre;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setString(1, padre.getNome());
           ps.setDate(2, padre.getDatanascimento());
           ps.setString(3, padre.getRg());
           ps.setString(4, padre.getCpf());
           ps.setString(5, padre.getTelefone());
           ps.setString(6, padre.getCelular());
           ps.setString(7, padre.getEndereco());
           ps.setString(8, padre.getNumcasa());
           ps.setString(9, padre.getBairro());
           ps.setString(10, padre.getSituacao());
           ps.setInt(11, padre.getIdcidade());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir o padre.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, padre.getNome());
           ps.setDate(2, padre.getDatanascimento());
           ps.setString(3, padre.getRg());
           ps.setString(4, padre.getCpf());
           ps.setString(5, padre.getTelefone());
           ps.setString(6, padre.getCelular());
           ps.setString(7, padre.getEndereco());
           ps.setString(8, padre.getNumcasa());
           ps.setString(9, padre.getBairro());
           ps.setString(10, padre.getSituacao());
           ps.setInt(11, padre.getIdcidade());
           ps.setInt(15, padre.getIdpadre());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar o padre.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, padre.getIdpadre());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir o padre.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, padre.getIdpadre());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               padre.setNome(rs.getString(2));
               padre.setDatanascimento(rs.getDate(3));
               padre.setRg(rs.getString(4));
               padre.setCpf(rs.getString(5));
               padre.setTelefone(rs.getString(6));
               padre.setCelular(rs.getString(7));
               padre.setEndereco(rs.getString(8));
               padre.setNumcasa(rs.getString(9));
               padre.setBairro(rs.getString(10));
               padre.setSituacao(rs.getString(11));
               padre.setIdcidade(rs.getInt(12));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar o padre.");
           e.printStackTrace();
           return false;
        }
    }
}