package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Estado;

public class DaoEstado {
    private Estado estado;
    private final String SQL_INCLUIR = "INSERT INTO ESTADO VALUES (?, ?, ?)";
    private final String SQL_ALTERAR = "UPDATE ESTADO SET NOME = ?, SIGLA = ?, WHERE IDESTADO = ?";
    private final String SQL_EXCLUIR ="DELETE FROM ESTADO WHERE IDESTADO = ?";
    private final String SQL_CONSULTAR = "SELECT * FROM ESTADO WHERE IDESTADO = ?";
    public static final String SQL_PESQUISAR = "SELECT IDESTADO, NOME, SIGLA FROM ESTADO ORDER BY NOME";
    public static final String SQLCOMBOBOX = "SELECT IDESTADO, NOME || '-' || SIGLA FROM ESTADO ORDER BY NOME";
    
    public String pesquisa(String texto){
        String sql = "SELECT * FROM ESTADO WHERE NOME LIKE'"+texto+"%' ORDER BY NOME";
        return(sql);
    }
    
    public DaoEstado(Estado estado) {
        this.estado = estado;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setInt(1, estado.getIdestado());
           ps.setString(2, estado.getNome());
           ps.setString(3, estado.getSigla());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir o Estado.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, estado.getNome());
           ps.setString(2, estado.getSigla());
           ps.setInt(4, estado.getIdestado());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar o Estado.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, estado.getIdestado());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir o Estado.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, estado.getIdestado());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               estado.setNome(rs.getString(2));
               estado.setSigla(rs.getString(3));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar o estado.");
           e.printStackTrace();
           return false;
        }
    }
}