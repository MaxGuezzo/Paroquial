package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Cor;
import pojo.TipoProduto;

public class DaoTipoProduto {
    private TipoProduto tipoProduto;
    private final String SQL_INCLUIR =
            "INSERT INTO TIPO_PRODUTO VALUES (null,?)";
    
    private final String SQL_ALTERAR =
            "UPDATE TIPO_PRODUTO SET NOME = ? WHERE IDTIPOPRODUTO = ?";
    
    private final String SQL_EXCLUIR =
            "DELETE FROM TIPO_PRODUTO WHERE IDTIPOPRODUTO = ?";
    
    private final String SQL_CONSULTAR =
            "SELECT * FROM TIPO_PRODUTO WHERE IDTIPOPRODUTO = ?";
    
    public static final String SQL_PESQUISAR =
            "SELECT IDTIPOPRODUTO, NOME FROM TIPO_PRODUTO";
                
    
     public String pesquisa(String texto, int valor){
        String sql = "SELECT * FROM TIPO_PRODUTO WHERE";
        if(valor == 0){
            sql = sql+" NOME LIKE'"+texto+"%' ORDER BY NOME";
        }else{
            sql = sql+" IDTIPOPRODUTO LIKE'"+texto+"%' ORDER BY IDTIPOPRODUTO";
        }
        return(sql);
    }
     
    public DaoTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setString(1, tipoProduto.getNome());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir o tipo do Produto.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, tipoProduto.getNome());
           ps.setInt(2, tipoProduto.getIdtipoproduto());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar o tipo do Produto.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, tipoProduto.getIdtipoproduto());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir o tipo do Produto.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, tipoProduto.getIdtipoproduto());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               tipoProduto.setNome(rs.getString(2));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar o tipo do Produto.");
           e.printStackTrace();
           return false;
        }
    }
}