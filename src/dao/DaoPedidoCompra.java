package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.PedidoCompra;

public class DaoPedidoCompra {
    private PedidoCompra pedidoCompra;
    private final String SQL_INCLUIR =
            "INSERT INTO PEDIDOCOMPRA VALUES (?,?,?,?,?)";
    
    private final String SQL_ALTERAR =
            "UPDATE PEDIDOCOMPRA SET DATAPEDIDO = ?, VALOR = ?, IDSITUACAOPEDIDO = ?, IDFORNECEDOR = ?,"
            + "IDFUNCIONARIO = ? WHERE IDPEDIDOCOMPRA = ?";
    
    private final String SQL_EXCLUIR =
            "DELETE FROM PEDIDOCOMPRA WHERE IDPEDIDOCOMPRA = ?";
    
    private final String SQL_CONSULTAR =
            "SELECT * FROM PEDIDOCOMPRA WHERE IDPEDIDOCOMPRA = ?";
    
//    public static final String SQL_PESQUISAR =
//            "SELECT CEB.IDCEB, CEB.NOME, COLABORADOR.NOME "
//            + "FROM CEB, COLABORADOR "
//            + "WHERE COLABORADOR.IDCOLABORADOR = CEB.IDCOLABORADORORDER BY CEB.NOME";
                
    
    public DaoPedidoCompra(PedidoCompra pedidoCompra) {
        this.pedidoCompra = pedidoCompra;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setDate(1, pedidoCompra.getDatapedido());
           ps.setBigDecimal(2, pedidoCompra.getValor());
           ps.setInt(3, pedidoCompra.getIdsituacaopedido());
           ps.setInt(4, pedidoCompra.getIdfornecedor());
           ps.setInt(5, pedidoCompra.getIdfuncionario());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir o pedidoCompra.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setDate(1, pedidoCompra.getDatapedido());
           ps.setBigDecimal(2, pedidoCompra.getValor());
           ps.setInt(3, pedidoCompra.getIdsituacaopedido());
           ps.setInt(4, pedidoCompra.getIdfornecedor());
           ps.setInt(5, pedidoCompra.getIdfuncionario());
           ps.setInt(6, pedidoCompra.getIdpedidocompra());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar o pedidoCompra.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, pedidoCompra.getIdpedidocompra());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir o pedidoCompra.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, pedidoCompra.getIdpedidocompra());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               pedidoCompra.setDatapedido(rs.getDate(2));
               pedidoCompra.setValor(rs.getBigDecimal(3));
               pedidoCompra.setIdsituacaopedido(rs.getInt(4));
               pedidoCompra.setIdfornecedor(rs.getInt(5));
               pedidoCompra.setIdfuncionario(rs.getInt(6));
 
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar o pedidoCompra.");
           e.printStackTrace();
           return false;
        }
    }
}