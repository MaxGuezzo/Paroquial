package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Enfermo;
import pojo.Hospital;

public class DaoHospital {
    private Hospital hospital;
    private final String SQL_INCLUIR =
            "INSERT INTO HOSPITAL VALUES (null,?,?,?,?,?,?,?)";
    
    private final String SQL_ALTERAR =
            "UPDATE HOSPITAL SET NOME = ?, ENDERECO = ?, NUMCASA = ?, BAIRRO = ?, TELEFONE = ?,"
            + "COMPLEMENTO = ?, IDCIDADE = ? WHERE IDHOSPITAL = ?";
    
    private final String SQL_EXCLUIR =
            "DELETE FROM HOSPITAL WHERE IDHOSPITAL = ?";
    
    private final String SQL_CONSULTAR =
            "SELECT * FROM HOSPITAL WHERE IDHOSPITAL = ?";
    
    public static final String SQL_PESQUISAR =
            "SELECT IDHOSPITAL, NOME, TELEFONE FROM HOSPITAL ORDER BY NOME";
                
    
      public String pesquisa(String texto, int valor){
        String sql = "SELECT * FROM HOSPITAL WHERE";
        if(valor == 0){
            sql = sql+" NOME LIKE'"+texto+"%' ORDER BY NOME";
        }else{
            sql = sql+" IDHOSPITAL LIKE'"+texto+"%' ORDER BY IDHOSPITAL";
        }
        return(sql);
    }
    
    public DaoHospital(Hospital hospital) {
        this.hospital = hospital;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setString(1, hospital.getNome());
           ps.setString(2, hospital.getEndereco());
           ps.setString(3, hospital.getNumcasa());
           ps.setString(4, hospital.getBairro());
           ps.setString(5, hospital.getTelefone());
           ps.setString(6, hospital.getComplemento());
           ps.setInt(7, hospital.getIdcidade());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir o hospital.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, hospital.getNome());
           ps.setString(2, hospital.getEndereco());
           ps.setString(3, hospital.getNumcasa());
           ps.setString(4, hospital.getBairro());
           ps.setString(5, hospital.getTelefone());
           ps.setString(6, hospital.getComplemento());
           ps.setInt(7, hospital.getIdcidade());
           ps.setInt(8, hospital.getIdhospital());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar o hospital.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, hospital.getIdhospital());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir o hospital.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, hospital.getIdhospital());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               hospital.setNome(rs.getString(2));
               hospital.setEndereco(rs.getString(3));
               hospital.setNumcasa(rs.getString(4));
               hospital.setBairro(rs.getString(5));
               hospital.setTelefone(rs.getString(6));
               hospital.setComplemento(rs.getString(7));
               hospital.setIdcidade(rs.getInt(8));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar o hospital.");
           e.printStackTrace();
           return false;
        }
    }
}