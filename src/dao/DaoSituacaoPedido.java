package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Cor;
import pojo.SituacaoPedido;

public class DaoSituacaoPedido {
    private SituacaoPedido situacaoPedido;
    private final String SQL_INCLUIR =
            "INSERT INTO SITUACAOPEDIDO VALUES (?)";
    
    private final String SQL_ALTERAR =
            "UPDATE SITUACAOPEDIDO SET NOME = ?, WHERE IDSITUACAOPEDIDO = ?";
    
    private final String SQL_EXCLUIR =
            "DELETE FROM SITUACAOPEDIDO WHERE IDSITUACAOPEDIDO = ?";
    
    private final String SQL_CONSULTAR =
            "SELECT * FROM SITUACAOPEDIDO WHERE IDSITUACAOPEDIDO = ?";
    
//    public static final String SQL_PESQUISAR =
//            "SELECT CEB.IDCEB, CEB.NOME, COLABORADOR.NOME "
//            + "FROM CEB, COLABORADOR "
//            + "WHERE COLABORADOR.IDCOLABORADOR = CEB.IDCOLABORADORORDER BY CEB.NOME";
                
    
    public DaoSituacaoPedido(SituacaoPedido situacaoPedido) {
        this.situacaoPedido = situacaoPedido;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setString(1, situacaoPedido.getNome());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir a Situacao do Pedido.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, situacaoPedido.getNome());
           ps.setInt(2, situacaoPedido.getIdsituacaopedido());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar a Situacao do Pedido.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, situacaoPedido.getIdsituacaopedido());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir a Situacao do Pedido.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, situacaoPedido.getIdsituacaopedido());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               situacaoPedido.setNome(rs.getString(2));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar a Situacao do Pedido.");
           e.printStackTrace();
           return false;
        }
    }
}