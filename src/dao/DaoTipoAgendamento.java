package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Cor;
import pojo.TipoAgendamento;

public class DaoTipoAgendamento {
    private TipoAgendamento tipoAgendamento;
    private final String SQL_INCLUIR =
            "INSERT INTO TIPO_AGENDAMENTO VALUES (null,?)";
    
    private final String SQL_ALTERAR =
            "UPDATE TIPO_AGENDAMENTO SET NOME = ? WHERE IDTIPOAGENDAMENTO = ?";
    
    private final String SQL_EXCLUIR =
            "DELETE FROM TIPO_AGENDAMENTO WHERE IDTIPOAGENDAMENTO = ?";
    
    private final String SQL_CONSULTAR =
            "SELECT * FROM TIPO_AGENDAMENTO WHERE IDTIPOAGENDAMENTO = ?";
    
    public static final String SQL_PESQUISAR =
            "SELECT IDTIPOAGENDAMENTO, NOME FROM TIPO_AGENDAMENTO";
    
    public String pesquisa(String texto, int valor){
        String sql = "SELECT * FROM TIPO_AGENDAMENTO WHERE";
        if(valor == 0){
            sql = sql+" NOME LIKE'"+texto+"%' ORDER BY NOME";
        }else{
            sql = sql+" IDTIPOAGENDAMENTO LIKE'"+texto+"%' ORDER BY IDTIPOAGENDAMENTO";
        }
        return(sql);
    }
                
    
    public DaoTipoAgendamento(TipoAgendamento tipoAgendamento) {
        this.tipoAgendamento = tipoAgendamento;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setString(1, tipoAgendamento.getNome());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir o tipo do Agendamento.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, tipoAgendamento.getNome());
           ps.setInt(2, tipoAgendamento.getIdtipoagendamento());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar o tipo do Agendamento.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, tipoAgendamento.getIdtipoagendamento());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir o tipo do Agendamento.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, tipoAgendamento.getIdtipoagendamento());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               tipoAgendamento.setNome(rs.getString(2));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar o tipo do Agendamento.");
           e.printStackTrace();
           return false;
        }
    }
}