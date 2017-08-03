package dao;

import banco.Conexao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import pojo.Colaborador;

public class DaoColaborador {
    private Colaborador colaborador;
    private final String SQL_INCLUIR =
            "INSERT INTO COLABORADOR VALUES (null,?,?,?,?,?,?,?,?,?,?,?)";
    
    private final String SQL_ALTERAR =
            "UPDATE COLABORADOR SET NOME = ?, DATANASCIMENTO = ?, TELEFONE = ?,"
            + "CELULAR = ?, ENDERECO = ?, NUMCASA = ?, BAIRRO = ?, RG = ?, CPF = ?,"
            + "SITUACAO = ?, IDCIDADE = ? WHERE IDCOLABORADOR = ?";
    
    private final String SQL_EXCLUIR =
            "DELETE FROM COLABORADOR WHERE IDCOLABORADOR = ?";
    
    private final String SQL_CONSULTAR =
            "SELECT * FROM COLABORADOR WHERE IDCOLABORADOR = ?";
    
        public static final String SQL_PESQUISAR =
            "SELECT IDCOLABORADOR, NOME, CPF "
            + "FROM COLABORADOR ORDER BY NOME";
                
        public String pesquisa(String texto){
        String sql = "SELECT * FROM COLABORADOR WHERE NOME LIKE'"+texto+"%' ORDER BY NOME";
        return(sql);
    }
        
    public DaoColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setString(1, colaborador.getNome());
           ps.setDate(2, (Date) colaborador.getDatanascimento());
           ps.setString(3, colaborador.getTelefone());
           ps.setString(4, colaborador.getCelular());
           ps.setString(5, colaborador.getEndereco());
           ps.setString(6, colaborador.getNumcasa());
           ps.setString(7, colaborador.getBairro());
           ps.setString(8, colaborador.getRg());
           ps.setString(9, colaborador.getCpf());
           ps.setString(10, colaborador.getSituacao() ? "A" : "I");
           ps.setInt(11, colaborador.getIdcidade());
           ps.executeUpdate();
           return true;
           
        } catch (SQLException e) {
            e.printStackTrace();
            if(e.getErrorCode() == 335544665  ){
                JOptionPane.showMessageDialog(null, "Este CPF ja esta cadastrado no sistema.");
            }
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir o colaborador.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, colaborador.getNome());
           ps.setDate(2, (Date) colaborador.getDatanascimento());
           ps.setString(3, colaborador.getTelefone());
           ps.setString(4, colaborador.getCelular());
           ps.setString(5, colaborador.getEndereco());
           ps.setString(6, colaborador.getNumcasa());
           ps.setString(7, colaborador.getBairro());
           ps.setString(8, colaborador.getRg());
           ps.setString(9, colaborador.getCpf());
           ps.setString(10, colaborador.getSituacao() ? "A" : "I");
           ps.setInt(11, colaborador.getIdcidade());
           ps.setInt(12, colaborador.getIdcolaborador());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar o colaborador.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, colaborador.getIdcolaborador());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir o colaborador.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, colaborador.getIdcolaborador());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               colaborador.setNome(rs.getString(2));
               colaborador.setDatanascimento(rs.getDate(3));
               colaborador.setTelefone(rs.getString(4));
               colaborador.setCelular(rs.getString(5));
               colaborador.setEndereco(rs.getString(6));
               colaborador.setNumcasa(rs.getString(7));
               colaborador.setBairro(rs.getString(8));
               colaborador.setRg(rs.getString(9));
               colaborador.setCpf(rs.getString(10));
               colaborador.setSituacao(rs.getString(11).equals("A"));
               colaborador.setIdcidade(rs.getInt(12));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar o colaborador.");
           e.printStackTrace();
           return false;
        }
    }
}