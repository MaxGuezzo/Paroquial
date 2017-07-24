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
            "INSERT INTO MISSA VALUES (?)";
    private final String SQL_ALTERAR =
            "UPDATE MISSA SET NOME = ? WHERE IDMISSA = ?";
    private final String SQL_EXCLUIR =
            "DELETE FROM MISSA WHERE IDMISSA = ?";
    private final String SQL_CONSULTAR =
            "SELECT * FROM MISSA WHERE IDMISSA = ?";
    
//    public static final String SQL_PESQUISAR =
//            "SELECT IDESTADO, NOME, SIGLA FROM ESTADO ORDER BY NOME";
//    public static final String SQLCOMBOBOX = "SELECT IDESTADO, NOME || '-' || SIGLA FROM ESTADO ORDER BY NOME";
    
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
           ps.setInt(4, missa.getIdmissa());
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