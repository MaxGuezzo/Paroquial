package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Ceb;
import pojo.Cidade;

public class DaoCeb {
    private Ceb ceb;
    private final String SQL_INCLUIR =
            "INSERT INTO CEB VALUES (?, ?, ?)";
    
    private final String SQL_ALTERAR =
            "UPDATE CEB SET NOME = ?, IDCOLABORADOR = ?, WHERE IDCEB = ?";
    
    private final String SQL_EXCLUIR =
            "DELETE FROM CEB WHERE IDCEB = ?";
    
    private final String SQL_CONSULTAR =
            "SELECT * FROM CEB WHERE IDCEB = ?";
    
    public static final String SQL_PESQUISAR =
            "SELECT CEB.IDCEB, CEB.NOME, COLABORADOR.NOME "
            + "FROM CEB, COLABORADOR "
            + "WHERE COLABORADOR.IDCOLABORADOR = CEB.IDCOLABORADORORDER BY CEB.NOME";
                
    
    public DaoCeb(Ceb ceb) {
        this.ceb = ceb;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setInt(1, ceb.getIdceb());
           ps.setString(2, ceb.getNome());
           ps.setInt(3, ceb.getIdcolaborador());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir a ceb.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, ceb.getNome());
           ps.setInt(2, ceb.getIdcolaborador());
           ps.setInt(3, ceb.getIdceb());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar a ceb.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, ceb.getIdceb());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir a ceb.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, ceb.getIdceb());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               ceb.setNome(rs.getString(2));
               ceb.setIdcolaborador(rs.getInt(3));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar a ceb.");
           e.printStackTrace();
           return false;
        }
    }
}