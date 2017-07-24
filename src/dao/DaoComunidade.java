package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Ceb;
import pojo.Comunidade;

public class DaoComunidade {
    private Comunidade comunidade;
    private final String SQL_INCLUIR =
            "INSERT INTO CEB VALUES (?,?,?,?)";
    
    private final String SQL_ALTERAR =
            "UPDATE COMUNIDADE SET NOME = ?, IDCOLABORADOR = ?, IDCEB = ? WHERE IDCOMUNIDADE = ?";
    
    private final String SQL_EXCLUIR =
            "DELETE FROM COMUNIDADE WHERE IDCOMUNIDADE = ?";
    
    private final String SQL_CONSULTAR =
            "SELECT * FROM COMUNIDADE WHERE IDCOMUNIDADE = ?";
    
//    public static final String SQL_PESQUISAR =
//            "SELECT CEB.IDCEB, CEB.NOME, COLABORADOR.NOME "
//            + "FROM CEB, COLABORADOR "
//            + "WHERE COLABORADOR.IDCOLABORADOR = CEB.IDCOLABORADORORDER BY CEB.NOME";
                
    
    public DaoComunidade(Comunidade comunidade) {
        this.comunidade = comunidade;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setInt(1, comunidade.getIdcomunidade());
           ps.setString(2, comunidade.getNome());
           ps.setInt(3, comunidade.getIdcolaborador());
           ps.setInt(4, comunidade.getIdceb());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir a comunidade.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, comunidade.getNome());
           ps.setInt(2, comunidade.getIdcolaborador());
           ps.setInt(3, comunidade.getIdceb());
           ps.setInt(4, comunidade.getIdcomunidade());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar a comunidade.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, comunidade.getIdcomunidade());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir a comunidade.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, comunidade.getIdcomunidade());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               comunidade.setNome(rs.getString(2));
              comunidade.setIdcolaborador(rs.getInt(3));
              comunidade.setIdceb(rs.getInt(4));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar a comunidade.");
           e.printStackTrace();
           return false;
        }
    }
}