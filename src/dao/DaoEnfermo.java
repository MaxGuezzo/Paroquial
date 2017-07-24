package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Enfermo;

public class DaoEnfermo {
    private Enfermo enfermo;
    private final String SQL_INCLUIR =
            "INSERT INTO ENFERMO VALUES (?,?,?,?,?,?,?,?,?,?)";
    
    private final String SQL_ALTERAR =
            "UPDATE ENFERMO SET NOME = ?, DATANASCIMENTO = ?, TELEFONE = ?,"
            + "CELULAR = ?, ENDERECO = ?, NUMCASA = ?, BAIRRO = ?"
            + "SITUACAO = ?, IDCIDADE = ? WHERE IDENFERMO = ?";
    
    private final String SQL_EXCLUIR =
            "DELETE FROM ENFERMO WHERE IDENFERMO = ?";
    
    private final String SQL_CONSULTAR =
            "SELECT * FROM ENFERMO WHERE IDENFERMO = ?";
    
//    public static final String SQL_PESQUISAR =
//            "SELECT CEB.IDCEB, CEB.NOME, COLABORADOR.NOME "
//            + "FROM CEB, COLABORADOR "
//            + "WHERE COLABORADOR.IDCOLABORADOR = CEB.IDCOLABORADORORDER BY CEB.NOME";
                
    
    public DaoEnfermo(Enfermo enfermo) {
        this.enfermo = enfermo;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setInt(1, enfermo.getIdenfermo());
           ps.setString(2, enfermo.getNome());
           ps.setDate(3, enfermo.getDatanascimento());
           ps.setString(4, enfermo.getTelefone());
           ps.setString(5, enfermo.getCelular());
           ps.setString(6, enfermo.getEndereco());
           ps.setString(7, enfermo.getNumcasa());
           ps.setString(8, enfermo.getBairro());
           ps.setString(9, enfermo.getSituacao());
           ps.setInt(10, enfermo.getIdcidade());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir o enfermo.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, enfermo.getNome());
           ps.setDate(2, enfermo.getDatanascimento());
           ps.setString(3, enfermo.getTelefone());
           ps.setString(4, enfermo.getCelular());
           ps.setString(5, enfermo.getEndereco());
           ps.setString(6, enfermo.getNumcasa());
           ps.setString(7, enfermo.getBairro());
           ps.setString(8, enfermo.getSituacao());
           ps.setInt(9, enfermo.getIdcidade());
           ps.setInt(10, enfermo.getIdenfermo());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar o enfermo.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, enfermo.getIdenfermo());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir o enfermo.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, enfermo.getIdenfermo());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               enfermo.setNome(rs.getString(2));
               enfermo.setDatanascimento(rs.getDate(3));
               enfermo.setTelefone(rs.getString(4));
               enfermo.setCelular(rs.getString(5));
               enfermo.setEndereco(rs.getString(6));
               enfermo.setNumcasa(rs.getString(7));
               enfermo.setBairro(rs.getString(8));
               enfermo.setSituacao(rs.getString(9));
               enfermo.setIdcidade(rs.getInt(10));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar o enfermo.");
           e.printStackTrace();
           return false;
        }
    }
}