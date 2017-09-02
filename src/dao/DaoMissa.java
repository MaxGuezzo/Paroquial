package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Estado;
import pojo.Missa;

public class DaoMissa {
    private Missa missa;
    private final String SQL_INCLUIR =
            "INSERT INTO MISSA VALUES (null,?)";
    private final String SQL_ALTERAR =
            "UPDATE MISSA SET NOME = ? WHERE IDMISSA = ?";
    private final String SQL_EXCLUIR =
            "DELETE FROM MISSA WHERE IDMISSA = ?";
    private final String SQL_CONSULTAR =
            "SELECT * FROM MISSA WHERE IDMISSA = ?";
    
    public static final String SQL_PESQUISAR =
            "SELECT IDMISSA, NOME FROM MISSA";
                
    
    public String pesquisa(String texto, int valor){
        String sql = "SELECT * FROM MISSA WHERE";
        if(valor == 0){
            sql = sql+" NOME LIKE'"+texto+"%' ORDER BY NOME";
        }else{
            sql = sql+" IDMISSA LIKE'"+texto+"%' ORDER BY IDMISSA";
        }
        return(sql);
    }
    public DaoMissa(Missa missa) {
        this.missa = missa;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setString(1, missa.getNome());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir a Missa.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, missa.getNome());
           ps.setInt(2, missa.getIdmissa());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar a Missa.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, missa.getIdmissa());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir a Missa.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, missa.getIdmissa());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               missa.setNome(rs.getString(2));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar a Missa.");
           e.printStackTrace();
           return false;
        }
    }
}