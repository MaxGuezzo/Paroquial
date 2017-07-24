package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Estado;
import pojo.Produto;

public class DaoProduto {
    private Produto produto;
    private final String SQL_INCLUIR =
            "INSERT INTO PRODUTO VALUES (?, ?, ?, ? , ?)";
    private final String SQL_ALTERAR =
            "UPDATE PRODUTO SET NOME = ?, VALORUNITARIO = ?, QUANTIDADE = ?, IDCOR = ?, IDTIPOPRODUTO = ?, WHERE IDESTADO = ?";
    private final String SQL_EXCLUIR =
            "DELETE FROM PRODUTO WHERE IDPRODUTO = ?";
    private final String SQL_CONSULTAR =
            "SELECT * FROM PRODUTO WHERE IDPRODUTO = ?";
    
//    public static final String SQL_PESQUISAR =
//            "SELECT IDESTADO, NOME, SIGLA FROM ESTADO ORDER BY NOME";
//    public static final String SQLCOMBOBOX = "SELECT IDESTADO, NOME || '-' || SIGLA FROM ESTADO ORDER BY NOME";
    
    public DaoProduto(Produto produto) {
        this.produto = produto;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setString(1, produto.getNome());
           ps.setBigDecimal(2, produto.getValorunitario());
           ps.setInt(3, produto.getQuantidade());
           ps.setInt(4, produto.getIdcor());
           ps.setInt(5, produto.getIdtipoproduto());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir o Tipo do Produto.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, produto.getNome());
           ps.setBigDecimal(2, produto.getValorunitario());
           ps.setInt(3, produto.getQuantidade());
           ps.setInt(4, produto.getIdcor());
           ps.setInt(5, produto.getIdtipoproduto());
           ps.setInt(6, produto.getIdproduto());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar o Tipo do Produto.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, produto.getIdproduto());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir o Tipo do Produto.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, produto.getIdproduto());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               produto.setNome(rs.getString(2));
               produto.setValorunitario(rs.getBigDecimal(3));
               produto.setQuantidade(rs.getInt(4));
               produto.setIdcor(rs.getInt(5));
               produto.setIdtipoproduto(rs.getInt(6));

           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar o Tipo do Produto.");
           e.printStackTrace();
           return false;
        }
    }
}