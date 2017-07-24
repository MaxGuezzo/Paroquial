package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Cor;
import pojo.TipoRecebimento;

public class DaoTipoRecebimento {
    private TipoRecebimento tipoRecebimento;
    private final String SQL_INCLUIR =
            "INSERT INTO TIPORECEBIMENTO VALUES (?)";
    
    private final String SQL_ALTERAR =
            "UPDATE TIPORECEBIMENTO SET NOME = ?, WHERE IDTIPORECEBIMENTO = ?";
    
    private final String SQL_EXCLUIR =
            "DELETE FROM TIPORECEBIMENTO WHERE IDTIPORECEBIMENTO = ?";
    
    private final String SQL_CONSULTAR =
            "SELECT * FROM TIPORECEBIMENTO WHERE IDTIPORECEBIMENTO = ?";
    
//    public static final String SQL_PESQUISAR =
//            "SELECT CEB.IDCEB, CEB.NOME, COLABORADOR.NOME "
//            + "FROM CEB, COLABORADOR "
//            + "WHERE COLABORADOR.IDCOLABORADOR = CEB.IDCOLABORADORORDER BY CEB.NOME";
                
    
    public DaoTipoRecebimento(TipoRecebimento tipoRecebimento) {
        this.tipoRecebimento = tipoRecebimento;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setString(1, tipoRecebimento.getNome());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir o tipo de Recebimento.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, tipoRecebimento.getNome());
           ps.setInt(2, tipoRecebimento.getIdtiporecebimento());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar o tipo de Recebimento.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, tipoRecebimento.getIdtiporecebimento());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir a tipoRecebimento.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, tipoRecebimento.getIdtiporecebimento());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               tipoRecebimento.setNome(rs.getString(2));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar o tipo de Recebimento.");
           e.printStackTrace();
           return false;
        }
    }
}