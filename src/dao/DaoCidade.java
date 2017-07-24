
package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Cidade;

public class DaoCidade {
    private Cidade cidade;
    private final String SQL_INCLUIR =
            "INSERT INTO CIDADE VALUES (?, ?, ?)";
    private final String SQL_ALTERAR =
            "UPDATE CIDADE SET NOME = ?, IDESTADO = ?, WHERE IDCIDADE = ?";
    private final String SQL_EXCLUIR =
            "DELETE FROM CIDADE WHERE IDCIDADE = ?";
    private final String SQL_CONSULTAR =
            "SELECT * FROM CIDADE WHERE IDCIDADE = ?";
    public static final String SQL_PESQUISAR =
            "SELECT CIDADE.IDCIDADE, CIDADE.NOME, ESTADO.SIGLA FROM cidade, ESTADO\n" +
                "where ESTADO.IDESTADO = CIDADE.IDESTADO ORDER BY CIDADE.NOME";
    
    public DaoCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setInt(1, cidade.getIdestado());
           ps.setString(2, cidade.getNome());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir o Cidade.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, cidade.getNome());
           ps.setInt(2, cidade.getIdestado());
           ps.setInt(4, cidade.getIdcidade());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar o Cidade.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, cidade.getIdcidade());
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
           ps.setInt(1, cidade.getIdcidade());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               cidade.setNome(rs.getString(2));
               cidade.setIdestado(rs.getInt(3));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar o estado.");
           e.printStackTrace();
           return false;
        }
    }
}