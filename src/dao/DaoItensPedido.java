
package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.ItensPedido;

public class DaoItensPedido {
    private ItensPedido itensPedido;
    private final String SQL_INCLUIR =
            "INSERT INTO ITENSPEDIDO VALUES (?, ?, ?)";
    private final String SQL_ALTERAR =
            "UPDATE ITENSPEDIDO SET QUANTIDADE = ?, VALORUNITARIO = ?, IDPRODUTO = ? WHERE IDITENSPEDIDO = ?";
    private final String SQL_EXCLUIR =
            "DELETE FROM ITENSPEDIDO WHERE ITENSPEDIDO = ?";
    private final String SQL_CONSULTAR =
            "SELECT * FROM ITENSPEDIDO WHERE ITENSPEDIDO = ?";
    
//    public static final String SQL_PESQUISAR =
//            "SELECT CIDADE.IDCIDADE, CIDADE.NOME, ESTADO.SIGLA FROM itensPedido, ESTADO\n" +
//                "where ESTADO.IDESTADO = CIDADE.IDESTADO ORDER BY CIDADE.NOME";
    
    public DaoItensPedido(ItensPedido itensPedido) {
        this.itensPedido = itensPedido;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setInt(1, itensPedido.getQuantidade());
           ps.setBigDecimal(2, itensPedido.getValorunitario());
           ps.setInt(3, itensPedido.getIdproduto());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir o Item do Pedido.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setInt(1, itensPedido.getQuantidade());
           ps.setBigDecimal(2, itensPedido.getValorunitario());
           ps.setInt(3, itensPedido.getIdproduto());
           ps.setInt(4, itensPedido.getIditenspedido());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar o Item do Pedido.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, itensPedido.getIditenspedido());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir o Item do Pedido.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, itensPedido.getIditenspedido());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               itensPedido.setQuantidade(rs.getInt(2));
               itensPedido.setValorunitario(rs.getBigDecimal(3));
               itensPedido.setIdproduto(rs.getInt(3));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar o Item do Pedido.");
           e.printStackTrace();
           return false;
        }
    }
}