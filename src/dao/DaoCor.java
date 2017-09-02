package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Ceb;
import pojo.Cor;

public class DaoCor {
    private Cor cor;
    private final String SQL_INCLUIR =
            "INSERT INTO COR VALUES (?,?)";
    
    private final String SQL_ALTERAR =
            "UPDATE COR SET NOME = ? WHERE IDCOR = ?";
    
    private final String SQL_EXCLUIR =
            "DELETE FROM COR WHERE IDCOR = ?";
    
    private final String SQL_CONSULTAR =
            "SELECT * FROM COR WHERE IDCOR = ?";
    
    public static final String SQL_PESQUISAR =
            "SELECT IDCOR, NOME FROM COR";
                
    
    public String pesquisa(String texto, int valor){
        String sql = "SELECT * FROM COR WHERE";
        if(valor == 0){
            sql = sql+" NOME LIKE'"+texto+"%' ORDER BY NOME";
        }else{
            sql = sql+" IDCOR LIKE'"+texto+"%' ORDER BY IDCOR";
        }
        return(sql);
    }           
    
    public DaoCor(Cor cor) {
        this.cor = cor;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setInt(1, cor.getIdcor());
           ps.setString(2, cor.getNome());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir a cor.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, cor.getNome());
           ps.setInt(2, cor.getIdcor());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar a cor.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, cor.getIdcor());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir a cor.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, cor.getIdcor());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               cor.setNome(rs.getString(2));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar a cor.");
           e.printStackTrace();
           return false;
        }
    }
}