package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Colaborador;
import pojo.Funcionario;

public class DaoFuncionario {
    private Funcionario funcionario;
    private final String SQL_INCLUIR =
            "INSERT INTO FUNCIONARIO VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private final String SQL_ALTERAR =
            "UPDATE FUNCIONARIO SET NOME = ?,SEXO = ?, RG = ?, CPF = ?, DATANASCIMENTO = ?, TELEFONE = ?,"
            + "CELULAR = ?, ENDERECO = ?, NUMCASA = ?, BAIRRO = ?, DATAADMISSAO ?, DATADEMISSAO = ?"
            + "SITUACAO = ?, IDCIDADE = ? WHERE IDFUNCIONARIO = ?";
    
    private final String SQL_EXCLUIR =
            "DELETE FROM FUNCIONARIO WHERE IDFUNCIONARIO = ?";
    
    private final String SQL_CONSULTAR =
            "SELECT * FROM COLABORADOR WHERE IDFUNCIONARIO = ?";
    
//    public static final String SQL_PESQUISAR =
//            "SELECT CEB.IDCEB, CEB.NOME, COLABORADOR.NOME "
//            + "FROM CEB, COLABORADOR "
//            + "WHERE COLABORADOR.IDCOLABORADOR = CEB.IDCOLABORADORORDER BY CEB.NOME";
                
    
    public DaoFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setString(1, funcionario.getNome());
           ps.setString(2, funcionario.getSexo());
           ps.setString(3, funcionario.getRg());
           ps.setString(4, funcionario.getCpf());
           ps.setDate(5, funcionario.getDatanascimento());
           ps.setString(6, funcionario.getTelefone());
           ps.setString(7, funcionario.getCelular());
           ps.setString(8, funcionario.getEndereco());
           ps.setString(9, funcionario.getNumcasa());
           ps.setString(10, funcionario.getBairro());
           ps.setDate(11, funcionario.getDataadmissao());
           ps.setDate(12, funcionario.getDatademissao());
           ps.setString(13, funcionario.getSituacao());
           ps.setInt(14, funcionario.getIdcidade());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir o funcionario.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, funcionario.getNome());
           ps.setString(2, funcionario.getSexo());
           ps.setString(3, funcionario.getRg());
           ps.setString(4, funcionario.getCpf());
           ps.setDate(5, funcionario.getDatanascimento());
           ps.setString(6, funcionario.getTelefone());
           ps.setString(7, funcionario.getCelular());
           ps.setString(8, funcionario.getEndereco());
           ps.setString(9, funcionario.getNumcasa());
           ps.setString(10, funcionario.getBairro());
           ps.setDate(11, funcionario.getDataadmissao());
           ps.setDate(12, funcionario.getDatademissao());
           ps.setString(13, funcionario.getSituacao());
           ps.setInt(14, funcionario.getIdcidade());
           ps.setInt(15, funcionario.getIdfuncionario());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar o funcionario.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, funcionario.getIdfuncionario());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir o funcionario.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, funcionario.getIdfuncionario());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               funcionario.setNome(rs.getString(2));
               funcionario.setSexo(rs.getString(3));
               funcionario.setRg(rs.getString(4));
               funcionario.setCpf(rs.getString(5));
               funcionario.setDatanascimento(rs.getDate(6));
               funcionario.setTelefone(rs.getString(7));
               funcionario.setCelular(rs.getString(8));
               funcionario.setEndereco(rs.getString(9));
               funcionario.setNumcasa(rs.getString(10));
               funcionario.setBairro(rs.getString(11));
               funcionario.setDataadmissao(rs.getDate(12));
               funcionario.setDatademissao(rs.getDate(13));
               funcionario.setSituacao(rs.getString(14));
               funcionario.setIdcidade(rs.getInt(15));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar o funcionario.");
           e.printStackTrace();
           return false;
        }
    }
}